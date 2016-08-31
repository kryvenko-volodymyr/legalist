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
            <h3>Custom login with Username and Password</h3>
            <form name='f' action='/legalist/login' method='POST'>
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
            <h3>Hello <security:authentication property="principal.username"/></h3>
            <a href='user'>To user page</a>
            <br>
            <a href='logout'>Logout</a>
        </security:authorize>
        <br>
        <br>
        <c:forEach var="element" items="${cookie}">
            <div>${element.key} = ${element.value.value}</div>
        </c:forEach>
        <ul>
            <c:forEach var="node" items="${nodes}">
                <li>${node.title}</li>
                </c:forEach>
        </ul>
        <ul>
            <c:forEach var="simpleHierarchyElement" items="${simpleHierarchy}">
                <li>${simpleHierarchyElement.key.title}</li>
                </c:forEach>
        </ul>
        <ul>
            <c:forEach var="fullHierarchyElement" items="${fullHierarchy}">
                <li>${fullHierarchyElement.key.title}</li>
                </c:forEach>
        </ul>
    </body>
</html>
