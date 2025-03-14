package cinema.servlets;

import cinema.dto.AccountDto;
import cinema.dto.FilmDto;
import cinema.dto.UserDto;
import cinema.service.AccountService;
import cinema.service.FilmService;
import cinema.service.UserService;
import cinema.service.impl.AccountServiceImpl;
import cinema.service.impl.FilmServiceImpl;
import cinema.service.impl.UserServiceImpl;
import cinema.utils.Constants;
import cinema.utils.HibernateUtil;
import cinema.utils.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "getAccountServlet", value = "/account")
public class GetAccountServlet extends HttpServlet {
    private final AccountService accountService = new AccountServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer accountId = ServletUtil.getIntegerParam(req,"id");
        AccountDto accountDto = this.accountService.get(Constants.ACCOUNT_ID);
        Integer userId = accountDto.getUserDto().getId();
        Set<FilmDto> desiredFilms = accountDto.getDesiredFilms();
        Set<FilmDto> watchedFilms = accountDto.getWatchedFilms();
        UserDto userDto = this.userService.get(userId);

        req.setAttribute("account", accountDto);
        req.setAttribute("user", userDto);
        req.setAttribute("films", desiredFilms);
        req.setAttribute("watched_films", watchedFilms);

        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/account.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.accountService.closeDao();
        this.userService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }

}
