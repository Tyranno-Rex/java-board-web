package org.example.functions.post;

import org.example.view.TerminalPrinter;
import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;

public class FunctionSearch {

    public static int searchByBody() throws IOException {
        TerminalPrinter.print("검색할 내용을 입력해주세요: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String body = st.nextToken();

        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT * FROM Post WHERE content LIKE '%" + body + "%'";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (connection != null) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    TerminalPrinter.println(resultSet.getString(4) + " " + resultSet.getString(5));
                }
                TerminalPrinter.println("검색되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return 1;
    }

    public static int searchByTitle() throws IOException {
        TerminalPrinter.print("검색할 제목을 입력해주세요: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String title = st.nextToken();
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT * FROM Post WHERE title LIKE '%" + title + "%'";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (connection != null) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    TerminalPrinter.println(resultSet.getString(4) + " " + resultSet.getString(5));
                }
                TerminalPrinter.println("검색되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return 1;
    }
    public static int search() throws IOException {
        TerminalPrinter.println("찾고자하는 방식을 선택해주세요");
        TerminalPrinter.println("1. 제목으로 검색");
        TerminalPrinter.println("2. 내용으로 검색");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(br.readLine());
        if (choice == 1) {
            searchByTitle();
        } else if (choice == 2) {
            searchByBody();
        } else {
            TerminalPrinter.println("잘못된 입력입니다.");
            return 0;
        }
        return 1;
    }
}
