package com.insta.service;

import com.insta.dao.PostRepo;
import com.insta.model.Post;
import com.insta.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;

    public int save(Post post) {
        Post post1 = postRepo.save(post);
        return post1.getPostId();
    }

    public JSONArray getposts(String userid, String postid) {
        JSONArray array = new JSONArray();
        if(null != postid && postRepo.findById(Integer.valueOf(postid)).isPresent()){
            Post post = postRepo.findById(Integer.valueOf(postid)).get();
            JSONObject obj = setPost(post);
            array.put(obj);
        }else{
            int userId = Integer.valueOf(userid);
            List<Post> postList = postRepo.findAll();
            for (Post post:postList) {
                User user = post.getUser();
//                JSONObject obj = setPost(post);
//                User user = (User) obj.get("user");
                if(user.getUserId() == userId){
                    JSONObject obj = setPost(post);
                    array.put(obj);
                }
            }
        }
        return array;
    }

    private JSONObject setPost(Post post) {
        JSONObject json = new JSONObject();
        json.put("postId",post.getPostId());
        json.put("postData",post.getPostData());
        User user = post.getUser();
        JSONObject userjson = new JSONObject();
        userjson.put("userId",user.getUserId());
        userjson.put("FirstName",user.getfName());
        userjson.put("age",user.getAge());
        json.put("user",userjson);
        return  json;
    }

    public void update(String postid,Post newpost) {
        if(postRepo.findById(Integer.valueOf(postid)).isPresent()) {
            Post post = postRepo.findById(Integer.valueOf(postid)).get();
            post.setPostId(newpost.getPostId());
            post.setPostData(newpost.getPostData());
            post.setUser(newpost.getUser());
            Timestamp updatetime = new Timestamp(System.currentTimeMillis());
            post.setUpdatedDate(updatetime);
            postRepo.save(post);
        }
    }

    public List<Post> get() {
       return postRepo.findAll();
    }
}
