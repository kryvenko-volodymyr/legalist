<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <a href='index'>Home</a>
        <br>
        <a href='logout'>Logout</a>
        <h1>Hello <security:authentication property="principal.username"/></h1>

        <p>authorities: <security:authentication property="authorities"/></p>
        <p>credentials: <security:authentication property="credentials"/></p>
        <p>details: <security:authentication property="details"/></p>
        <p>principal: <security:authentication property="principal"/></p>

    </body>
</html>
