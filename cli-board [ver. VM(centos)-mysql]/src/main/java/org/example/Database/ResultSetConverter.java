package org.example.Database;

import org.example.model.Comments;
import org.example.model.Post;
import org.example.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ResultSetConverter {
    public static List<?> convertResultSetToList(ResultSet resultSet, Class<?> clazz) throws SQLException {
        List<Object> resultList = new ArrayList<>();
        while (resultSet.next()) {
            try {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                if (obj instanceof Post) {
                    Post post = (Post) obj;
                    post.setPostId(resultSet.getLong("post_id"));
                    post.setUserId(resultSet.getLong("user_id"));
                    post.setNickname(resultSet.getString("nickname"));
                    post.setAnonymous(resultSet.getBoolean("anonymous"));
                    post.setTitle(resultSet.getString("title"));
                    post.setBody(resultSet.getString("content"));
                    post.setDate(resultSet.getString("date"));
                    post.setEditDate(resultSet.getString("modified_date"));
                    post.setViewCount(resultSet.getInt("views"));
                    post.setLikeCount(resultSet.getInt("likes"));
                    post.setDislikeCount(resultSet.getInt("dislikes"));
                    resultList.add(post);
                } else if (obj instanceof User) {
                    User user = (User) obj;
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setNickname(resultSet.getString("nickname"));
                    user.setID(resultSet.getString("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setUserStatus(resultSet.getString("user_status"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPreviousPassword(resultSet.getString("previous_password"));
                    user.setAgreement(resultSet.getBoolean("agreement"));
                    user.setBirthDate(resultSet.getString("birth_date"));
                    user.setJoinDate(resultSet.getString("join_date"));
                    resultList.add(user);
                } else if (obj instanceof Comments) {
                    Comments comment = (Comments) obj;
                    comment.setCommentId(resultSet.getLong("comment_id"));
                    comment.setPostId(resultSet.getLong("post_id"));
                    comment.setComment(resultSet.getString("comment"));
                    comment.setNickname(resultSet.getString("nickname"));
                    resultList.add(comment);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}