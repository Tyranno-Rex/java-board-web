package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("---------------------------------");
            System.out.println("\n명령어를 입력해주세요. (add, detail, list, delete, update, exit)");
            System.out.print("\nEnter Command: ");
            String name = scanner.nextLine();
            int var3 = CommandHandler.command_controller(name);
        }
    }
}