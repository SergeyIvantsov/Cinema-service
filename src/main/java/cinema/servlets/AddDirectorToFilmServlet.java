package cinema.servlets;


import cinema.dto.DirectorDto;
import cinema.service.DirectorService;
import cinema.service.impl.DirectorServiceImpl;
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

@WebServlet(name = "addDirectorToFilm", value = "/addDirector")
public class AddDirectorToFilmServlet extends HttpServlet {

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

        directorService.save(ServletUtil.getDirectorFromRequest(req));
        resp.sendRedirect("films_manager");
    }

    @Override
    public void destroy() {
        this.directorService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }
    
    
}
