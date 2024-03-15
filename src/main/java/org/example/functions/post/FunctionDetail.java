package org.example.functions.post;

import org.example.Database.SQLController;
import org.example.model.Post;
import org.example.model.User;
import org.example.view.TerminalPrinter;

import java.sql.*;
import java.util.*;
import static org.example.functions.utils.utils.getNumber;
import static org.example.functions.utils.utils.getString2;
import org.example.model.Comments;

public class FunctionDetail {


    public static void getComment(String index) {
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "select count(*) from Comment where post_id = " + index;

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();
            if (connection != null) {
                while (resultset.next()) {
                    int cnt = resultset.getInt(1);
                    if (cnt == 0) {
                        TerminalPrinter.println("댓글이 없습니다.");
                    } else {
                        String sql2 = "select * from Comment where post_id = " + index;
                        try (Connection connection2 = DriverManager.getConnection(url, username, pw);
                             PreparedStatement statement2 = connection2.prepareStatement(sql2)) {
                            ResultSet resultset2 = statement2.executeQuery();
                            if (connection2 != null) {
                                while (resultset2.next()) {
                                    TerminalPrinter.println(resultset2.getString("comment"));
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Connection Failed! Check output console");
                            e.printStackTrace();
                        }
                    }
                }
                statement.close();
                resultset.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public static int getCommentCnt(int index) {
        int cnt = 0;
        String sql = "select count(*) from Comment";
        SQLController sqlController = new SQLController();
        try {
            cnt = sqlController.GetSQLInt(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    public static int detail(User user) {

        int index;
        try {
            FunctionList list = new FunctionList();
            list.list();
            TerminalPrinter.print("상세 조회할 게시물의 번호를 입력해주세요: ");
            try {
                index = getNumber();
            } catch (InputMismatchException e) {
                TerminalPrinter.println("숫자를 입력해주세요.");
                return 0;
            }

            String sql = "select * from Post where post_id = " + index;
            SQLController sqlController = new SQLController();
            Post post = sqlController.getPost(sql);
            if (post == null) {
                return 0;
            }
            TerminalPrinter.println("조회되었습니다.");
            TerminalPrinter.println("---------------------------------");
            TerminalPrinter.println("\n검색하신 게시물의 정보입니다.\n");
            TerminalPrinter.println("---------------------------------");
            TerminalPrinter.println("제목: " + post.getTitle());
            TerminalPrinter.println("---------------------------------");
            TerminalPrinter.println("내용: " + post.getBody());
            TerminalPrinter.println("작성일:" + post.getDate());
            TerminalPrinter.println("수정일:" + post.getEditDate());
            TerminalPrinter.println("조회수:" + post.getViewCount());
            if (post.isAnonymous()) {
                TerminalPrinter.println("익명 게시물입니다.");
            } else {
                TerminalPrinter.println("작성자: " + post.getNickname());
            }
            TerminalPrinter.println("추천수♡: " + post.getLikeCount());
            TerminalPrinter.println("비추천수♥: " + post.getDislikeCount());
            TerminalPrinter.println("댓글: ");
            getComment(String.valueOf(post.getPostId()));
            TerminalPrinter.println("---------------------------------");

            // 상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로):
            TerminalPrinter.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로): ");

            try {
                int choice = getNumber();

                if (choice == 1) {
                    int commentCnt = getCommentCnt(index);
                    String comment = getString2("댓글을 입력해주세요: ");
                    String sql2 = "insert into Comment (comment_id, post_id, comment, nickname) values (" + (commentCnt + 1) + ", " + index + ", '" + comment + "', '" + user.getNickname() + "')";
                    sqlController.executeSQL(sql2);
                }
                if (choice == 2) {
                    String sql2 = "select likes from Post where post_id = " + index;
                    int likes = sqlController.GetSQLInt(sql2);
                    ++likes;
//                    String sql3 = "Select * from LikeAndDislike where post_id = " + index + " and user_id = '" + user.getUserId() + "'";
//                    ResultSet resultSet = sqlController.GetSQL(sql3);

                    String sql3 = "update Post set likes = " + likes + " where post_id = " + index;
                    sqlController.executeSQL(sql3);

                }
                if (choice == 3) {
                    String content = getString2("수정할 내용을 입력해주세요: ");
                    String sql2 = "update Post set content = '" + content + "' where post_id = " + index;
                    sqlController.executeSQL(sql2);
                }
                if (choice == 4) {
                    String sql2 = "delete from Post where post_id = " + index;
                    sqlController.executeSQL(sql2);
                }
                if (choice == 5) {
                    return 1;
                }

            } catch (InputMismatchException e) {
                TerminalPrinter.println("숫자를 입력해주세요.");
                return 0;
            }
            {
                String sql2 = "select views from Post where post_id = " + index;
                int views = sqlController.GetSQLInt(sql2);
                ++views;
                String sql3 = "update Post set views = " + views + " where post_id = " + index;
                sqlController.executeSQL(sql3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
