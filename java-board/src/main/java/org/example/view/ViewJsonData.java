package org.example.view;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ViewJsonData {
    public static void printJsonData(JSONObject jsonData) {
        TerminalPrinter.println("게시물 제목: " + jsonData.get("title"));
        TerminalPrinter.println("게시물 내용: " + jsonData.get("body"));
        TerminalPrinter.println("게시물 작성일: " + jsonData.get("date"));
        TerminalPrinter.println("게시물 수정일: " + jsonData.get("edit"));
        TerminalPrinter.println("게시물 작성자: " + jsonData.get("user"));
        Object commentObject = jsonData.get("comment");
        ArrayList<String> comments = (ArrayList<String>) commentObject;
        for (String comment : comments) {
            TerminalPrinter.println("댓글: " + comment);
        }
        TerminalPrinter.println("게시물 조회수: " + jsonData.get("view"));
        TerminalPrinter.println("게시물 좋아요: " + jsonData.get("like"));
        TerminalPrinter.println("게시물 싫어요: " + jsonData.get("dislike"));
        TerminalPrinter.println("---------------------------------");
    }
}
