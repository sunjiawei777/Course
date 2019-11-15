package com.example.myapplication.dao;

import com.example.myapplication.entity.History;

import java.util.List;

public interface HistoryDao {
    List<History> select(String username);
    History select(String username,String title);
    void insert(History history);
    void update(History history);
    void delete(History history);
}
