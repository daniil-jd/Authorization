package ru.kurce.kai.DAO;

import ru.kurce.kai.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private final Connection connection;

    public ProductDAO (Connection connection){
        this.connection = connection;
        createTable();
    }

    public boolean exist (Product product) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from PRODUCTS where PRODUCT='" + product.getProduct() + "'");

            boolean b = false;
            while (resultSet.next()) {
                b = true;
                break;
            }
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getProducts () {
        try {
            Statement stmt = connection.createStatement();
            List<Product> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("select * from PRODUCTS");
            Product product = null;
            while (resultSet.next()) {
                product = new Product(
                        Long.parseLong(resultSet.getString("id")),
                        resultSet.getString("product"),
                        resultSet.getString("price"));
                list.add(product);
            }
            stmt.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertProduct(Product product) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            boolean b = stmt.execute("insert into PRODUCTS (PRODUCT, PRICE) values ('" + product.getProduct() + "', '" + product.getPrice() + "')");
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createTable () {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS products(id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                    " product VARCHAR(55) NOT NULL UNIQUE, price VARCHAR(20) NOT NULL);");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
