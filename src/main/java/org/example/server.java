package org.example;

import com.sun.source.tree.WhileLoopTree;
import org.example.functions.post.CommandHandler;
import org.example.functions.login.loginController;
import org.example.view.TerminalPrinter;
import org.json.simple.parser.ParseException;
import org.example.model.User;

import java.io.IOException;
import java.util.Scanner;

public class server {

    public static void server() throws Exception {
        Scanner scanner = new Scanner(System.in);


        User user = loginController.loginController();

        System.out.println(user.getNickName() + "님 환영합니다.");
        while(true) {
            TerminalPrinter.println("게시판 프로그램입니다.");
            TerminalPrinter.println("명령어를 입력해주세요.");
            TerminalPrinter.println("명령어 목록");
            TerminalPrinter.println("1. add\t\t2. list\n3. detail\t4. update\n5. delete\t6. search\n7. clear\t8. exit");
            TerminalPrinter.print("명령어를 입력해주세요: ");
            String name = scanner.nextLine();
            int var = CommandHandler.command_controller(name, user);
        }
    }
}
