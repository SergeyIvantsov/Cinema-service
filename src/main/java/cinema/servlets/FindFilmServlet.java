package cinema.servlets;

import cinema.dto.FilmDto;
import cinema.service.FilmService;
import cinema.service.impl.FilmServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "find_film_servlet", value = "/find_film")
public class FindFilmServlet extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTitle = req.getParameter("find_film");


        List<FilmDto> filmDtoList;
        if (searchTitle != null && !searchTitle.isEmpty()) {
            filmDtoList = filmService.findByName(searchTitle);
        } else {
            filmDtoList = filmService.getAll();
        }

        req.setAttribute("films", filmDtoList);
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/films_manager.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.filmService.closeDao();
        super.destroy();
    }
}
