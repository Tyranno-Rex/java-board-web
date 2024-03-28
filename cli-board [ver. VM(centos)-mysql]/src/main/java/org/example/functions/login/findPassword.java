package org.example.functions.login;

import org.example.Database.SQLController;
import org.example.model.User;
import org.example.view.TerminalPrinter;
import java.sql.*;

import static org.example.functions.utils.utils.getString;

public class findPassword {

    public static void findPassword() throws Exception{
        TerminalPrinter.print("\n--------\n비밀번호 찾기\n--------\n");
        String id, email, nickname;


        TerminalPrinter.println("아이디: ");
        id = getString();
        TerminalPrinter.println("닉네임: ");
        nickname = getString();
        TerminalPrinter.println("이메일: ");
        email = getString();

        String sql = "SELECT * FROM User WHERE ID = '" + id + "' AND nickname = '" + nickname + "' AND email = '" + email + "'";
        SQLController SQLController = new SQLController();
        User user = SQLController.getUser(sql);

        if (user != null) {
            TerminalPrinter.println("비밀번호는 " + user.getPassword() + "입니다.");
        } else {
            TerminalPrinter.println("일치하는 정보가 없습니다.");
        }
    }
}