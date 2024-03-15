package org.example.functions.post;

import org.example.Database.SQLController;
import org.example.model.User;
import org.example.view.TerminalPrinter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import static org.example.functions.utils.utils.getNumber;
import static org.example.functions.utils.utils.getString2;

public class FunctionUpdate {

    public static int update(User user)  {
        String title;
        int index;
        try {
            FunctionList.list();
            TerminalPrinter.println("수정할 게시물의 번호를 입력해주세요:(취소: -1)");
            index = getNumber();
            if (index == -1 || !AuthCheck.check(user.getNickname(), index)){
                return -1;
            }
            TerminalPrinter.println("1. 제목 수정, 2. 내용 수정");
            int choice = getNumber();
            if (choice == 1){
                title = getString2("수정할 제목을 입력해주세요:");
                String sql = "UPDATE Post SET title = '" + title + "' WHERE post_id = " + index;
                SQLController sqlController = new SQLController();
                sqlController.executeSQL(sql);
                TerminalPrinter.println("수정이 완료되었습니다.");
            }
            if (choice == 2){
                title = getString2("수정할 내용을 입력해주세요:");
                String sql = "UPDATE Post SET content = '" + title + "' WHERE post_id = " + index;
                SQLController sqlController = new SQLController();
                sqlController.executeSQL(sql);
                TerminalPrinter.println("수정이 완료되었습니다.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 5;
    }
}
