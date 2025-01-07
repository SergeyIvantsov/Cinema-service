<%@include file="hat.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cinema.dto.UserDto" %>
<html>
<head>
    <title>Account</title>
</head>

<body>
<br/>
<h1>Account:</h1>


<% UserDto user = (UserDto) request.getAttribute("user");
%>
<td>User Id: <%= user.getId() %>
</td>
<br>
<td>UserName: <%= user.getUsername()%>
</td>
<%--//Добав табл.--%>
</body>
</html>
