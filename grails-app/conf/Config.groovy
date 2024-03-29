// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

// to avoid putting secrets in git
grails.config.locations = ["file:${userHome}/grails-conf/${appName}-config.groovy"]

grails.gorm.failOnError = true

grails.project.groupId = 'com.getsu.wcy' // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password', 'password1', 'password2']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn 'grails.app.services.grails.plugin.springsecurity.ui.SpringSecurityUiService'
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.getsu.wcy.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.getsu.wcy.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'com.getsu.wcy.auth.Role'
grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.ui.register.emailFrom = 'bendy@getsu.com'
grails.plugin.springsecurity.ui.register.emailSubject = 'new Bendy account'
grails.plugin.springsecurity. ui.password.validationRegex = /^.*$/
grails.plugin.springsecurity.interceptUrlMap = [
        '/securityInfo/**':                 ['ROLE_ADMIN'],
        '/registrationCode/**':             ['ROLE_ADMIN'],
        '/role/**':                         ['ROLE_ADMIN'],
        '/user/**':                         ['ROLE_ADMIN'],

        '/foo/**':                          ['ROLE_USER'],

        '/':                                ['permitAll'],
        '/index':                           ['permitAll'],
        '/index.gsp':                       ['permitAll'],
        '/**/js/**':                        ['permitAll'],
        '/**/css/**':                       ['permitAll'],
        '/**/images/**':                    ['permitAll'],
        '/**/favicon.ico':                  ['permitAll'],
        '/login/**':                        ['permitAll'],
        '/logout/**':                       ['permitAll'],
        '/register/**':                     ['permitAll'],
        '/oauth/**':                        ['permitAll'],
]

// mail depends on external config file
grails {
    mail {
        // host =       // in external config file
        port = 465
        // username =        // in external config file
        // password =        // in external config file
        props = ["mail.smtp.auth":"true",
                "mail.smtp.socketFactory.port":"465",
                "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                "mail.smtp.socketFactory.fallback":"false"]
    }
}

def appName = grails.util.Metadata.current.'app.name'
def baseURL = grails.serverURL ?: "http://localhost:${System.getProperty('server.port', '8080')}/${appName}"
oauth {
    providers {

        // for Google OAuth 2.0
        google {
            api = org.grails.plugin.springsecurity.oauth.GoogleApi20
            key = // i.e., client ID for web app, in external config file
            secret = // i.e., client secret for web app, in external config file
            successUri = '/oauth/google/success'
            failureUri = '/oauth/google/failure'
            callback = "${baseURL}/oauth/google/callback"
            scope = 'https://www.googleapis.com/auth/userinfo.email'
        }

        facebook {
            api = org.scribe.builder.api.FacebookApi
            key = // i.e., Facebook App ID, in external config file
            secret = // i.e., Facebook App Secret, in external config file
            successUri = '/oauth/facebook/success'
            failureUri = '/oauth/facebook/error'
            callback = "${baseURL}/oauth/facebook/callback"
        }
    }
}
// Added by the Spring Security OAuth plugin:
grails.plugin.springsecurity.oauth.domainClass = 'com.getsu.wcy.auth.OAuthID'
