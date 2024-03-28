package org.example.functions.login;
import org.example.model.User;
import org.example.view.TerminalPrinter;
import org.example.Database.SQLController;
import static org.example.functions.utils.utils.getString2;

public class login {
    public static User login() throws Exception{
        try {
            String id = getString2("아이디: ", true);
            String password = getString2("비밀번호: ", true);
            String sql = "SELECT * FROM User WHERE ID = '" + id + "' AND password = '" + password + "'";

            SQLController SQLController = new SQLController();
            User user = SQLController.getUser(sql);

            if (user != null) {
                TerminalPrinter.println("로그인 성공");
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        TerminalPrinter.println("로그인 실패");
        return null;
    }
}