import com.getsu.wcy.auth.FacebookSpringSecurityOAuthService

// Place your Spring DSL code here
beans = {

    facebookSpringSecurityOAuthService (FacebookSpringSecurityOAuthService) {
        oauthService = ref("oauthService")
    }
}
