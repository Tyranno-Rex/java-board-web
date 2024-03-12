package org.example;
import org.example.functions.*;
import static java.lang.System.exit;

public class CommandHandler {

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\db\\";
    public static int  command_controller (String command) {
        if (command.equals("exit")) {
            exit(0);
            return 0;
        }
        else if (command.equals("add")) {
            int result = FunctionADD.add(FILE_PATH);
            return result;
        }
        else if (command.equals("list")) {
            int result = FunctionList.list(FILE_PATH);
            return result;
        }
        else if (command.equals("detail")) {
            int result = FunctionDetail.detail(FILE_PATH);
            return 1;
        }
        else if (command.equals("update")) {
            FunctionUpdate.update(FILE_PATH);
            return 5;
        }
        else if (command.equals("delete")) {
            int result = FunctionDelete.delete(FILE_PATH);
            return result;
        }
        else {
            return 6;
        }
    }
}
