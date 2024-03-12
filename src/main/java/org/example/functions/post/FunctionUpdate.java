package org.example.functions.post;

import org.example.Database.FileToJson;
import org.example.Database.getFile;
import org.example.view.TerminalPrinter;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.StringTokenizer;

public class FunctionUpdate {

    public static int update(String FILE_PATH) throws JSONException {

        String title;
        int index;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            File folder = getFile.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            getFile.show_filelist(fileList);
            assert fileList != null;

            TerminalPrinter.println("수정할 게시물의 번호를 입력해주세요: ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            index = Integer.parseInt(st.nextToken());

            if (fileList.length <= index) {
                TerminalPrinter.println("잘못된 입력입니다.");
                return 0;
            }
            TerminalPrinter.println("수정할 게시물의 제목을 입력해주세요: ");
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            title = st2.nextToken();

            File originalFile = fileList[index];
            if (originalFile.renameTo(new File(FILE_PATH + title + ".json"))) {
                TerminalPrinter.println("파일이름이 변경되었습니다.");
            } else {
                TerminalPrinter.println("파일이름 변경이 실패했습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            File[] fileList = getFile.returnfilelist(FILE_PATH);
            JSONObject original = null;

            for (File file : fileList) {
                if (file.getName().equals(title + ".json")) {
                    original = FileToJson.FileToJson(file);
                }
            }
           assert original != null;
            TerminalPrinter.println("게시물의 버전을 선택해주세요: ");
            TerminalPrinter.println("1. 게시물 이어 쓰기  2. 게시물 덮어쓰기");

            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int version = Integer.parseInt(st3.nextToken());
            TerminalPrinter.println("게시물의 제목을 입력해주세요: ");

            StringTokenizer st4 = new StringTokenizer(br.readLine());
            String body = st4.nextToken();
            if (version == 1)
                original.put("body", original.get("body") + "\n" + body);
            else if (version == 2) {
                original.remove("body");
                original.put("body", body);
            } else {
                TerminalPrinter.println("잘못된 입력입니다.");
                return 0;
            }
            original.put("title", title);

            // 파일을 업데이트합니다.
            FileWriter fileWriter = new FileWriter(fileList[index]);
            fileWriter.write(original.toString());
            fileWriter.close();
        }

        catch (FileNotFoundException e) {
            TerminalPrinter.println("파일을 찾을 수 없습니다." + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            TerminalPrinter.println("파일을 읽거나 쓰는 도중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (
        ParseException e) {
            TerminalPrinter.println("파일 파싱 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (
        JSONException e) {
            TerminalPrinter.println("JSON 형식 오류: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 5;
    }
}
