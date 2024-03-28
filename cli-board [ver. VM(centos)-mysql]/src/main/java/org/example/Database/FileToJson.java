package org.example.Database;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class FileToJson {

    public static JSONObject FileToJson(File file) throws Exception{
        FileReader fileReader = new FileReader(file);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONObject user_json = (JSONObject) obj;
        return user_json;
    }
}
