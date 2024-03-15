package org.example.functions.post;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

import org.example.model.User;
import org.example.model.Post;
import org.example.view.TerminalPrinter;
public class FunctionADD {

    public static int getPostId() {
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT * FROM Post";
        int cnt = 0;
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (connection != null) {
                while (resultSet.next()) {
                    cnt = Integer.parseInt(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return cnt + 1;
    }
    public static void sendsqlusePost(Post post) {
        long postId = post.getPostId();
        long userId = post.getUserId();
        String nickname =post.getNickname();
        boolean isAnonymous = post.isAnonymous();
        String title = post.getTitle();
        String body = post.getBody();
        String date = post.getDate();
        String editDate = post.getEditDate();
        int viewCount = post.getViewCount();
        int likeCount = post.getLikeCount();
        int dislikeCount = post.getDislikeCount();


        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "INSERT INTO Post (post_id, user_id, nickname, anonymous, title, " +
                "content, date, modified_date, views, likes, dislikes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (connection != null) {
                System.out.println("Connected to the database");
                // PreparedStatement를 사용하여 값 설정
                statement.setLong(1, postId);
                statement.setLong(2, userId);
                statement.setString(3, nickname);
                statement.setBoolean(4, isAnonymous);
                statement.setString(5, title);
                statement.setString(6, body);
                statement.setString(7, date);
                statement.setString(8, editDate);
                statement.setInt(9, viewCount);
                statement.setInt(10, likeCount);
                statement.setInt(11, dislikeCount);
                // SQL 쿼리 실행
                int number = statement.executeUpdate();
                System.out.println("Number of records inserted: " + number);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        TerminalPrinter.println(sql);
    }
    public static String gettitle() {
        String title = "";
        try {
            TerminalPrinter.print("게시물의 제목을 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            title = scanner.nextLine();
            if (title == null || title.equals("")) {
                TerminalPrinter.println("제목을 입력해주세요");
                gettitle();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return title;
    }
    public static String getbody() {
        TerminalPrinter.print("게시물의 내용을 입력해주세요: ");
        Scanner scanner = new Scanner(System.in);
        String body = scanner.nextLine();
        if (body == null || body.equals("")) {
            TerminalPrinter.println("내용을 입력해주세요");
            getbody();
        }
        return body;
    }
    public static int add( User user) {
        try  {
            String title = gettitle();
            String body = getbody();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(new Date());
            Post post = new Post(getPostId(), user.getUserId(), user.getNickname(),  false, title, body, date, date, 0, 0, 0);
            sendsqlusePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 2;
    }
}