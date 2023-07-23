package com.insta.Util;

import com.insta.model.User;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static boolean isValidemail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidphoneNum(String phoneNum) {
        Pattern p = Pattern.compile("^\\d{10}$");

        Matcher m = p.matcher(phoneNum);

        return (m.matches());
    }
    public static User setUser(String requser) {
        JSONObject json = new JSONObject(requser);
        User user = new User();
        user.setfName(json.getString("firstname"));
        if(json.has("lastname")) {
            user.setlName(json.getString("lastname"));
        }
        if(json.has("userId")) {
            user.setUserId(json.getInt("userId"));
        }
        user.setAge(json.getInt("age"));
        user.setEmail(json.getString("email"));
        user.setPhoneNum(json.getString("phoneNumber"));
        return user;
    }
    public static JSONObject validateUser(String requser) {
        JSONObject jsonObject = new JSONObject(requser);
        JSONObject error = new JSONObject();
        if(jsonObject.has("firstname")){
            String name = jsonObject.getString("firstname");
            if(name.isEmpty()){
                error.put("firstname","enter first name");
            }
        }else {
            error.put("firstname","Missing parameter");
        }
        if(!jsonObject.has("age")){
            error.put("age","Missing parameter");
        }
        if(jsonObject.has("email")){
            String email = jsonObject.getString("email");
            if(!CommonUtils.isValidemail(email)){
                error.put("email","Please enter a valid email");
            }
        }else {
            error.put("email","Missing parameter");
        }
        if(jsonObject.has("phoneNumber")){
            String phone = jsonObject.getString("phoneNumber");
            if(!CommonUtils.isValidphoneNum(phone)){
                error.put("phoneNumber","Please enter a 10 digit phone-number");
            }
        }else {
            error.put("phoneNumber","Missing parameter");
        }
        return error;
    }
}
