package org.example.functions.post;

import org.example.model.User;
import org.example.view.TerminalPrinter;

import java.io.IOException;

import static java.lang.System.exit;

public class CommandHandler {
    private static final String POST_FILE_PATH = System.getProperty("user.dir") + "\\src\\db\\posts\\";
    public static int  command_controller (String command, User user) throws IOException {
        if (command.equals("exit") || command.equals("8")) {
            exit(0);
            return 0;
        } else if (command.equals("add") || command.equals("1")) {
            int result = FunctionADD.add(user);
            return result;
        } else if (command.equals("list") || command.equals("2")) {
            int result = FunctionList.list();
            return result;
        } else if (command.equals("detail") || command.equals("3")) {
            int result = FunctionDetail.detail(user);
            return result;
        } else if (command.equals("update") || command.equals("4")) {
            FunctionUpdate.update(user);
            return 1;
        } else if (command.equals("delete") || command.equals("5")) {
            int result = FunctionDelete.delete(user);
            return result;
        } else if (command.equals("search") || command.equals("6")) {
            int result = FunctionSearch.search();
            return result;
        } else if (command.equals("clear") || command.equals("7")) {
            TerminalPrinter.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            return 1;
        } else {
            return 6;
        }
    }
}
