package cinema.servlets;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.dto.UserDto;
import cinema.entity.Film;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.service.UserService;
import cinema.service.impl.AccountServiceImpl;
import cinema.service.impl.FilmServiceImpl;
import cinema.service.impl.UserServiceImpl;
import cinema.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getAccountServlet", value = "/account")
public class GetAccountServlet extends HttpServlet {
    private final AccountService accountService = new AccountServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AccountDto accountDto = this.accountService.get(1);//toDo убрать хардкод айдишника
        Integer userId = accountDto.getUserId();
        List<FilmDto> desiredFilms = accountDto.getDesiredFilms();
        final UserDto userDto = this.userService.get(userId);

        req.setAttribute("account", accountDto);
        req.setAttribute("user", userDto);
        //обратиться к сервису и получ. список фильмов, которые неоходимо
        req.setAttribute("films", desiredFilms);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/account.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.accountService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }

}
