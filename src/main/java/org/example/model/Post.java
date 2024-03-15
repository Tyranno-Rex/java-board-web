package org.example.model;


public class Post {
    private long postId;
    private Long userId;
    private String nickname;
    private boolean isAnonymous;
    private String title;
    private String body;
    private String date;
    private String editDate;
    private int viewCount;
    private int likeCount;
    private int dislikeCount;

    public Post(long postId, Long userId, String Nickname, boolean isAnonymous, String title, String body, String date, String editDate, int viewCount, int likeCount, int dislikeCount) {
        this.postId = postId;
        this.userId = userId;
        this.nickname = Nickname;
        this.isAnonymous = isAnonymous;
        this.title = title;
        this.body = body;
        this.date = date;
        this.editDate = editDate;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

}
