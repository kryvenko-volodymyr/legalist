<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <title>vseprosto Login</title>

    <%@include file="jspf/head.jspf"%>

    <%@include file="jspf/navbarFooterStyle.jspf"%>

</head>

<body>
    <%@include file="jspf/navbar.jspf"%>

    <div class ='container'>
        <h2 class='text-center'>Вхід для зареєстрованих користувачів</h2>

        <c:if test="${not empty param.authentication_error}">
            <h1>Woops!</h1>

            <p class="error">Your login attempt was not successful.</p>
        </c:if>
        <c:if test="${not empty param.authorization_error}">
            <h1>Woops!</h1>

            <p class="error">You are not permitted to access that resource.</p>
        </c:if>

        <form action="<c:url value="/login"/>" method="post" role="form">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name='username' value="john">
            </div>
            <div class="form-group">
                <label for="pwd">Пароль</label>
                <input type="password" class="form-control" id="pwd" name='password' value="123">
            </div>
            <div class="checkbox">
                <label><input type="checkbox"> Не вимагати пароль протягом наступних 2-х тижнів</label>
            </div>
            <button type="submit" class="btn btn-default">Увійти</button>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
    </div>
</body>
</html>