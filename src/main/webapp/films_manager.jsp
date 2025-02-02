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
<form action="find_film" method="get">
    <label>Find film by title:</label>
    <input name="find_film" type="text" placeholder="Enter film title"/>
    <input type="submit" value="Search"/>
</form>

<br/>
<a href="save"><h3>ADD FILM TO COMMON LIST</h3></a><br/>
<br/>

<% String MessageWatched = (String) session.getAttribute("MessageWatched"); %>
<% if (MessageWatched != null) { %>
<div class="alert alert-success">
    <%= MessageWatched %>
</div>
<% session.removeAttribute("MessageWatched"); %>
<% } %>

<% String MessageDesired = (String) session.getAttribute("MessageDesired");%>
<%if (MessageDesired != null) { %>
<div class="alert alert-success">
    <%= MessageDesired %>
</div>
<%session.removeAttribute("MessageDesired");%>
<% } %>

<h1>All Films:</h1>
<table>
    <tr>
        <td><B>Film ID</B></td>
        <td><B>Film title</B></td>
        <td><B>Film description</B></td>
        <td><B>Film year</B></td>
        <td><B>Film genre</B></td>
        <td><B>Film director</B></td>
        <td><B>Film actors</B></td>
    </tr>
    <% List<FilmDto> films = (List<FilmDto>) request.getAttribute("films");
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
        <td><%= film.getDirector() %>
        </td>

        <td>
            <ul>
                <%
                    for (ActorDto actorDto : film.getActorsDto()) {
                %>
                <li><%= actorDto.getActorName() %>
                    <%= actorDto.getActorSurname() %>
                </li>
                <%
                    }
                %>
            </ul>
        </td>


        <td class="action-buttons">
            <form name="delete" method="post" action="delete">
                <button name="id" value="<%= film.getId() %>">Delete</button>
            </form>
        </td>
        <td class="action-buttons">
            <form name="update" method="get" action="update">
                <button name="id" value="<%= film.getId() %>">Update</button>
            </form>
        </td>
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

<div class="pagination">
    <% Integer currentPage = (Integer) request.getAttribute("currentPage"); %>
    <% if (currentPage > 1) { %>
    <%! Integer previousPage; %>
    <a href="?page=<%= previousPage=currentPage - 1 %>">Previous Page<%=previousPage%></a>
    <% } %>


    <% Integer totalPages = (Integer) request.getAttribute("totalPages"); %>
    <% if (currentPage < totalPages) { %>
    <%!Integer nextPage; %>
    <a href="?page=<%=nextPage= currentPage + 1 %>">Next Page <%= nextPage %></a>
    <% } %>
</div>
<br/>
</body>
</html>
