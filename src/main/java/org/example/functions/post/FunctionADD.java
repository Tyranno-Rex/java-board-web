package org.example.functions.post;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.Date;

import org.example.model.User;
import org.example.model.Post;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
public class FunctionADD {
    public static void sendsqlusePost(Post post) {
        long postId = post.getPostId();
        long userId = post.getUserId();
        boolean isAnonymous = post.isAnonymous();
        String title = post.getTitle();
        String body = post.getBody();
        String date = post.getDate();
        String editDate = post.getEditDate();
        int viewCount = post.getViewCount();
        int likeCount = post.getLikeCount();
        int dislikeCount = post.getDislikeCount();

        String sql = "INSERT INTO Post (post_id, user_id, anonymous, title, content, date, modified_date, views, likes, dislikes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (connection != null) {
                System.out.println("Connected to the database");
                // PreparedStatement를 사용하여 값 설정
                statement.setLong(1, postId);
                statement.setLong(2, userId);
                statement.setBoolean(3, isAnonymous);
                statement.setString(4, title);
                statement.setString(5, body);
                statement.setString(6, date);
                statement.setString(7, editDate);
                statement.setInt(8, viewCount);
                statement.setInt(9, likeCount);
                statement.setInt(10, dislikeCount);
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

    public static String gettitle(String FILE_PATH) {
        File file = new File(FILE_PATH);
        File[] files = file.listFiles();
        TerminalPrinter.print("게시물의 제목을 입력해주세요: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        if (title == null || title.equals("")) {
            TerminalPrinter.println("제목을 입력해주세요");
            gettitle(FILE_PATH);
        }
        for (File f : files) {
            if (f.getName().equals(title + ".json")) {
                TerminalPrinter.println("이미 존재하는 제목입니다");
                gettitle(FILE_PATH);
            }
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
    public static int add(String FILE_PATH, User user) {
        try  {
            String title = gettitle(FILE_PATH);
            String body = getbody();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(new Date());
            Post post = new Post(1, user.getUserId(), false, title, body, date, date, 0, 0, 0);
            sendsqlusePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 2;
    }
}