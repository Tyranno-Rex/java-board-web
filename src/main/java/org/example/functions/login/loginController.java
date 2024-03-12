package org.example.functions.login;

import org.example.view.TerminalPrinter;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;


public class loginController {
    private static String USER_FILE_PATH =  System.getProperty("user.dir") + "\\src\\db\\User\\";
    public static void loginController() throws Exception {
        while (true) {
            TerminalPrinter.println("1. 로그인 2. 회원가입 3. 비밀번호 찾기");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num == 1) {
                if (login.login(USER_FILE_PATH) == 1)
                    break;
            } else if (num == 2) {
                signup.signup(USER_FILE_PATH);
            } else if (num == 3) {
                findPassword.findPassword(USER_FILE_PATH);
            } else {
                TerminalPrinter.println("잘못된 입력입니다.");
            }
        }

    }
}
