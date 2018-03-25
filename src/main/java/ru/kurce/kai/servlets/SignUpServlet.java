package ru.kurce.kai.servlets;

import ru.kurce.kai.models.User;
import ru.kurce.kai.services.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = dbService.getUsers();
        request.setAttribute("usersFromServer", users);
        request.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        if (!dbService.userExist(user))
            dbService.addUser(user);
        doGet(request, response);
    }

    @Override
    public void destroy() {
        dbService.close();
    }
}
