package com.getsu.wcy.auth

import grails.plugin.springsecurity.oauth.FacebookOAuthToken
import grails.plugin.springsecurity.oauth.OAuthLoginException
import org.scribe.model.Token
import grails.converters.JSON

/**
 * Facebook oauth service using @facebook.com email address instead of id for identity.
 *
 * @author jbeutel@hawaii.edu
 */
class FacebookSpringSecurityOAuthService {

    def oauthService

    def createAuthToken(Token accessToken) {
        def response = oauthService.getFacebookResource(accessToken, 'https://graph.facebook.com/me')
        def user
        try {
            user = JSON.parse((String) response.body)
        } catch (Exception e) {
            log.error "Error parsing response from Facebook. Response:\n${response.body}"
            throw new OAuthLoginException("Error parsing response from Facebook", e)
        }
        if (! user?.username) {
            log.error "No username from Facebook. Response:\n${response.body}"
            throw new OAuthLoginException("No user id from Facebook")
        }
        // Use @facebook.com email address instead of user's preferred email address on Facebook
        // because not all Facebook users have a preferred email address (e.g., mobile Facebook app users).
        // Also, the preferred email address would need to be requested in addition to the basic info.
        // Note that the @facebook.com email is delivered as a message within Facebook, not a real email.
        def email = "${user.username}@facebook.com"
        return new FacebookOAuthToken(accessToken, email)
    }
}
