package org.example.functions.post;

import org.example.Database.getFile;
import org.example.view.TerminalPrinter;
import org.example.view.ViewJsonData;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class FunctionDetail {
    public static int edit(File[] fileList, int index, JSONObject original, String FILE_PATH) {
        try {
            Scanner scanner = new Scanner(System.in);
            TerminalPrinter.println("게시물의 제목을 수정해주세요: ");
            String title = scanner.nextLine();
            TerminalPrinter.println("게시물의 내용을 수정해주세요: ");
            String body = scanner.nextLine();
            original.replace("title", title);
            original.replace("body", body);
            original.put("edit", new java.util.Date().toString());
            FileWriter fileWriter = new FileWriter(fileList[index]);
            fileWriter.write(original.toJSONString());
            fileWriter.close();
            TerminalPrinter.println("게시물이 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static int detail(String FILE_PATH) {
        try {
            File folder = getFile.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            getFile.show_filelist(fileList);

            TerminalPrinter.print("상세 조회할 게시물의 번호를 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (fileList.length <= index) {
                TerminalPrinter.println("잘못된 번호를 입력하셨습니다.");
                return 0;
            }

            File file = fileList[index];
            TerminalPrinter.println("\n검색하신 게시물의 정보입니다.\n");
            TerminalPrinter.println("---------------------------------");
            TerminalPrinter.println("검색한 게시물 제목: " + file.getName());
            TerminalPrinter.println("---------------------------------");
            TerminalPrinter.println("검색한 게시물 내용:");
            TerminalPrinter.println("---------------------------------");
            FileReader fileReader = new FileReader(file);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader);
            JSONObject original = (JSONObject) obj;

            // 화면에 랜더링할 정보를 감싼다.
            JSONObject view_object;
            view_object = new JSONObject();
            view_object.put("title", original.get("title"));
            view_object.put("body", original.get("body"));
            view_object.put("date", original.get("date"));
            view_object.put("edit", original.get("edit"));
            view_object.put("view", original.get("view"));
            view_object.put("comment", original.get("comment"));
            view_object.put("like", original.get("like"));
            view_object.put("dislike", original.get("dislike"));

            // 화면에 랜더링할 정보를 출력한다.

            ViewJsonData.printJsonData(view_object);
            Long view = (Long) original.get("view");
            original.put("view", view.intValue() + 1);

            while (true) {

                TerminalPrinter.println("1. 댓글 달기\n2. 추천하기\n3. 비추천하기\n4. 수정\n5. 삭제\n6. 뒤로가기");
                TerminalPrinter.print("원하시는 기능을 선택해주세요: ");
                int select = scanner.nextInt();
                if (select == 1) {
                    TerminalPrinter.print("댓글을 입력해주세요: ");
                    scanner.nextLine();
                    String comment = scanner.nextLine();
                    ArrayList<String> commentList = (ArrayList<String>) original.get("comment");
                    commentList.add(comment);
                    original.put("comment", commentList);
                    TerminalPrinter.println("댓글이 등록되었습니다.");
                } else if (select == 2) {
                    Long like = (Long) original.get("like");
                    original.put("like", like.intValue() + 1);
                    TerminalPrinter.println("추천이 등록되었습니다.");
                } else if (select == 3) {
                    Long dislike = (Long) original.get("dislike");
                    original.put("dislike", dislike.intValue() + 1);
                    TerminalPrinter.println("비추천이 등록되었습니다.");
                } else if (select == 4) {
                    if (edit(fileList, index, original, FILE_PATH) == 0) {
                    }
                } else if (select == 5) {
                    file.delete();
                    TerminalPrinter.println("게시물이 삭제되었습니다.");
                } else if (select == 6) {
                    break;
                } else {
                    TerminalPrinter.println("잘못된 번호를 입력하셨습니다.");
                }
            }
            FileWriter fileWriter = new FileWriter(fileList[index]);
            fileWriter.write(original.toJSONString());
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        } catch (AssertionError e) {
            TerminalPrinter.println("게시물이 존재하지 않습니다.");
            return 0;
        }
        return 1;
    }
}
