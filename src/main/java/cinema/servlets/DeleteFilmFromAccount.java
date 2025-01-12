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
import java.util.Set;

@WebServlet(name = "deleteFilmFromAccount", value = "/delete_film_from_account")
public class DeleteFilmFromAccount extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    private FilmService filmService = new FilmServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDto accountDto = accountService.get(1);//toDo хардкод
        Set<FilmDto> desiredFilms = accountDto.getDesiredFilms();
        FilmDto filmDto = filmService.get(ServletUtil.getIntegerParam(request, "id"));
        desiredFilms.remove(filmDto);
        accountService.update(1, accountDto);
        response.sendRedirect("account");
    }



    @Override
    public void destroy() {
        this.filmService.closeDao();
        accountService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }




}
