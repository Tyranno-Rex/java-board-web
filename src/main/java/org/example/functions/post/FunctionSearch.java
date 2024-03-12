package org.example.functions.post;

import org.example.view.TerminalPrinter;

import java.io.*;
import java.util.StringTokenizer;

public class FunctionSearch {

    public static int searchByBody(File[] fileList, BufferedReader br) throws IOException {
        TerminalPrinter.print("검색할 내용을 입력해주세요: ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        String body = st.nextToken();
        for (File file : fileList) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(body)) {
                    TerminalPrinter.println(file.getName());
                    break;
                }
            }
        }
        return 1;
    }
    public static int searchByTitle(File[] fileList, BufferedReader br) throws IOException {
        TerminalPrinter.print("검색할 제목을 입력해주세요: ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        String title = st.nextToken();
        for (File file : fileList) {
            if (file.getName().contains(title)) {
                TerminalPrinter.println(file.getName());
            }
        }
        return 1;
    }
    public static int search(String FILE_PATH) throws IOException {
        File folder = new File(FILE_PATH);
        File[] fileList = folder.listFiles();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TerminalPrinter.println("게시물 목록");
        for (File file : fileList) {
            TerminalPrinter.println(file.getName());
        }

        TerminalPrinter.println("찾고자하는 방식을 선택해주세요");
        TerminalPrinter.println("1. 제목으로 검색");
        TerminalPrinter.println("2. 내용으로 검색");

        int choice = Integer.parseInt(br.readLine());
        if (choice == 1) {
            return searchByTitle(fileList, br);
        } else if (choice == 2) {
            return searchByBody(fileList, br);
        } else {
            TerminalPrinter.println("잘못된 입력입니다.");
            return 0;
        }
    }
}
