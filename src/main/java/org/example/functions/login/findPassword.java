package org.example.functions.login;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class findPassword {

    public static void findPassword(String USER_FILE_PATH) throws Exception{
        TerminalPrinter.print("\n--------\n비밀번호 찾기\n--------\n");
        String id, email, nickname;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TerminalPrinter.println("아이디: ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        id = st.nextToken();
        TerminalPrinter.println("닉네임: ");
        st = new StringTokenizer(br.readLine());
        nickname = st.nextToken();
        TerminalPrinter.println("이메일: ");
        st = new StringTokenizer(br.readLine());
        email = st.nextToken();

        File[] fileList = getFile.returnfilelist(USER_FILE_PATH);
        assert fileList != null;
        boolean flag = false;
        for (File file : fileList) {
            JSONObject user_json = FileToJson.FileToJson(file);

            if (user_json.get("id").equals(id)
                    && user_json.get("nickname").equals(nickname)
                    && user_json.get("email").equals(email)) {
                TerminalPrinter.println("비밀번호는 " + user_json.get("password") + "입니다.");
                flag = true;
            }
        }
        if (!flag) {
            TerminalPrinter.println("일치하는 정보가 없습니다.");
        }
    }
}