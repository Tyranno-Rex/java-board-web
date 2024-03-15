package org.example.functions.utils;

import org.example.view.TerminalPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class utils {
    public static int getNumber() {
        try {
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            return index;
        } catch (InputMismatchException e) {
            TerminalPrinter.println("잘못된 입력입니다.");
            TerminalPrinter.println("숫자를 입력해주세요.");
            getNumber();
            return 0;
        }
    }
    public static String getString() {
        try {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            return str;
        } catch (InputMismatchException e) {
            TerminalPrinter.println("잘못된 입력입니다.");
            TerminalPrinter.println("문자를 입력해주세요.");
            getString();
            return "";
        }
    }

    public static boolean GetBoolean() {
        try {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if (str == "Y") {
                return true;
            } else if (str == "N") {
                return false;
            }
        } catch (InputMismatchException e) {
            TerminalPrinter.println("잘못된 입력입니다.");
            TerminalPrinter.println("true 또는 false를 입력해주세요.");
            GetBoolean();
        }
        return false;
    }
}
