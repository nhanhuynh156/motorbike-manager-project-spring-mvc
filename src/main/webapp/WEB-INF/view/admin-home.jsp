<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/14/2018
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Hello Motor</h1>
<a href="/motorbike/home">Motorbike</a>
    <br><br>
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <input type="submit" value="log out">
    </form>
    <%--<a onclick="document.forms['logoutForm'].submit()" >Logout</a>--%>
</body>
</html>
