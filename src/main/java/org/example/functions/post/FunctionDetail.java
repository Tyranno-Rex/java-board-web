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
        String sql = "SELECT * FROM Comment where post_id = " + index;
        SQLController sqlController = new SQLController();
        List<?> commentsList = sqlController.getList(sql);
        for (Object comment : commentsList) {
            if (comment instanceof Comments) {
                Comments typedComment = (Comments) comment;
                TerminalPrinter.println(typedComment.getComment());
            }
        }
    }

    public static int getCommentCnt(int index) {
        int cnt = 0;
        String sql = "SELECT count(*) From Comment";
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

            String sql = "SELECT * FROM Post where post_id = " + index;
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
            TerminalPrinter.println("상세보기 기능을 선택해주세요(/ 1. 댓글 등록 / 2. 추천 / 3. 비추천 / 4. 수정 / 5. 삭제 / 6. 목록으로 /): ");

            try {
                int choice = getNumber();

                if (choice == 1) {
                    int commentCnt = getCommentCnt(index);
                    String comment = getString2("댓글을 입력해주세요: ");
                    String sql2 = "insert into Comment (comment_id, post_id, comment, nickname) values (" + (commentCnt + 1) + ", " + index + ", '" + comment + "', '" + user.getNickname() + "')";
                    sqlController.executeSQL(sql2);
                }
                if (choice == 2) {
                    String sql3 = "SELECT count(*) FROM LikeAndDislike where post_id = " + index + " and user_id = '" + user.getUserId() + "'";
                    int cnt = sqlController.GetSQLInt(sql3);
                    if (cnt > 0) {
                        TerminalPrinter.println("이미 추천/비추천을 하셨습니다.");
                        return 0;
                    } else {
                        String sql2 = "select likes from Post where post_id = " + index;
                        int likes = sqlController.GetSQLInt(sql2);
                        ++likes;
                        String sql4 = "update Post set likes = " + likes + " where post_id = " + index;
                        String sql5 = "insert into LikeAndDislike (post_id, user_id) values (" + index + ", '" + user.getUserId() + "')";
                        sqlController.executeSQL(sql4);
                        sqlController.executeSQL(sql5);
                    }
                }
                if (choice == 3) {
                    String sql3 = "select count(*) from LikeAndDislike where post_id = " + index + " and user_id = '" + user.getUserId() + "'";
                    int cnt = sqlController.GetSQLInt(sql3);
                    if (cnt > 0) {
                        TerminalPrinter.println("이미 추천/비추천을 하셨습니다.");
                        return 0;
                    } else {
                        String sql2 = "select dislikes from Post where post_id = " + index;
                        int dislikes = sqlController.GetSQLInt(sql2);
                        ++dislikes;
                        String sql4 = "update Post set dislikes = " + dislikes + " where post_id = " + index;
                        String sql5 = "insert into LikeAndDislike (post_id, user_id) values (" + index + ", '" + user.getUserId() + "')";
                        sqlController.executeSQL(sql4);
                        sqlController.executeSQL(sql5);
                    }
                }
                if (choice == 4) {
                     if (AuthCheck.check(user.getNickname(), index)) {
                        String content = getString2("수정할 내용을 입력해주세요: ");
                        String sql2 = "update Post set content = '" + content + "' where post_id = " + index;
                        sqlController.executeSQL(sql2);
                     }
                     else TerminalPrinter.println("권한이 없습니다.");
                }
                if (choice == 5) {
                    if (AuthCheck.check(user.getNickname(), index)) {
                        String sql2 = "delete from Post where post_id = " + index;
                        sqlController.executeSQL(sql2);
                    }
                    else TerminalPrinter.println("권한이 없습니다.");
                }
                if (choice == 6 || choice > 6 || choice < 1) {
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
