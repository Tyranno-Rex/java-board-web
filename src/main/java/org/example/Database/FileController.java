package org.example.Database;

import java.io.File;

    public class FileController {
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
}
