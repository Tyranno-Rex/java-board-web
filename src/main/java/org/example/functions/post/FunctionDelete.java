package org.example.functions.post;

import org.example.Database.getFile;
import org.example.model.User;
import org.example.view.TerminalPrinter;

import java.io.File;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FunctionDelete {

    public static void delete_func(User user) {
        try {
            FunctionList list = new FunctionList();
            list.list();

            TerminalPrinter.print("삭제할 게시물의 번호를 입력해주세요: (취소 -1): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int index = Integer.parseInt(input);

            if (index == -1 || !AuthCheck.check(user.getNickname(), index)){
                return;
            }
            String url = "jdbc:mysql://192.168.22.1:3306/java";
            String username = "eunseong";
            String pw = "1234";
            String sql = "DELETE FROM Post WHERE post_id = " + index;
            try (Connection connection = DriverManager.getConnection(url, username, pw);
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                TerminalPrinter.println("삭제되었습니다.");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }

        } catch (InputMismatchException | NumberFormatException e) {
            // 숫자가 아닌 다른 값이 입력되었을 때 처리
            TerminalPrinter.println("숫자를 입력해주세요.");
            delete_func(user);
        } catch (Exception e) {
            // 기타 예외 처리
            TerminalPrinter.println("오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    public static int delete(User user) {
        try {
            delete_func(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
