package org.example.model;


import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    private String name;
    private String email;

    private ArrayList<String> posts = new ArrayList<String>();

    public User(String id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.posts = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addPost(String post) {
        posts.add(post);
    }
    public ArrayList<String> getPosts() {
        return posts;
    }
}
