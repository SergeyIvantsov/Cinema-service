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

@WebServlet(name = "addWatchedFilmToAccount", value = "/add_watched")
public class AddWatchedFilmToAccount extends HttpServlet {
    private FilmService filmService = new FilmServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idWatchFilm = ServletUtil.getIntegerParam(request, "id");
        FilmDto filmDto = filmService.get(idWatchFilm);
        AccountDto accountDto = accountService.get(1);//toDo это хардкод на пока, потом убрать его
        accountDto.getWatchedFilms().add(filmDto);
        accountService.update(accountDto.getId(), accountDto);

        request.getSession().setAttribute("MessageWatched", "Фильм "+filmDto.getTitle()+ " добавлен в список просмотренных!");        //toDo вывести уведомление пользов-лю о том, что фильм добавлен в список
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
