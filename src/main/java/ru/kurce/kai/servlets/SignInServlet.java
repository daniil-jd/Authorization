package ru.kurce.kai.servlets;

import ru.kurce.kai.models.User;
import ru.kurce.kai.services.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class SignInServlet extends HttpServlet {
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (dbService.userExist(new User(name, password))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", name);
            Cookie nameCookie = new Cookie("name", name);
            Cookie passCookie = new Cookie("password", password);
            response.addCookie(nameCookie);
            response.addCookie(passCookie);

            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        dbService.close();
    }
}
