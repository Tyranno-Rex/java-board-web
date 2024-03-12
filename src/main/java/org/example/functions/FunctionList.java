package org.example.functions;

import org.example.Database.FileController;

import java.io.File;

public class FunctionList {
    public static int list(String FILE_PATH) {
        try {
            File folder = FileController.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            FileController.show_filelist(fileList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 3;
    }
}
