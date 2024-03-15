package org.example.functions.login;

import org.example.model.User;
import org.example.view.TerminalPrinter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class loginController {
    private static String USER_FILE_PATH =  System.getProperty("user.dir") + "\\src\\db\\User\\";

    public static User loop() {
        try {
            TerminalPrinter.println("1. 로그인 2. 회원가입 3. 비밀번호 찾기");
            Scanner scanner = new Scanner(System.in);

            // 사용자 입력을 문자열로 받음
            String input = scanner.nextLine();
            // 숫자로 변환 시도
            int num = Integer.parseInt(input);
            // 입력값에 따른 처리
            User user;
            if (num == 1) {
                if ((user = login.login(USER_FILE_PATH)) != null){
                    return user;
                }
            } else if (num == 2) {
                signup.signup(USER_FILE_PATH);
                loop();
            } else if (num == 3) {
                findPassword.findPassword(USER_FILE_PATH);
                loop();
            } else {
                TerminalPrinter.println("잘못된 입력입니다.");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            // 숫자가 아닌 다른 값이 입력되었을 때 처리
            TerminalPrinter.println("숫자를 입력해주세요.");
            loop();
        } catch (Exception e) {
            // 기타 예외 처리
            TerminalPrinter.println("오류가 발생했습니다.");
            e.printStackTrace();
        }
        return null;
    }


    public static User loginController() throws Exception {

        while (true) {
            try {
                User user = loop();
                if (user != null) {
                    return user;
                } else {
                    TerminalPrinter.println("로그인에 실패했습니다.");
                    TerminalPrinter.println("다시 시도해주세요.");
                }
            }  catch (Exception e) {
                // 기타 예외 처리
                TerminalPrinter.println("오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
}
