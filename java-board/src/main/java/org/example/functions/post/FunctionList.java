package org.example.functions.post;

import org.example.Database.SQLController;
import org.example.Database.getFile;
import org.example.model.Post;

import java.io.File;
import java.sql.*;
import java.util.List;

public class FunctionList {
    public static int list() {
        try {
            // Test
            // List<Post> postList = sqlController.getList(sql, Test); // 자바 리플렉션, 제너릭 이용해서 resultSet 변환 코드만 캡슐화

            String sql = "SELECT * FROM Post";
            SQLController sqlController = new SQLController();
            List<?> postList = sqlController.getList(sql, Post.class);
            for (Object post : postList) {
                // 적절한 형변환 후 메서드 호출
                if (post instanceof Post) {
                    Post typedPost = (Post) post;
                    System.out.println(typedPost.getPostId() + ". " + typedPost.getTitle());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 3;
    }
}
