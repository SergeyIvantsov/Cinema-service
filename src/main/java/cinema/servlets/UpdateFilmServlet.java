package cinema.servlets;

import cinema.dto.DirectorDto;
import cinema.dto.FilmDto;
import cinema.service.DirectorService;
import cinema.service.FilmService;
import cinema.service.impl.DirectorServiceImpl;
import cinema.service.impl.FilmServiceImpl;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "updateFilmServlet", value = "/update")
public class UpdateFilmServlet extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();
    private DirectorService directorService = new DirectorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DirectorDto> directorDtoList = directorService.getAll();
        request.setAttribute("directorDtoList", directorDtoList);

        Integer id = ServletUtil.getIntegerParam(request, "id");
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Film Id is null");
            return;
        }

        FilmDto filmDto = filmService.get(id);
        request.setAttribute("film", filmDto);

        if (filmDto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Film not found");
            return;
        }
        request.setAttribute("selectedDirectorDto", filmDto.getDirector().getId());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/update.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        filmService.update(ServletUtil.getIntegerParam(request, "id"),
                ServletUtil.mapFilm(request));
        response.sendRedirect("films_manager");
    }

    @Override
    public void destroy() {
        filmService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
