<%@include file="hat.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cinema.dto.FilmDto" %>
<%@ page import="java.util.List" %>
<%--<%@ page import="cinema.utils.ServletConstants" %>--%>
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
<%--<a href="<%= ServletConstants.CARS_SAVE_SERVLET %>"><h1>SAVE CAR PAGE</h1></a><br/>--%>
<br/>
<h1>All Films:</h1>
<table>
  <tr>
    <td>Film ID</td>
    <td>Film title</td>
    <td>Film description</td>
    <td>Film year</td>
    <td>Film genre</td>
    <td>Film director</td>

  </tr>
  <% List<FilmDto> films = (List<FilmDto>) request.getAttribute("films");
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
      <form name="delete" method="post" action="delete">
        <button name="id" value="<%= film.getId() %>">Delete</button>
      </form>
    </td>
    <td class="action-buttons">
      <form name="update" method="get" action="update">
        <button name="id" value="<%= film.getId() %>">Update</button>
      </form>
    </td>

  </tr>
  <%
    }
  %>
</table>
</body>
</html>
