package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {
    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\db\\";

    public static File accessFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            throw new IllegalArgumentException("폴더가 존재하지 않습니다.");
        }
        return folder;
    }

    public static void show_filelist(File[] fileList) {
        int num = 0;
        for (File file : fileList) {
            System.out.println(num  + ". " + file.getName());
            num++;
        }
    }

    public static int  command_controller (String command) {
        if (command.equals("exit")) {
            exit(0);
            return 0;
        }
        else if (command.equals("add")) {
            try  {
                System.out.print("게시물의 제목을 입력해주세요: ");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                System.out.print("게시물의 내용을 입력해주세요: ");
                String body = scanner.nextLine();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", title);
                jsonObject.put("body", body);

                FileWriter fileWriter = new FileWriter(FILE_PATH + title + ".json");
                fileWriter.write(jsonObject.toString());
                fileWriter.flush(); // 파일에 입력된 내용을 강제로 쓰기
                fileWriter.close(); // 파일 닫기
                System.out.println("게시물의 등록되었습니다");
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 2;
        }
        else if (command.equals("list")) {
            try {
                File folder = accessFolder(FILE_PATH);
                File[] fileList = folder.listFiles();
                assert fileList != null;

                show_filelist(fileList);
            }
            catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return 3;
        }
        else if (command.equals("detail")) {
            try {
                File folder = accessFolder(FILE_PATH);
                File[] fileList = folder.listFiles();
                assert fileList != null;

                show_filelist(fileList);

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
        else if (command.equals("update")) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                File folder = accessFolder(FILE_PATH);
                File[] fileList = folder.listFiles();
                show_filelist(fileList);
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
                    original.put("body", original.get("body") + body);
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
            }
            catch (IOException e) {
                System.err.println("파일을 읽거나 쓰는 도중 오류가 발생했습니다: " + e.getMessage());
                e.printStackTrace();
            }
            catch (ParseException e) {
                System.err.println("파일 파싱 중 오류가 발생했습니다: " + e.getMessage());
                e.printStackTrace();
            }
            catch (JSONException e) {
                System.err.println("JSON 형식 오류: " + e.getMessage());
                e.printStackTrace();
            }
            return 5;
        }
        else if (command.equals("delete")) {
            try {
                File folder = accessFolder(FILE_PATH);
                File[] fileList = folder.listFiles();
                assert fileList != null;

                show_filelist(fileList);

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

            return 4;
        }
        else {
            return 6;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------------");
            System.out.println("\n명령어를 입력해주세요. (add, detail, list, delete, update, exit)");
            System.out.print("\nEnter Command: ");
            String name = scanner.nextLine();

            int result = command_controller(name);
        }
    }
}