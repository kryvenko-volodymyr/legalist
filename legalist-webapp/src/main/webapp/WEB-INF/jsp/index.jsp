<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>legalist.ua</title>
    </head>

    <body>
        

        <security:authorize access="not isAuthenticated()">
            <h3>This is custom index JSP</h3>
            <form name='login' action='login' method='POST'>
                <table>
                    <tr><td>User:</td><td>
                            <input type='text' name='username' value=''></td></tr>
                    <tr><td>Password:</td>
                        <td><input type='password' name='password'/></td></tr>
                    <tr><td colspan='2'>
                            <input name="submit" type="submit" value="Login"/></td></tr>
                    <tr><td colspan='2'>
                            <input id="remember_me" name="remember-me" type="checkbox"/>
                            <label for="remember_me" class="inline">Remember me</label>
                        </td></tr>

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                </table>
            </form>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a href='logout'>Logout</a>
            <h3>Hello <a href='user'><security:authentication property="principal.username"/></a></h3>
        </security:authorize>
    </body>
</html>