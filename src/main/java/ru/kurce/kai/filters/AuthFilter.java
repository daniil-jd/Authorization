package ru.kurce.kai.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/home")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            //response.sendRedirect(request.getContextPath() + "/login");
            servletRequest.getServletContext().getRequestDispatcher(request.getContextPath() + "/login").forward(request, response);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
