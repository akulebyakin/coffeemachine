package dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/coffeemachine" +
            "?useUnicode=true" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
//    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
