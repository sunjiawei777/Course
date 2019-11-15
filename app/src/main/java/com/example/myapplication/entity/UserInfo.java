package com.example.myapplication.entity;

import java.io.Serializable;

//entity类与数据库表一一对应
public class UserInfo implements Serializable {
    private int _id;
    private String username;
    private String nickname;
    private String sex;
    private String signature;

    public UserInfo(){

    }

    public UserInfo(int id, String username, String nickname, String sex, String signature) {
        _id = id;
        this.username = username;
        this.nickname = nickname;
        this.sex = sex;
        this.signature = signature;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
