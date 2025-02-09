<%@include file="hat.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cinema.dto.FilmDto" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cinema.dto.ActorDto" %>
<%@ page import="java.util.Set" %>
<html>
<head>
    <title>Films list</title>
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
<br/>

<h1>Films by Director:</h1>
<table>
    <tr>
        <td><B>Film ID</B></td>
        <td><B>Film title</B></td>
        <td><B>Film description</B></td>
        <td><B>Film year</B></td>
        <td><B>Film genre</B></td>
        <td><B>Film director</B></td>
        <%--        <td><B>Film actors</B></td>--%>
    </tr>
    <% List<FilmDto> films = (List<FilmDto>) request.getAttribute("filmsByDirector");
        for (FilmDto film : films) {
    %>
    <tr>
        <td><%= film.getId() %>
        </td>
        <td><%= film.getTitle() %>
        </td>
        <td><%= film.getDescription() %>
        </td>
        <td><%= film.getYear() %>
        </td>
        <td><%= film.getGenre() %>
        </td>
        <td><%= film.getDirector().getDirectorName() %>
            <%=film.getDirector().getDirectorSurname()%>
        </td>

        <%--        <td>--%>
        <%--            <ul>--%>
        <%--                <%--%>
        <%--                    for (ActorDto actorDto : film.getActorsDto()) {--%>
        <%--                %>--%>
        <%--                <li><%= actorDto.getActorName() %>--%>
        <%--                    <%= actorDto.getActorSurname() %>--%>
        <%--                </li>--%>
        <%--                <%--%>
        <%--                    }--%>
        <%--                %>--%>
        <%--            </ul>--%>
        <%--        </td>--%>


        <td class="action-buttons">
            <form name="add" method="post" action="add">
                <button name="id" value="<%= film.getId() %>">Add Film to desired</button>
            </form>
        </td>
        <td class="action-buttons">
            <form name="add_watched" method="post" action="add_watched">
                <button name="id" value="<%= film.getId() %>">Mark Film as watched</button>
            </form>
        </td>
    </tr>

    <%
        }
    %>
</table>
<br/>
<br/>

</body>
</html>

