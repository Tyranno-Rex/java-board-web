package org.example.functions.post;

import org.example.model.User;
import org.example.view.TerminalPrinter;
import java.sql.*;
import java.util.*;
import static org.example.functions.utils.utils.getNumber;

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
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "select count(*) from Comment where post_id = " + index;
        int cnt = 0;
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultset = statement.executeQuery();
            if (connection != null) {
                while (resultset.next()) {
                    cnt = resultset.getInt(1);
                }
            }
            statement.close();
            resultset.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
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

            String url = "jdbc:mysql://192.168.22.1:3306/java";
            String username = "eunseong";
            String pw = "1234";
            String sql = "select * from Post where post_id = " + index;

            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultset = statement.executeQuery();
                if (connection != null) {
                    while (resultset.next()) {
                        TerminalPrinter.println("조회되었습니다.");
                        TerminalPrinter.println("---------------------------------");
                        TerminalPrinter.println("\n검색하신 게시물의 정보입니다.\n");
                        TerminalPrinter.println("---------------------------------");
                        assert resultset != null;
                        TerminalPrinter.println("제목: " + resultset.getString(4));
                        TerminalPrinter.println("---------------------------------");
                        TerminalPrinter.println("내용: " + resultset.getString("content"));
                        TerminalPrinter.println("작성일:" + resultset.getString("date"));
                        TerminalPrinter.println("수정일:" + resultset.getString("modified_date"));
                        TerminalPrinter.println("조회수:" + resultset.getString("views"));
                        if (resultset.getString(3).equals("anonymous")) {
                            TerminalPrinter.println("익명 게시물입니다.");
                        } else {
                            TerminalPrinter.println("작성자: " + resultset.getString("user_id"));
                        }
                        TerminalPrinter.println("추천수♡: " + resultset.getString("likes"));
                        TerminalPrinter.println("비추천수♥: " + resultset.getString("dislikes"));
                        TerminalPrinter.println("댓글: ");
                        getComment(resultset.getString("post_id"));

                        TerminalPrinter.println("---------------------------------");
                    }
                }
                statement.close();
                resultset.close();
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }


            // 상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로):
            TerminalPrinter.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로): ");

            try {
                int choice = getNumber();

                if (choice == 1) {
                    int commentCnt = getCommentCnt(index);
                    TerminalPrinter.println("댓글을 입력해주세요: ");
                    Scanner scanner = new Scanner(System.in);
                    String comment = scanner.nextLine();
                    String sql2 = "insert into Comment (comment_id, post_id, comment, nickname) values (?, ?, ?, ?)";
                    try (Connection connection = DriverManager.getConnection(url, username, pw);
                         PreparedStatement statement = connection.prepareStatement(sql2)) {
                        System.out.println(commentCnt + 1);
                        statement.setLong(1, commentCnt + 1);
                        statement.setLong(2, index);
                        statement.setString(3, comment);
                        statement.setString(4, user.getNickname());
                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            TerminalPrinter.println("댓글 등록에 성공했습니다.");
                        } else {
                            TerminalPrinter.println("댓글 등록에 실패했습니다.");
                        }
                        statement.close();
                    } catch (SQLException e) {
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                    }
                }

                if (choice == 2) {
                    String sql2 = "select likes from Post where post_id = ?";
                    try (Connection connection = DriverManager.getConnection(url, username, pw);
                         PreparedStatement statement = connection.prepareStatement(sql2)) {
                        statement.setInt(1, index);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            int likes = resultSet.getInt("likes");
                            likes++;
                            String sql3 = "update Post set likes = ? where post_id = ?";
                            try (PreparedStatement updateStatement = connection.prepareStatement(sql3)) {
                                updateStatement.setInt(1, likes);
                                updateStatement.setInt(2, index);
                                updateStatement.executeUpdate();
                            }
                        }
                        statement.close();
                        resultSet.close();
                    } catch (SQLException e) {
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                    }
                }
                if (choice == 3) {
                    TerminalPrinter.println("수정할 내용을 입력해주세요: ");
                    Scanner scanner = new Scanner(System.in);
                    String content = scanner.nextLine();
                    String sql2 = "update Post set content = '" + content + "' where post_id = " + index;
                    try (Connection connection = DriverManager.getConnection(url, username, pw);
                         PreparedStatement statement = connection.prepareStatement(sql2)) {
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                    }
                }
                if (choice == 4) {
                    String sql2 = "delete from Post where post_id = " + index;
                    try (Connection connection = DriverManager.getConnection(url, username, pw);
                         PreparedStatement statement = connection.prepareStatement(sql2)) {
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                    }
                }
                if (choice == 5) {
                    return 1;
                }

            } catch (InputMismatchException e) {
                TerminalPrinter.println("숫자를 입력해주세요.");
                return 0;
            }
            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int post_id = resultSet.getInt("post_id");
                    int views = resultSet.getInt("views");
                    views++;
                    String sql2 = "update Post set views = ? where post_id = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(sql2)) {
                        updateStatement.setInt(1, views);
                        updateStatement.setInt(2, post_id);
                        updateStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
