package cinema.servlets;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.service.impl.AccountServiceImpl;
import cinema.service.impl.FilmServiceImpl;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteFilmFromAccount", value = "/delete_film_from_account")
public class DeleteFilmFromAccount extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idFilm = ServletUtil.getIntegerParam(request, "id");
        accountService.deleteFilmFromAccount(idFilm);
        response.sendRedirect("account");
    }


    @Override
    public void destroy() {
        this.accountService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }


}
