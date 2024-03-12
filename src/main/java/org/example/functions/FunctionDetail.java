package org.example.functions;

import org.example.Database.FileController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class FunctionDetail {

    public static int detail(String FILE_PATH) {
        try {
            File folder = FileController.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            FileController.show_filelist(fileList);

            System.out.print("상세 조회할 게시물의 번호를 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (fileList.length <= index) {
                System.out.println("잘못된 번호를 입력하셨습니다.");
                return 0;
            }

            File file = fileList[index];
            System.out.println("\n검색하신 게시물의 정보입니다.\n");
            System.out.println("---------------------------------");
            System.out.println("검색한 게시물 제목: " + file.getName());
            System.out.println("---------------------------------");
            System.out.println("검색한 게시물 내용:");
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
