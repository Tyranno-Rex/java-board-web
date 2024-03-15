package org.example.functions.post;

import org.example.Database.getFile;

import java.io.File;
import java.sql.*;

public class FunctionList {
    public static int list() {
        try {
            String url = "jdbc:mysql://192.168.22.1:3306/java";
            String username = "eunseong";
            String pw = "1234";
            String sql = "SELECT * FROM Post";

            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (connection != null) {
                    int i = 1;
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) + ". " + resultSet.getString(5));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 3;
    }
}
