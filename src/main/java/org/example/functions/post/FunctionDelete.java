package org.example.functions.post;

import org.example.Database.getFile;
import org.example.view.TerminalPrinter;

import java.io.File;
import java.util.Scanner;

public class FunctionDelete {

    public static int delete(String FILE_PATH) {
        try {
            File folder = getFile.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            getFile.show_filelist(fileList);

            TerminalPrinter.print("삭제할 게시물의 번호를 입력해주세요: ");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (fileList.length <= index) {
                TerminalPrinter.println("잘못된 번호를 입력하셨습니다.");
                return 0;
            }
            File file = fileList[index];
            if( file.exists() ){
                if(file.delete()){
                    TerminalPrinter.println("파일삭제 성공");
                }else{
                    TerminalPrinter.println("파일삭제 실패");
                }
            }else{
                TerminalPrinter.println("파일이 존재하지 않습니다.");
            }
            TerminalPrinter.println("게시물이 삭제되었습니다.");
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
