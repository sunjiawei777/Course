package com.example.myapplication.service.Impl;

import android.content.Context;

import com.example.myapplication.dao.UserInfoDao;
import com.example.myapplication.dao.UserInfoDaoImpl;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao dao;

    public UserInfoServiceImpl(Context context){
        dao = new UserInfoDaoImpl(context);
    }
    @Override
    public UserInfo get(String username) {
        return dao.select(username);
    }

    @Override
    public void save(UserInfo userInfo) {
        dao.insert(userInfo);
    }

    @Override
    public void modify(UserInfo userInfo) {
        dao.update(userInfo);
    }
}
