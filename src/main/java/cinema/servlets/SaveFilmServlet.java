package cinema.servlets;


import cinema.dto.DirectorDto;
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

@WebServlet(name = "saveFilmServlet", value = "/save")
public class SaveFilmServlet extends HttpServlet {
    private final FilmService filmService = new FilmServiceImpl();
    private final DirectorService directorService = new DirectorServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DirectorDto> directorDtoList = directorService.getAll();

        req.setAttribute("directorDtoList", directorDtoList);
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/save.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmService.save(ServletUtil.mapFilm(req));
        resp.sendRedirect("films_manager");
    }

    @Override
    public void destroy() {
        this.filmService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
