<%@ page import="cinema.dto.FilmDto" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="cinema.dto.DirectorDto" %>
<%@ page import="java.util.List" %>
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
        <input name="title" type="text" value="<%=filmDto.getTitle()%>" required>
    </label>
    <br/>
    <label>
        Fill film description:
        <input name="description" type="text" value="<%=filmDto.getDescription()%>" required>
    </label>
    <br/>
    <label>
        Fill film year:
        <input name="year" type="number" value="<%=filmDto.getYear()%>" required>
    </label>
    <br/>
    <label>
        Fill film genre:
        <input name="genre" type="text" value="<%=filmDto.getGenre()%>" required>
    </label>
    <br/>
    <label>
        Select film director:
        <select name="director" required>
            <option value=" " selected>Select Director</option>
            <%
                List<DirectorDto> directorDtoList = (List<DirectorDto>) request.getAttribute("directorDtoList");
                Integer selectedDirectorId = (Integer) request.getAttribute("selectedDirectorDto");
                for (DirectorDto director : directorDtoList) {
            %>
            <option value="<%=director.getId()%>"
                <%=(selectedDirectorId !=null && selectedDirectorId.equals(director.getId())) ? "selected" : "" %>>
                <%=director.getDirectorName() + " " + director.getDirectorSurname()%>
            </option>
            <%
                }
            %>
        </select>
    </label>


    <%--    <label>--%>
    <%--        Fill film actors:--%>
    <%--        <input name="actors" type="text" value="<%= StringUtils.join(filmDto.getActorsDto(), ", ") %>" required>--%>
    <%--    </label>--%>

    <br/>
    <input type=submit value="Submit">
</form>
<br/>
<a href="films_manager">BACK TO ALL FILMS</a><br/>
</body>
</html>
