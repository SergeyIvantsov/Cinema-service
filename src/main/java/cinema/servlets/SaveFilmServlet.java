package cinema.servlets;


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

@WebServlet(name = "saveFilmServlet", value = "/save")
public class SaveFilmServlet extends HttpServlet {
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
