package org.example.functions.login;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
import java.io.*;
import java.time.LocalDate;
import java.util.StringTokenizer;
import static java.lang.System.exit;
import static org.example.functions.utils.utils.GetBoolean;
import static org.example.model.User.getUserCount;

import java.sql.*;

public class signup {

    public static int availableId(String id, String USER_FILE_PATH) throws Exception {
        if (id.length() < 4) {
            TerminalPrinter.println("아이디는 4자 이상이어야 합니다.");
            return 1;
        }
        if (id.length() > 20) {
            TerminalPrinter.println("아이디는 20자 이하이어야 합니다.");
            return 1;
        }
        for (int i = 0; i < id.length(); i++) {
            if ((id.charAt(i) < 48 || id.charAt(i) > 57) // 0~9
                    && (id.charAt(i) < 65 || id.charAt(i) > 90) // A~Z
                    && (id.charAt(i) < 97 || id.charAt(i) > 122)) // a~z
            {
                TerminalPrinter.println("아이디는 숫자와 알파벳 대소문자로만 이루어져야합니다.");
                return 1;
            }
        }

        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT ID FROM User";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (connection != null) {
                while (resultSet.next()) {
                    if (resultSet.getString(1).equals(id)) {
                        TerminalPrinter.println("이미 존재하는 아이디입니다.");
                        return 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return 0;
    }

    public static int availableNickname(String nickname, String USER_FILE_PATH) throws Exception {
        if (nickname.length() < 4) {
            TerminalPrinter.println("아이디는 4자 이상이어야 합니다.");
            return 1;
        }
        if (nickname.length() > 20) {
            TerminalPrinter.println("아이디는 20자 이하이어야 합니다.");
            return 1;
        }
        for (int i = 0; i < nickname.length(); i++) {
            if ((nickname.charAt(i) < 48 || nickname.charAt(i) > 57) // 0~9
                    && (nickname.charAt(i) < 65 || nickname.charAt(i) > 90) // A~Z
                    && (nickname.charAt(i) < 97 || nickname.charAt(i) > 122)) // a~z
            {
                TerminalPrinter.println("아이디는 숫자와 알파벳 대소문자로만 이루어져야합니다.");
                return 1;
            }
        }

        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "SELECT nickname FROM User";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (connection != null) {
                while (resultSet.next()) {
                    if (resultSet.getString(1).equals(nickname)) {
                        TerminalPrinter.println("이미 존재하는 아이디입니다.");
                        return 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return 0;
    }

    public static int availablePassword(String password, String repassword) {
        if (!password.equals(repassword)) {
            TerminalPrinter.println("비밀번호가 일치하지 않습니다.");
            return 0;
        }
        if (!(password.length() >= 8 && password.length() <= 16)) {
            TerminalPrinter.println("비밀번호는 8자 이상 16자 이하이어야 합니다.");
            return 0;
        }
        if (!password.matches(".*[0-9].*")) {
            TerminalPrinter.println("비밀번호는 숫자를 포함해야합니다.");
            return 0;
        }
        if (!password.matches(".*[a-zA-Z].*")) {
            TerminalPrinter.println("비밀번호는 영문자를 포함해야합니다.");
            return 0;
        }
        if (!password.matches(".*[!@#$%^&*()].*")) {
            TerminalPrinter.println("비밀번호는 특수문자를 포함해야합니다.");
            return 0;
        }
        return 1;
    }

    public static void signup(String USER_FILE_PATH) throws Exception {

        String id, password, repassword, nickname, email, birth;
        Boolean argreement = false;


        TerminalPrinter.println("------------------");
        TerminalPrinter.println("-----회원가입-----");
        TerminalPrinter.println("------------------");
        while (true) {
            TerminalPrinter.println("아이디를 입력해주세요(4자 이상, 20자 이하)(숫자와 영어 대소문자로만 구성):");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            id = st.nextToken();
            if (availableId(id, USER_FILE_PATH) == 0) {
                break;
            }
        }
        while (true) {
            TerminalPrinter.println("닉네임을 입력해주세요(4자 이상, 20자 이하): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            nickname = st.nextToken();
            if (availableNickname(nickname, USER_FILE_PATH) == 0) {
                break;
            }
        }
        while (true) {
            TerminalPrinter.println("비밀번호를 입력해주세요:(8자 이상 16자 이하, 숫자, 영문자, 특수문자를 포함해야합니다.)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            password = st.nextToken();
            TerminalPrinter.println("비밀번호를 다시 입력해주세요: ");
            st = new StringTokenizer(br.readLine());
            repassword = st.nextToken();
            if (availablePassword(password, repassword) == 1) {
                break;
            }
        }
        while (true) {
            TerminalPrinter.println("이메일을 입력해주세요: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            email = st.nextToken();
            if (email.contains("@") && email.contains(".")) {
                break;
            }
            TerminalPrinter.println("이메일 형식이 올바르지 않습니다.");
        }

        while (true) {
            TerminalPrinter.println("생년월일을 입력해주세요(YYYY-MM-DD): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            birth = st.nextToken();
            if (birth.length() != 10) {
                TerminalPrinter.println("생년월일 형식이 올바르지 않습니다.");
                continue;
            }
            if (birth.charAt(4) != '-' || birth.charAt(7) != '-') {
                TerminalPrinter.println("생년월일 형식이 올바르지 않습니다.");
                continue;
            }
            if (birth.charAt(0) < '0' || birth.charAt(0) > '9' || birth.charAt(1) < '0' || birth.charAt(1) > '9' || birth.charAt(2) < '0' || birth.charAt(2) > '9' || birth.charAt(3) < '0' || birth.charAt(3) > '9') {
                TerminalPrinter.println("생년월일 형식이 올바르지 않습니다.");
                continue;
            }
            if (birth.charAt(5) < '0' || birth.charAt(5) > '1' || birth.charAt(6) < '0' || birth.charAt(6) > '9') {
                TerminalPrinter.println("생년월일 형식이 올바르지 않습니다.");
                continue;
            }
            if (birth.charAt(8) < '0' || birth.charAt(8) > '3' || birth.charAt(9) < '0' || birth.charAt(9) > '9') {
                TerminalPrinter.println("생년월일 형식이 올바르지 않습니다.");
                continue;
            }
            break;
        }

        while (true) {
            TerminalPrinter.println("해당 서비스를 이용하기 위해 사용자는 개인정보 수집에 동의해야합니다. (Y/N)");
            TerminalPrinter.println("이 서비스는 개인정보를 수집하게 되며, 이를 이용하여 서비스를 제공합니다.");
            TerminalPrinter.println("제 3자에게 개인정보를 제공하지 않으며, 해당 개인 정보는 온전히 서비스 제공을 위한 목적으로만 사용됩니다.");
            TerminalPrinter.println("동의하지 않을 경우 회원가입이 불가능합니다. (Y/N)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            if (input.equals("Y")) {
                argreement = true;
                break;
            } else if (input.equals("N")) {
                TerminalPrinter.println("동의하지 않으시면 회원가입이 불가능합니다.");
                TerminalPrinter.println("프로그램을 종료합니다.");
                exit(0);
            }
        }

        String url = "jdbc:mysql://192.168.22.1:3306/java";
        String username = "eunseong";
        String pw = "1234";
        String sql = "INSERT INTO User (user_id, ID, nickname, email, user_status, password, " +
                "previous_password, agreement, birth, join_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, pw);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (connection != null) {
                System.out.println("Connected to the database");
                statement.setLong(1, getUserCount(connection) + 1);
                statement.setString(2, id);
                statement.setString(3, nickname);
                statement.setString(4, email);
                statement.setString(5, "available");
                statement.setString(6, password);
                statement.setString(7, "");
                statement.setBoolean(8, argreement);
                statement.setString(9, birth);
                statement.setDate(10, Date.valueOf(LocalDate.now()));
                int number = statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

            TerminalPrinter.println("회원가입이 완료되었습니다.");
        }
    }
}
