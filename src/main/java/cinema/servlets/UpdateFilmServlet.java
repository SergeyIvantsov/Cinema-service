package cinema.servlets;

import cinema.dto.FilmDto;
import cinema.service.FilmService;
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

@WebServlet(name = "updateFilmServlet", value = "/update")
public class UpdateFilmServlet extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ServletUtil.getIntegerParam(request, "id");
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Film Id is null");
            return;
        }

        FilmDto filmDto = filmService.get(id);
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("film", filmDto);
        if (filmDto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Film not found");
            return;
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/update.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
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
