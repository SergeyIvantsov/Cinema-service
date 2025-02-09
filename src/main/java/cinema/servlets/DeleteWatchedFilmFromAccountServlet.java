package cinema.servlets;

import cinema.service.AccountService;
import cinema.service.impl.AccountServiceImpl;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteWatchedFilmFromAccount", value = "/delete_watched_film")
public class DeleteWatchedFilmFromAccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idWatchFilm = ServletUtil.getIntegerParam(request, "id");
        accountService.deleteWatchedFilmFromAccount(idWatchFilm);
        response.sendRedirect("account");
    }


    @Override
    public void destroy() {
        this.accountService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }



}
