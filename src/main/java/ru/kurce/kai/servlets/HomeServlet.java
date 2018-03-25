package ru.kurce.kai.servlets;

import ru.kurce.kai.models.Product;
import ru.kurce.kai.services.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private DBService dbService;

    @Override
    public void init() throws ServletException {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = dbService.getProducts();
        request.setAttribute("productFromServer", list);
        request.getServletContext().getRequestDispatcher("/jsp/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prod = request.getParameter("product");
        String price = request.getParameter("price");
        Product product = new Product(prod, price);
        if (!dbService.productExist(product))
            dbService.addProduct(product);
        doGet(request, response);
    }

    @Override
    public void destroy() {
        dbService.close();
    }
}
