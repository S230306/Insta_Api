package com.insta.controller;

import com.insta.Util.CommonUtils;
import com.insta.model.User;
import com.insta.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody String requser){
        JSONObject errorjson = CommonUtils.validateUser(requser);
        if(errorjson.isEmpty()) {
            User user = CommonUtils.setUser(requser);
            int id = userService.add(user);
            return new ResponseEntity<>("user created " + id, HttpStatus.CREATED);
        }
         return new ResponseEntity<>(errorjson.toString(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("get-user")
    public ResponseEntity<String> getUser(@Nullable @RequestParam String id){
        JSONArray jsonArray = userService.getuser(id);
        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id , @RequestBody String userdata){
        JSONObject errorjson = CommonUtils.validateUser(userdata);
        if(errorjson.isEmpty()) {
            User user = CommonUtils.setUser(userdata);
            userService.update(user,id);
            return new ResponseEntity<>("updated user",HttpStatus.OK);
        }
           return new ResponseEntity<>(errorjson.toString(),HttpStatus.BAD_REQUEST);

    }

}
