package org.example.functions.post;
import java.io.*;
import java.util.*;
import java.text.*;

import org.example.view.TerminalPrinter;
import org.json.simple.JSONObject;
public class FunctionADD {

    public static int add(String FILE_PATH) {
        try  {
            TerminalPrinter.print("게시물의 제목을 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            TerminalPrinter.print("게시물의 내용을 입력해주세요: ");
            String body = scanner.nextLine();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("body", body);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(new Date());
            jsonObject.put("date", date);
            jsonObject.put("edit", date);
            jsonObject.put("view", 1);
            jsonObject.put("like", 0);
            jsonObject.put("dislike", 0);
            jsonObject.put("comment", new ArrayList<String>());

            FileWriter fileWriter = new FileWriter(FILE_PATH + title + ".json");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush(); // 파일에 입력된 내용을 강제로 쓰기
            fileWriter.close(); // 파일 닫기
            TerminalPrinter.println("게시물의 등록되었습니다");
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 2;
    }
}
