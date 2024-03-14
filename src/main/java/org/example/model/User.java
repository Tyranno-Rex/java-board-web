package org.example.model;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.example.Main;

public class User {
    private long userId;
    private String nickname;
    private String email;
    private ArrayList<Long> personalPost;
    private String userStatus;
    private String password;
    private String previousPassword;
    private boolean agreement;
    private String birthDate;
    private String joinDate;

    public User(String nickname, String email, String userStatus, String password) {
        this.userId = makeUserId();
        this.nickname = nickname;
        this.email = email;
        this.personalPost = new ArrayList<>();
        this.userStatus = userStatus;
        this.password = password;
        this.previousPassword = "null";
        this.agreement = false;
        this.birthDate = "null";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        this.joinDate = date;
    }

    public long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Long> getPersonalPost() {
        return personalPost;
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


    public static int getUserCount(Connection connection) {
        int userCount = 0;
        String sql = "SELECT COUNT(*) FROM User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                    userCount = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCount;
    }

    public static long makeUserId() {
        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        try (Connection connection = DriverManager.getConnection(url, username, pw)){
            if (connection != null) {
                int userCount = getUserCount(connection);
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
