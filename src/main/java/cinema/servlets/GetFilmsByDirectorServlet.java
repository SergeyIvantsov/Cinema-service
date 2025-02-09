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
import java.util.List;

@WebServlet(name = "getFilmsByDirector", value = "/films_by_director")
public class GetFilmsByDirectorServlet extends HttpServlet {

    private FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer directorId = ServletUtil.getIntegerParam(request, "directorId");

        List<FilmDto> filmsByDirector = filmService.getFilmsByDirector(directorId);

        request.setAttribute("filmsByDirector", filmsByDirector);

        RequestDispatcher requestDispatcher = request.getServletContext()
                .getRequestDispatcher("/films_by_director.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    public void destroy() {
        this.filmService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }


}
