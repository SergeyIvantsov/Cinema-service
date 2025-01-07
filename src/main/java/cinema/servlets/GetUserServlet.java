package cinema.servlets;

import cinema.dto.FilmDto;
import cinema.dto.UserDto;
import cinema.service.FilmService;
import cinema.service.UserService;
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

//@WebServlet(name = "getUserServlet", value = "/account1")
public class GetUserServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final UserDto userDto = this.userService.get(1);//toDo убрать хардкод айдишника
//
//        req.setAttribute("user", userDto);
//
//        RequestDispatcher requestDispatcher = getServletContext()
//                .getRequestDispatcher("/account1.jsp");
//        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        this.userService.closeDao();
        HibernateUtil.close();
        super.destroy();
    }


}
