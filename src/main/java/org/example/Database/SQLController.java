package org.example.Database;

import org.example.model.Comments;
import org.example.model.Post;
import org.example.model.User;

import java.sql.*;

public class SQLController {
    String url = "jdbc:mysql://192.168.22.1:3306/java";
    String username = "eunseong";
    String pw = "1234";

    public User getUser(String sql) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return new User(resultSet.getLong("user_id"),
                        resultSet.getString("nickname"),
                        resultSet.getString("ID"),
                        resultSet.getString("email"),
                        resultSet.getString("user_status"),
                        resultSet.getString("password"),
                        resultSet.getString("previous_Password"),
                        resultSet.getBoolean("agreement"),
                        resultSet.getString("birth"),
                        resultSet.getString("join_date"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int insertUser(String sql, User user) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getUserId());
            statement.setString(2, user.getID());
            statement.setString(3, user.getNickname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUserStatus());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getPreviousPassword());
            statement.setBoolean(8, user.isAgreement());
            statement.setString(9, user.getBirthDate());
            statement.setString(10, user.getJoinDate());
            statement.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public Post getPost(String sql) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return new Post(resultSet.getLong("post_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("nickname"),
                        resultSet.getBoolean("anonymous"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getString("date"),
                        resultSet.getString("modified_date"),
                        resultSet.getInt("views"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Post insertPost(String sql, Post post) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, post.getPostId());
            statement.setLong(2, post.getUserId());
            statement.setString(3, post.getNickname());
            statement.setBoolean(4, post.isAnonymous());
            statement.setString(5, post.getTitle());
            statement.setString(6, post.getBody());
            statement.setString(7, post.getDate());
            statement.setString(8, post.getEditDate());
            statement.setInt(9, post.getViewCount());
            statement.setInt(10, post.getLikeCount());
            statement.setInt(11, post.getDislikeCount());
            statement.executeUpdate();
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Comments getComment(String sql) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return new Comments(resultSet.getLong("comment_id"),
                        resultSet.getLong("post_id"),
                        resultSet.getString("comment"),
                        resultSet.getString("nickname"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Comments insertComment(String sql, Comments comments) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, comments.getCommentId());
            statement.setLong(2, comments.getPostId());
            statement.setString(3, comments.getComment());
            statement.setString(4, comments.getNickname());
            statement.executeUpdate();
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void executeSQL(String sql) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int GetSQLInt(String sql) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}



