package com.example.myapplication.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.HistoryActivity;
import com.example.myapplication.activity.LoginActivity;
import com.example.myapplication.activity.SettingActivity;
import com.example.myapplication.activity.UserInfoActivity;

public class MyInfoFragment extends Fragment {
    private Context mContext;
    private TextView tvUsername;
    private LinearLayout headLayout;
    private RelativeLayout historyLayout, settingLayout ;
    private boolean isLogin;  // 是否登录的标志位

    public MyInfoFragment() { }

    public static MyInfoFragment newInstance(){
        return new MyInfoFragment();
    }

    //获取登录的状态
    private boolean checkLoginStatus(){
        SharedPreferences sp=mContext.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin",false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //获取fragment的父Activity，以及目前的登录状态
        this.mContext=getContext();
        this.isLogin=checkLoginStatus();
        //获取fragment界面上需要处理的控件对象
        View view=inflater.inflate(R.layout.fragment_my_info,container,false);
        tvUsername=view.findViewById(R.id.tv_username);
        setUsername(isLogin);

        headLayout = view.findViewById(R.id.ll_head);
        historyLayout = view.findViewById(R.id.rl_history);
        settingLayout = view.findViewById(R.id.rl_setting);
        //设置事件监听器
        headLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin){
                    Intent intent=new Intent(mContext, UserInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(mContext, LoginActivity.class);
                    startActivityForResult(intent,1);
                }
            }
        });

        historyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin){
                    Intent intent = new Intent(mContext, HistoryActivity.class);
                    startActivityForResult(intent, 1);

                }else {
                    Toast.makeText(mContext,"请先登录",Toast.LENGTH_LONG).show();
                }
            }
        });

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin){
                    Intent intent = new Intent(mContext, SettingActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mContext,"请先登录",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void setUsername(boolean isLogin){
        if (isLogin){
            tvUsername.setText(readLoginInfo());
        }else {
            tvUsername.setText("点击登录");
        }
    }

    private String readLoginInfo(){
        SharedPreferences sp=mContext.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        return sp.getString("LoginUser","");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode== Activity.RESULT_OK && data!=null){
            isLogin=data.getBooleanExtra("isLogin",false);
            setUsername(isLogin);
        }
    }
}
