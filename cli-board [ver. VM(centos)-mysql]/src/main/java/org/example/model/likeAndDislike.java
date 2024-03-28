package org.example.model;

public class likeAndDislike {
    private long post_id;
    private long user_id;

    public likeAndDislike() {
    }

    public likeAndDislike(long post_id, long user_id) {
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
