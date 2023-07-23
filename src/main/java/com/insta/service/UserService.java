package com.insta.service;

import com.insta.dao.UserRepo;
import com.insta.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public int add(User user) {
        User user1 = userRepo.save(user);
        return user.getUserId();
    }

    public JSONArray getuser(String id) {
        JSONArray jsonArray = new JSONArray();
        if(id != null && userRepo.findById(Integer.valueOf(id)).isPresent()){
            User user = userRepo.findById(Integer.valueOf(id)).get();
            if (user != null) {
                JSONObject json = getJsonobj(user);
                jsonArray.put(json);
            }
        }
        else {
            List<User> users = userRepo.findAll();
            for (User user: users) {
                JSONObject json = getJsonobj(user);
                jsonArray.put(json);
            }
        }
        return jsonArray;
    }

    private JSONObject getJsonobj(User user) {
        JSONObject jsonObject = new JSONObject(user);
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("fName",user.getfName());
        jsonObject.put("lName",user.getlName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNum",user.getPhoneNum());
        return jsonObject;

    }

    public void update(User newuser,String id) {
       if(userRepo.findById(Integer.valueOf(id)).isPresent()){
           User user = userRepo.findById(Integer.valueOf(id)).get();
           user.setfName(newuser.getfName());
           user.setlName(newuser.getlName());
           user.setEmail(newuser.getEmail());
           user.setAge(newuser.getAge());
           user.setPhoneNum(newuser.getPhoneNum());
           userRepo.save(user);
       }
    }
}
