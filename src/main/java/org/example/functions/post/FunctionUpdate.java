package org.example.functions.post;

import org.example.model.User;
import org.example.view.TerminalPrinter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import static org.example.functions.utils.utils.getNumber;
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
            String url = "jdbc:mysql://192.168.22.1:3306/java";
            String username = "eunseong";
            String pw = "1234";
            String sql = "SELECT * FROM Post WHERE post_id = " + index;
            TerminalPrinter.println("1. 제목 수정, 2. 내용 수정");
            int choice = getNumber();
            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    TerminalPrinter.println("존재하지 않는 게시물입니다.");
                    return 0;
                }

                if (choice == 1){
                    TerminalPrinter.println("현재 제목: " + resultSet.getString(5));
                    TerminalPrinter.println("수정할 제목을 입력해주세요:");
                    Scanner sc = new Scanner(System.in);
                    title = sc.nextLine();
                    sql = "UPDATE Post SET title = ? WHERE post_id = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
                        statement2.setString(1, title);
                        statement2.setInt(2, index);
                        statement2.executeUpdate();
                        TerminalPrinter.println("수정이 완료되었습니다.");
                    }
                }
                if (choice == 2){
                    TerminalPrinter.println("수정할 내용을 입력해주세요:");
                    Scanner sc = new Scanner(System.in);
                    title = sc.nextLine();
                    sql = "UPDATE Post SET content = ? WHERE post_id = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
                        statement2.setString(1, title);
                        statement2.setInt(2, index);
                        statement2.executeUpdate();
                        TerminalPrinter.println("수정이 완료되었습니다.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 5;
    }
}
