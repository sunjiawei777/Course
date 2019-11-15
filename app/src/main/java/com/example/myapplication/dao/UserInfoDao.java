package com.example.myapplication.dao;

import com.example.myapplication.entity.UserInfo;

import java.util.List;

//dao的作用，完成对一张表的增删改查的原子性操作
public interface UserInfoDao {
    List<UserInfo> select();
    UserInfo select(String username);
    void insert (UserInfo userInfo);
    void delete (UserInfo userInfo);
    void update (UserInfo userInfo);
}
