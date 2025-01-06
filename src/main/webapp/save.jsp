
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add film</title>
</head>
<body>
<h2>Add film:</h2>
<form name="save" method="post" action="save">
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
<%--    <button>Submit</button>--%>
</form>
<br/>
<a href="films_manager">Return to All Films</a><br/>
</body>
</html>