package cinema.servlets;

import cinema.service.FilmService;
import cinema.service.impl.FilmServiceImpl;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteFilmServlet", value = "/delete")
public class DeleteFilmServlet extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.filmService.delete(ServletUtil.getIntegerParam(request, "id"));
        response.sendRedirect("films_manager");
    }

    @Override
    public void destroy() {
    this.filmService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
}
