package com.example.myapplication.service.Impl;

import android.content.Context;

import com.example.myapplication.dao.HistoryDao;
import com.example.myapplication.dao.HistoryDaoImpl;
import com.example.myapplication.entity.History;
import com.example.myapplication.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDao dao;
    public HistoryServiceImpl(Context context){
        dao=new HistoryDaoImpl(context);
    }
    @Override
    public List<History> get(String username) {
        return dao.select(username);
    }

    @Override
    public History get(String username, String title) {
        return dao.select(username,title);
    }

    @Override
    public void save(History history) {
        dao.insert(history);
    }

    @Override
    public void modify(History history) {
        dao.update(history);
    }

    @Override
    public void delete(History history) {
        dao.delete(history);
    }
}
