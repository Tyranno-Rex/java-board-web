package org.example.Database;

import org.example.view.TerminalPrinter;

import java.io.File;

public class getFile {
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
            TerminalPrinter.println(num  + ". " + file.getName());
            num++;
        }
    }
    public static File[] returnfilelist(String folderPath) {
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList == null) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        return fileList;
    }
}
