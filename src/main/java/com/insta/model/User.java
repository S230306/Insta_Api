package com.insta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "f_Name")
    private String fName;
    @Column(name = "l_Name")
    private String lName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_Num")
    private String phoneNum;
    public User(){

    }

    public User(Integer userId, String fName, String lName, Integer age, String email, String phoneNum) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
