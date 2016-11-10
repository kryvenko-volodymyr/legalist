<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>legalist.ua</title>
        <script>
            //builds oauth/authorization link depending on the webapp hostname
            window.onload = function () {
                var origin = document.location.origin;
                var href = document.location.href;
                
                var tokenQuery = '/oauth/authorize?response_type=token&client_id=vseprosto&redirect_uri=' + href + '&scope=read';

                var getAccessTokenLink = document.getElementById("get-access-token");
                if (getAccessTokenLink) getAccessTokenLink.setAttribute('href', origin + tokenQuery);
            }
        </script>

        <script type='text/javascript'>
            var url = document.location;
            var hash = url.hash;
            var accessToken;

            if (hash) {
                accessToken = hash.split('&').filter(function (el) {
                    if (el.match('access_token') !== null)
                        return true;
                })[0].split('=')[1];
                document.cookie = "access-token=" + accessToken;
            }

            console.log(document.cookie);
        </script>

    </head>

    <body>
        <h1>vse prosto</h1>
        <h2>Custom index.jsp</h2>

        <security:authorize access="not isAuthenticated()">
            <p>NOT authenticated at authorization server</p>
            <a id='get-access-token' href=''>
                Authenticate and get token
            </a>
        </security:authorize>

        <security:authorize access="isAuthenticated()">
            <p>Successfully authenticated as 
                <a href='user'>
                    <security:authentication property="principal.username"/>
                </a>.
            </p>
            <a href='logout'>Logout</a>
        </security:authorize>

    </body>
</html>