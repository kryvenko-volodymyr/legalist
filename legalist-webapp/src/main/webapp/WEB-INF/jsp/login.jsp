<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Legalist login custom page</title>
    </head>
    <body onload='document.f.username.focus();'>
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
    </body>
</html>
