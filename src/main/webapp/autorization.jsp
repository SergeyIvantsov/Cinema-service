<%@include file="hat.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login / Register</title>
</head>

<body>
<br>
<h1><B>Registration</B></h1>
<br>
<form action="register" method="post">
    <label>Login:</label>
    <input name="register_login" type="text" placeholder="Enter login" required>
    <input type="submit" value="Submit">
    <label>Password:</label>
    <input name="register_password" type="text" placeholder="Enter password" required>
    <input type="submit" value="Submit">
</form>
<br>
<h1><B>Authorization</B></h1>
<form action = "login" method = "post">
    <label>If you have account input your Login to enter:</label>
    <input name="input_login" type="text" placeholder="Enter login" required>
    <input type="submit" value="Submit">
</form>
</body>
</html>
