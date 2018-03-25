package ru.kurce.kai.DAO;


import ru.kurce.kai.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO (Connection connection){
        this.connection = connection;
        createTable();
    }

    public User getUserById(long id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from users where uid=" + id);
            User user = null;
            while (resultSet.next()) {
                user = new User(
                        Long.parseLong(resultSet.getString("uid")),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            stmt.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByName (String name) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from users where username='" + name + "'");
            User user = null;
            while (resultSet.next()) {
                user = new User(
                        Long.parseLong(resultSet.getString("uid")),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            stmt.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist (User user) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from users where username='" + user.getName() + "' and password='" + user.getPassword() + "'");

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

    public List<User> getUsers () {
        try {
            Statement stmt = connection.createStatement();
            List<User> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery("select * from users");
            User user = null;
            while (resultSet.next()) {
                user = new User(
                        Long.parseLong(resultSet.getString("uid")),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                list.add(user);
            }
            stmt.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUser(User user) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            boolean b = stmt.execute("insert into users (username, password) values ('" + user.getName() + "', '" + user.getPassword() + "')");
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createTable () {
        //"create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))"
        //create table if not exists users (uid BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
        //                    " username VARCHAR(55) NOT NULL, UNIQUE" + " password VARCHAR(55) NOT NULL" +");
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users(uid BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                    " username VARCHAR(55) NOT NULL UNIQUE, password VARCHAR(55) NOT NULL);");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
