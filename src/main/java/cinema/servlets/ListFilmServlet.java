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
public class    ListFilmServlet extends HttpServlet {
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = 1;
        int pageSize = 5;
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<FilmDto> filmDtoList = filmService.getFilmsForPage(page, pageSize);
        int totalFilms = filmService.getTotalFilmCount();
        int totalPages = (int) Math.ceil((double) totalFilms / pageSize);

        req.setAttribute("films", filmDtoList);
        req.setAttribute("filmsSize", totalFilms);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("totalPages", totalPages);

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
