package com.example.myapplication.service;

import com.example.myapplication.entity.History;

import java.util.List;

public interface HistoryService {
    List<History> get(String username);
    History get(String username,String title);
    void save(History history);
    void modify(History history);
    void delete(History history);
}
