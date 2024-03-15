package org.example.functions.login;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.model.User;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringTokenizer;

import static org.example.functions.utils.utils.getString;

public class login {
    public static User login(String USER_FILE_PATH) throws Exception{
        try {

            TerminalPrinter.print("아이디: ");
            String id = getString();
            TerminalPrinter.print("비밀번호: ");
            String password = getString();
            String url = "jdbc:mysql://192.168.22.1:3306/java";
            String username = "eunseong";
            String pw = "1234";
            String sql = "SELECT * FROM User WHERE ID = '" + id + "' AND password = '" + password + "'";

            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 java.sql.Statement statement = connection.createStatement();
                 java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    TerminalPrinter.println("로그인 성공");
                    return new User(resultSet.getLong("user_id"),
                            resultSet.getString("nickname"),
                            resultSet.getString("email"),
                            resultSet.getString("user_status"),
                            resultSet.getString("password"),
                            resultSet.getString("previous_Password"),
                            resultSet.getBoolean("agreement"),
                            resultSet.getString("birth"),
                            resultSet.getString("join_date"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            TerminalPrinter.println("로그인 실패");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}