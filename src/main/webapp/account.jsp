<%@include file="hat.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cinema.dto.UserDto" %>
<%@ page import="cinema.dto.FilmDto" %>
<%@ page import="java.util.Set" %>
<html>
<head>
    <br/>
    <title>Account</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 2px solid black;
            padding: 8px;
            text-align: center;
        }

    </style>
</head>

<body>
<a href="index.jsp">Back to Menu</a>
<br/>
<h1>Account:</h1>
<% UserDto user = (UserDto) request.getAttribute("user");
%>
<td>User Id: <%= user.getId() %>
</td>
<br>
<td>UserName: <%= user.getUserName()%>
</td>
<h2>Desired Films:</h2>
<table>
    <tr>
        <td><B>Film ID</B></td>
        <td><B>Film title</B></td>
        <td><B>Film description</B></td>
        <td><B>Film year</B></td>
        <td><B>Film genre</B></td>
        <td><B>Film director</B></td>

    </tr>
    <% Set<FilmDto> films = (Set<FilmDto>) request.getAttribute("films");
        for (FilmDto film : films) {
    %>
    <tr>
        <td><%= film.getId() %></td>
        <td><%= film.getTitle() %></td>
        <td><%= film.getDescription() %></td>
        <td><%= film.getYear() %></td>
        <td><%= film.getGenre() %></td>
        <td><%= film.getDirector() %></td>

        <td class="action-buttons">
            <form name="delete" method="post" action="delete_film_from_account">
                <button name="id" value="<%= film.getId() %>">Delete</button>
            </form>
        </td>

    </tr>
    <%
        }
    %>
</table>
<br/>

<h2>Watched Films:</h2>
<table>
    <tr>
        <td><B>Film ID</B></td>
        <td><B>Film title</B></td>
        <td><B>Film description</B></td>
        <td><B>Film year</B></td>
        <td><B>Film genre</B></td>
        <td><B>Film director</B></td>

    </tr>
    <% Set<FilmDto> films2 = (Set<FilmDto>) request.getAttribute("watched_films");
        for (FilmDto film : films2) {
    %>
    <tr>
        <td><%= film.getId() %></td>
        <td><%= film.getTitle() %></td>
        <td><%= film.getDescription() %></td>
        <td><%= film.getYear() %></td>
        <td><%= film.getGenre() %></td>
        <td><%= film.getDirector() %></td>

        <td class="action-buttons">
            <form name="delete" method="post" action="delete_watched_film">
                <button name="id" value="<%= film.getId() %>">Delete</button>
            </form>
        </td>

    </tr>
    <%
        }
    %>
</table>
</body>
</html>
