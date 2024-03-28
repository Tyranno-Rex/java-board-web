package org.example.Database;

import org.example.model.Comments;
import org.example.model.Post;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<?> getList(String sql, Class<?> clazz) {
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sql)) {
            return ResultSetConverter.convertResultSetToList(resultSet, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}



