package ru.kurce.kai.services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.h2.jdbcx.JdbcDataSource;
import ru.kurce.kai.DAO.ProductDAO;
import ru.kurce.kai.DAO.UserDAO;
import ru.kurce.kai.models.Product;
import ru.kurce.kai.models.User;


public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:~/test_h2";
            String name = "sa";
            String pass = "";

            JdbcDataSource ds = new JdbcDataSource();

            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //work with user
    public User getUser (long id){
        return (new UserDAO(connection).getUserById(id));
    }
    public User getByName (String name){
        return (new UserDAO(connection).getUserByName(name));
    }
    public List<User> getUsers (){
        return (new UserDAO(connection).getUsers());
    }
    public boolean addUser (User user) {
        return (new UserDAO(connection).insertUser(user));
    }
    public boolean userExist (User user) {
        return (new UserDAO(connection).exist(user));
    }

    //work with product
    public boolean productExist (Product product) {
        return (new ProductDAO(connection).exist(product));
    }
    public List<Product> getProducts () {
        return (new ProductDAO(connection).getProducts());
    }
    public boolean addProduct (Product product) {
        return (new ProductDAO(connection).insertProduct(product));
    }


    public void close () {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
