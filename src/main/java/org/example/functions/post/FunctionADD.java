package org.example.functions.post;

import java.sql.*;

import org.example.Database.SQLController;
import org.example.model.User;
import org.example.model.Post;
import org.example.view.TerminalPrinter;
import static org.example.functions.utils.utils.getString2;
import static org.example.functions.utils.utils.GetDate;

public class FunctionADD {
    public static int getPostId() {
        String sql = "SELECT COUNT(*) FROM Post";
        int cnt = 0;
        SQLController sqlController = new SQLController();
        try {
            cnt = sqlController.GetSQLInt(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cnt + 1;
    }
    public static void sendsqlusePost(Post post) {
        String sql = "INSERT INTO Post (post_id, user_id, nickname, anonymous, title, " +
                "content, date, modified_date, views, likes, dislikes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLController sqlController = new SQLController();
        sqlController.insertPost(sql, new Post(post.getPostId(), post.getUserId(), post.getNickname(),
                                        post.isAnonymous(), post.getTitle(), post.getBody(), post.getDate(),
                                        post.getEditDate(), post.getViewCount(), post.getLikeCount(),
                                        post.getDislikeCount()));
        TerminalPrinter.println(sql);
    }
    public static int add( User user) {
        try  {
            String title = getString2("게시물의 제목을 입력해주세요: ");
            String body = getString2("게시물의 내용을 입력해주세요: ");
            Post post = new Post(getPostId(), user.getUserId(), user.getNickname(),  false,
                    title, body, GetDate(), GetDate(), 0, 0, 0);
            sendsqlusePost(post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 2;
    }
}
