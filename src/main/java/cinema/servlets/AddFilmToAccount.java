package cinema.servlets;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.service.impl.AccountServiceImpl;
import cinema.service.impl.FilmServiceImpl;
import cinema.utils.Constants;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addFilmToAccount", value = "/add")
public class AddFilmToAccount extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idFilm = ServletUtil.getIntegerParam(request, "id");

        accountService.addFilmToDesireList(Constants.ACCOUNT_ID,idFilm);


        request.getSession().setAttribute("MessageDesired", "Фильм "+filmService.get(idFilm).getTitle()+ " добавлен в список желаемых!");
        response.sendRedirect("films_manager");
   }

    @Override
    public void destroy() {
        this.filmService.closeDao();
        this.accountService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }

}
