package org.example.functions;

import org.example.Database.FileController;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.StringTokenizer;

public class FunctionUpdate {

    public static int update(String FILE_PATH) throws JSONException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            File folder = FileController.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            FileController.show_filelist(fileList);
            assert fileList != null;

            System.out.print("수정할 게시물의 번호를 입력해주세요: ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());

            if (fileList.length <= index) {
                System.out.println("게시물이 존재하지 않습니다.");
                return 0;
            }

            FileReader fileReader = new FileReader(fileList[index]);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader);
            org.json.simple.JSONObject original = (org.json.simple.JSONObject) obj;

            System.out.println();

            System.out.print("세로 적을 게시물의 제목을 입력해주세요: ");
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            String title = st2.nextToken();

            System.out.print("1. 게시물 이어 쓰기  2. 게시물 덮어쓰기: ");
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int version = Integer.parseInt(st3.nextToken());

            System.out.print("게시물의 내용을 입력해주세요: ");
            StringTokenizer st4 = new StringTokenizer(br.readLine());
            String body = st4.nextToken();

            if (version == 1)
                original.put("body", original.get("body") + "\n" + body);
            else if (version == 2) {
                original.remove("body");
                original.put("body", body);
            } else {
                System.out.println("잘못된 입력입니다.");
                return 0;
            }

            original.put("title", title);


            // 파일을 업데이트합니다.
            FileWriter fileWriter = new FileWriter(fileList[index]);
            fileWriter.write(original.toString());
            fileWriter.close();

            File originalFile = fileList[index];
            File newFile = new File(FILE_PATH + title + ".json");

            if (originalFile.renameTo(newFile)) {
                System.out.println("파일 이름 변경에 성공했습니다.");
            } else {
                System.out.println("파일 이름 변경에 실패했습니다.");
            }
        }




        catch (FileNotFoundException e) {
            System.err.println("파일을 찾을 수 없습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("파일을 읽거나 쓰는 도중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (
        ParseException e) {
            System.err.println("파일 파싱 중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } catch (
        JSONException e) {
            System.err.println("JSON 형식 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return 5;
    }
}
