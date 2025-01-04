package cinema.servlets;

import cinema.dto.FilmDto;
import cinema.service.FilmService;
import cinema.service.impl.FilmServiceImpl;
import cinema.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "listFilmServlet", value = "/films_manager")
public class ListFilmServlet extends HttpServlet {
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<FilmDto> filmDtoList = this.filmService.getAll();

        req.setAttribute("films", filmDtoList);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/films_manager.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.filmService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
