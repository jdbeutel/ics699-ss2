<head>
    <meta name='layout' content='main'/>
    <title>Create or Link Account</title>
    <style type="text/css">
    fieldset {
        border: 1px solid green;
        padding: 1em;
        font: 80%/1 sans-serif;
    }

    fieldset legend {
        padding: 0.2em 0.5em;
        border: 1px solid green;
        color: green;
        font-weight: bold;
        font-size: 90%;
        text-align: right;
    }

    fieldset label {
        float: left;
        width: 25%;
        margin-top: 5px;
        margin-right: 0.5em;
        padding-top: 0.2em;
        text-align: right;
        font-weight: bold;
    }

    fieldset input[type="submit"] {
        float: right;
        background: #F0F0F0;
        cursor: pointer;
    }

    fieldset br {
        margin-top: 10px;
    }
    </style>
</head>

<body>

<div class='body' style="padding: 15px;">
    <g:if test='${flash.error}'>
        <div class="errors">${flash.error}</div>
    </g:if>

    <h4><g:message code="springSecurity.oauth.registration.link.not.exists" default="No user was found with this account." args="[session.springSecurityOAuthToken.providerName]"/></h4>
    <br/>

    <g:hasErrors bean="${createAccountCommand}">
    <div class="errors">
        <g:renderErrors bean="${createAccountCommand}" as="list"/>
    </div>
    </g:hasErrors>

    <g:form action="createAccount" method="post" autocomplete="off">
        Create a new Bendy account for ${session.springSecurityOAuthToken.socialId}?
        <g:submitButton name="${message(code: 'springSecurity.oauth.registration.create.button', default: 'Create')}"/>
    </g:form>

    <br/>

    <g:link controller="login" action="auth"><g:message code="springSecurity.oauth.registration.back" default="Back to login page"/></g:link>
</div>

</body>
