package org.example.model;

public class likeAndDislike {
    private long post_id;
    private long user_id;
    private boolean like_or_dislike;

    public likeAndDislike(long post_id, long user_id, boolean like_or_dislike) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.like_or_dislike = like_or_dislike;
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

    public boolean isLike_or_dislike() {
        return like_or_dislike;
    }

    public void setLike_or_dislike(boolean like_or_dislike) {
        this.like_or_dislike = like_or_dislike;
    }
}
