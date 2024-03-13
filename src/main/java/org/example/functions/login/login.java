package org.example.functions.login;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.model.User;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class login {
    public static User login(String USER_FILE_PATH) throws Exception{

        File[] fileList = getFile.returnfilelist(USER_FILE_PATH);
        assert fileList != null;

        TerminalPrinter.print("아이디: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String id = st.nextToken();
        TerminalPrinter.print("비밀번호: ");
        st = new StringTokenizer(br.readLine());
        String password = st.nextToken();

        for (File file : fileList) {
            JSONObject user_json = FileToJson.FileToJson(file);
            if (user_json.get("id").equals(id) && user_json.get("password").equals(password)) {
                User user = new User((String) user_json.get("id"),
                            (String) user_json.get("password"),
                            (String) user_json.get("nickname"),
                            (String) user_json.get("email"));
                return user;
            }
        }
        TerminalPrinter.println("로그인 실패");
        return null;
    }
}