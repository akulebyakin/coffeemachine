import dao.impl.ConnectionFactory;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public static void main(String[] args) {
        String query = "select * from ingredients";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(id + ". name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();

            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }
}
