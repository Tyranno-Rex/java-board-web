package org.example.functions.login;

import org.example.view.TerminalPrinter;
import java.sql.*;

import static org.example.functions.utils.utils.getString;

public class findPassword {

    public static void findPassword(String USER_FILE_PATH) throws Exception{
        TerminalPrinter.print("\n--------\n비밀번호 찾기\n--------\n");
        String id, email, nickname;


        TerminalPrinter.println("아이디: ");
        id = getString();
        TerminalPrinter.println("닉네임: ");
        nickname = getString();
        TerminalPrinter.println("이메일: ");
        email = getString();
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT * FROM User WHERE ID = '" + id + "' AND nickname = '" + nickname + "' AND email = '" + email + "'";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                TerminalPrinter.println("비밀번호는 " + resultSet.getString("password") + "입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}