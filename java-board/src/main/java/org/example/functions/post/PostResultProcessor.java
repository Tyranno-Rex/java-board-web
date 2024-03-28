//package org.example.functions.post;
//
//import org.example.model.Post;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PostResultProcessor implements ResultProcessor<Post> {
//    public List<Post> getList(ResultSet resultSet) {
//
//        List<Post> postList = new ArrayList<>();
//
//        try {
//            while(resultSet.next()) {
//                Post post = new Post();
//
//                post.setPostId(resultSet.getLong("post_id"));
//                post.setUserId(resultSet.getLong("user_id"));
//                post.setNickname(resultSet.getString("nickname"));
//                post.setAnonymous(resultSet.getBoolean("anonymous"));
//                post.setTitle(resultSet.getString("title"));
//                post.setBody(resultSet.getString("content"));
//                post.setDate(resultSet.getString("date"));
//                post.setEditDate(resultSet.getString("modified_date"));
//                post.setViewCount(resultSet.getInt("views"));
//                post.setLikeCount(resultSet.getInt("likes"));
//                post.setDislikeCount(resultSet.getInt("dislikes"));
//
//                postList.add(post);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return postList;
//    }
//}
