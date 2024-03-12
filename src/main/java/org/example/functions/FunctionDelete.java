package org.example.functions;

import org.example.CommandHandler;
import org.example.Database.FileController;

import java.io.File;
import java.util.Scanner;

public class FunctionDelete {

    public static int delete(String FILE_PATH) {
        try {
            File folder = FileController.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            FileController.show_filelist(fileList);

            System.out.print("삭제할 게시물의 번호를 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (fileList.length <= index) {
                System.out.println("잘못된 번호를 입력하셨습니다.");
                return 0;
            }
            File file = fileList[index];
            file.delete();
            System.out.println("게시물이 삭제되었습니다.");
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("---------------------------------");
                System.out.println("\n명령어를 입력해주세요. (add, detail, list, delete, update, exit)");
                System.out.print("\nEnter Command: ");
                String name = scanner.nextLine();

                int result = CommandHandler.command_controller(name);
            }
        }
    }
}
