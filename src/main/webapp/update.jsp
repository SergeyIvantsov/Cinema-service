<%@ page import="cinema.dto.FilmDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update film</title>
</head>

<body>
<%FilmDto filmDto = (FilmDto) request.getAttribute("film");%>
<h2>Update car</h2>
<form name="update" method="post" action="update">
<input name="id" type="hidden" value="<%= filmDto.getId()%>" required>

    <label>
        Fill film title:
        <input name="title" type="text" required>
    </label>
    <br/>
    <label>
        Fill film description:
        <input name="description" type="text" required>
    </label>
    <br/>
    <label>
        Fill film year:
        <input name="year" type="number" required>
    </label>
    <br/>
    <label>
        Fill film genre:
        <input name="genre" type="text" required>
    </label>
    <br/>
    <label>
        Fill film director:
        <input name="director" type="text" required>
    </label>
    <br/>
    <input type=submit value="Submit">
</form>
<br/>
<a href="films_manager">BACK TO ALL FILMS</a><br/>

</form>

</body>
</html>
