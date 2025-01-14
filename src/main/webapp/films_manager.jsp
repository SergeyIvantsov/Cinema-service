<%@include file="hat.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cinema.dto.FilmDto" %>
<%@ page import="java.util.List" %>
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

<form action="find_film" method="get">
  <label>Find film by title:</label>
  <input name="find_film" type="text" placeholder="Enter film title"/>
  <input type="submit" value="Search" />
</form>

<br/>
<a href="save"><h3>ADD FILM TO COMMON LIST</h3></a><br/>
<br/>
<h1>All Films:</h1>
<table>
  <tr>
    <td><B>Film ID</B></td>
    <td><B>Film title</B></td>
    <td><B>Film description</B></td>
    <td><B>Film year</B></td>
    <td><B>Film genre</B></td>
    <td><B>Film director</B></td>

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
</body>
</html>
