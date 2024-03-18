package org.example.functions.post;

import org.example.Database.SQLController;
import org.example.model.Post;
import org.example.view.TerminalPrinter;
import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.StringTokenizer;

import static org.example.functions.utils.utils.getNumber;
import static org.example.functions.utils.utils.getString2;

public class FunctionSearch {

    public static int searchByBody() throws IOException {
        String body = getString2("검색할 내용을 입력해주세요: ");
        String sql = "SELECT * FROM Post WHERE content LIKE '%" + body + "%'";
        SQLController sqlcontroller = new SQLController();
        List<?> postList = sqlcontroller.getList(sql, Post.class);

        for (Object post : postList) {
            if (post instanceof Post) {
                Post typedPost = (Post) post;
                TerminalPrinter.println(typedPost.getPostId() + ". " + typedPost.getTitle());
            }
        }
        return 1;
    }

    public static int searchByTitle() throws IOException {
        String title = getString2("검색할 제목을 입력해주세요: ");
        String sql = "SELECT * FROM Post WHERE title LIKE '%" + title + "%'";
        SQLController sqlcontroller = new SQLController();
        List<?> postList = sqlcontroller.getList(sql, Post.class);

        for (Object post : postList) {
            if (post instanceof Post) {
                Post typedPost = (Post) post;
                TerminalPrinter.println(typedPost.getPostId() + ". " + typedPost.getTitle());
            }
        }
        return 1;
    }
    public static int search() throws IOException {
        TerminalPrinter.println("찾고자하는 방식을 선택해주세요");
        TerminalPrinter.println("1. 제목으로 검색");
        TerminalPrinter.println("2. 내용으로 검색");
        int choice = getNumber();
        if (choice == 1) {
            searchByTitle();
        } else if (choice == 2) {
            searchByBody();
        } else {
            TerminalPrinter.println("잘못된 입력입니다.");
            return 0;
        }
        return 1;
    }
}
