<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>legalist.ua</title>
    </head>
    <body>
        <h1>Hello Legalist!</h1>
        <c:forEach var="element" items="${cookie}">
            <div>${element.key} = ${element.value.value}</div>
        </c:forEach>
    </body>
</html>
