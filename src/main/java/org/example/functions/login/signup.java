package org.example.functions.login;

import org.example.Database.SQLController;
import org.example.view.TerminalPrinter;
import org.example.model.User;
import static org.example.functions.utils.utils.getString2;
import static org.example.model.User.getUserCount;
import static java.lang.System.exit;
import java.time.LocalDate;

public class signup {

    public static int availableId(String id) throws Exception {
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

        String sql = "SELECT * FROM User WHERE ID = '" + id + "'";
        SQLController SQLController = new SQLController();
        User user = SQLController.getUser(sql);
        if (user != null) {
            TerminalPrinter.println("이미 존재하는 아이디입니다.");
            return 1;
        }
        return 0;
    }

    public static int availableNickname(String nickname) throws Exception {
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

        String sql = "SELECT * FROM User WHERE nickname = '" + nickname + "'";
        SQLController SQLController = new SQLController();
        User user = SQLController.getUser(sql);
        if (user != null) {
            TerminalPrinter.println("이미 존재하는 닉네임입니다.");
            return 1;
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

    public static void signupInsert(String id, String nickname, boolean argreement, String email, String password, String birth) {
        String sql = "INSERT INTO User (user_id, ID, nickname, email, user_status, password, " +
                "previous_password, agreement, birth, join_date) VALUES (" +
                getUserCount() + 1 + ", '" + id + "', '" + nickname + "', '" + email + "', 'available', '" + password + "', '', " + argreement + ", '" + birth + "', '" + LocalDate.now().toString() + "')";

//                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        User user = new User(getUserCount() + 1, nickname, id, email, "available",
                password, "", argreement, birth, LocalDate.now().toString());
        SQLController SQLController = new SQLController();
        SQLController.executeSQL(sql);
//        SQLController.insertUser(sql, user);
    }

    public static void signup() throws Exception {

        String id, password, repassword, nickname, email, birth;
        boolean argreement = false;
        TerminalPrinter.println("------------------");
        TerminalPrinter.println("-----회원가입-----");
        TerminalPrinter.println("------------------");
        do {
            id = getString2("아이디를 입력해주세요(4자 이상, 20자 이하)(숫자와 영어 대소문자로만 구성): ");
        } while (availableId(id) != 0);
        do {
            nickname = getString2("닉네임을 입력해주세요(4자 이상, 20자 이하)(숫자와 영어 대소문자로만 구성): ");
        } while (availableNickname(nickname) != 0);
        do {
            password = getString2("비밀번호를 입력해주세요(8자 이상 16자 이하, 숫자, 영문자, 특수문자 포함): ", true);
            repassword = getString2("비밀번호를 다시 입력해주세요: ", true);
        } while (availablePassword(password, repassword) != 1);
        while (true) {
            email = getString2("이메일을 입력해주세요: ");
            if (email.contains("@") && email.contains(".")) {
                break;
            }
            TerminalPrinter.println("이메일 형식이 올바르지 않습니다.");
        }
        while (true) {
            birth = getString2("생년월일을 입력해주세요(YYYY-MM-DD): ");

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
            TerminalPrinter.println("동의하지 않을 경우 회원가입이 불가능합니다");
            String input = getString2("동의하시겠습니까? (Y/N): ", true);
            if (input.equals("Y")) {
                argreement = true;
                break;
            } else if (input.equals("N")) {
                TerminalPrinter.println("동의하지 않으시면 회원가입이 불가능합니다.");
                TerminalPrinter.println("프로그램을 종료합니다.");
                exit(0);
            }
        }

        signupInsert(id, nickname, argreement, email, password, birth);
    }
}
