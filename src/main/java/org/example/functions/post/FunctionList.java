package org.example.functions.post;

import org.example.Database.getFile;

import java.io.File;

public class FunctionList {
    public static int list(String FILE_PATH) {
        try {
            File folder = getFile.accessFolder(FILE_PATH);
            File[] fileList = folder.listFiles();
            assert fileList != null;

            getFile.show_filelist(fileList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 3;
    }
}
