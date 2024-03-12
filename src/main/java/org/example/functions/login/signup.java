package org.example.functions.login;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.StringTokenizer;


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

        File[] fileList = getFile.returnfilelist(USER_FILE_PATH);
        assert fileList != null;
        for (File file : fileList) {
            JSONObject json_user = FileToJson.FileToJson(file);
            if (json_user.get("id").equals(id)) {
                TerminalPrinter.println("이미 존재하는 아이디입니다.");
                return 1;
            }
        }
        return 0;
    }

    public static int availableNickname(String id, String USER_FILE_PATH) throws Exception {
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

        File[] fileList = getFile.returnfilelist(USER_FILE_PATH);
        assert fileList != null;
        for (File file : fileList) {
            JSONObject json_user = FileToJson.FileToJson(file);
            if (json_user.get("id").equals(id)) {
                TerminalPrinter.println("이미 존재하는 아이디입니다.");
                return 1;
            }
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

        String id, password, repassword, nickname, email;
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
            TerminalPrinter.println("닉네임을 입력해주세요(4자 이상, 20자 이하): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            nickname = st.nextToken();
            if (availableNickname(nickname, USER_FILE_PATH) == 0) {
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("password", password);
        jsonObject.put("nickname", nickname);
        jsonObject.put("email", email);
        FileWriter fileWriter = new FileWriter(USER_FILE_PATH + id + ".json");
        fileWriter.write(jsonObject.toString());
        fileWriter.flush();
        fileWriter.close();
        TerminalPrinter.println("회원가입이 완료되었습니다.");}
}
