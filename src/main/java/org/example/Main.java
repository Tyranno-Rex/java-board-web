package org.example;


import java.sql.*;


public class Main {

    public static void main(String[] args) {
        try {
            server.server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}