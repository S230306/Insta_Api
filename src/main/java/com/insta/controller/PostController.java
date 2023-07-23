package com.insta.controller;

import com.insta.dao.UserRepo;
import com.insta.model.Post;
import com.insta.model.User;
import com.insta.service.PostService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResponseEntity<String> savePost(@RequestBody String postdata){
        Post post = setPost(postdata);
        int postid = postService.save(post);
        return new ResponseEntity<>("post saved id "+postid, HttpStatus.CREATED);
    }
    @GetMapping("get-post")
    public ResponseEntity<String> getPost(@RequestParam String userid , @Nullable @RequestParam String postid){
        JSONArray arr = postService.getposts(userid,postid);
        return new ResponseEntity<>(arr.toString(),HttpStatus.OK);
    }
    @GetMapping("/get-all-post")
    public List<Post> getAll(){
        return postService.get();
    }
    @PutMapping("/update-post/{postid}")
    public ResponseEntity<String> updatepost(@PathVariable String postid,@RequestBody String reqpost){
        Post post = setPost(reqpost);
        postService.update(postid,post);
        return new ResponseEntity<>("updated post",HttpStatus.OK);
    }
    private Post setPost(String postdata) {
        JSONObject jsonObject = new JSONObject(postdata);
        User user = null;
        int userid = jsonObject.getInt("userId");
        if(userRepo.findById(userid).isPresent()){
            user = userRepo.findById(userid).get();
        }else {
            return null;
        }
        Post post = new Post();
        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));
        Timestamp creattime = new Timestamp(System.currentTimeMillis());
        post.setCreatedDate(creattime);
        return post;
    }
}
