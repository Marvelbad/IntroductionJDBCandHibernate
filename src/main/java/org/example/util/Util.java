package org.example.util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

@UtilityClass
public class Util {
    private String url = "jdbc:mysql://localhost:3306/test";
    private String userName = "root";
    private String password = "root";

    public Connection getJDBCConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
