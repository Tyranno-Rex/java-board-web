package org.example.model;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.example.Main;

public class User {
    private Long userId;
    private String nickname;
    private String ID;
    private String email;
    private String userStatus;
    private String password;
    private String previousPassword;
    private boolean agreement;
    private String birthDate;
    private String joinDate;

    public User(Long userId, String nickname, String ID, String email,
                String userStatus, String password, String previousPassword,
                boolean agreement, String birthDate, String joinDate) {
        this.userId = userId;
        this.nickname = nickname;
        this.ID = ID;
        this.email = email;
        this.userStatus = userStatus;
        this.password = password;
        this.previousPassword = previousPassword;
        this.agreement = agreement;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getID() {return ID;}

    public String getEmail() {
        return email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getPassword() {
        return password;
    }

    public String getPreviousPassword() {
        return previousPassword;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }


    public static Long getUserCount() {
        Long userCount = 0L;
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT COUNT(*) FROM User";
        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                userCount = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    System.out.println(resultSet.getInt(1));
//                    userCount = resultSet.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return userCount;
    }

    public static long makeUserId() {
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        try (Connection connection = DriverManager.getConnection(url, username, pw)){
            if (connection != null) {
                long userCount = getUserCount();
                System.out.println("Connected to the database");
                return userCount;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            makeUserId();
        }
        return 0;
    }
}
