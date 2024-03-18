package org.example.model;

public class Comments {
    private long commentId;
    private long postId;
    private String comment;
    private String nickname;
    public Comments() {
    }

    public Comments(long commentId, long postId, String comment, String nickname) {
        this.commentId = commentId;
        this.postId = postId;
        this.comment = comment;
        this.nickname = nickname;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
