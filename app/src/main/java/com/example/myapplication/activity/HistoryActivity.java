package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.entity.History;
import com.example.myapplication.service.HistoryService;
import com.example.myapplication.service.Impl.HistoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private List<History> histories;
    private HistoryService historyService = new HistoryServiceImpl(this);

    //控件及adapter
    private RecyclerView rvHistory;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        initToolBar();
        initData();
        initView();
    }

    private void initView() {
        rvHistory = findViewById(R.id.rv_course_history);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(manager);
        adapter = new HistoryAdapter(histories);
        rvHistory.setAdapter(adapter);
    }

    private void initData() {
        SharedPreferences pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = pref.getString("LoginUser", "");


        histories = new ArrayList<>();

        //根据用户名查询播放记录

        try {
            histories = historyService.get(username);
        } catch (NullPointerException e) {
        }
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("历史记录");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryActivity.this.finish();
            }
        });
    }
}
