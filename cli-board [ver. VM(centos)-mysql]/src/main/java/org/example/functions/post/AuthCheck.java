package org.example.functions.post;

import java.awt.image.ImageProducer;

import org.example.view.TerminalPrinter;
import java.sql.*;

public class AuthCheck {
    public static boolean check(String nickname, int post_id) {
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT * FROM Post WHERE post_id = " + post_id;
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (connection != null) {
                if (!resultSet.next()) {
                    TerminalPrinter.println("존재하지 않는 게시물입니다.");
                    return false;
                } else {
                    if (resultSet.getString(2).equals("anonymous") || !nickname.equals(resultSet.getString(3))) {
                        TerminalPrinter.println("권한이 없습니다.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return true;
    }
}
