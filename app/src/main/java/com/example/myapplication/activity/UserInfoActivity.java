package com.example.myapplication.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.R;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.service.Impl.UserInfoServiceImpl;
import com.example.myapplication.service.UserInfoService;
import com.example.myapplication.utils.SharedUtils;
import com.example.myapplication.utils.StatusUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    //1.定义界面上的控件对象
    private TextView tvNickname,tvSignature,tvUsername,tvSex;
    private RelativeLayout nicknameLayout,signatureLayout,sexLayout;

    // 2.定义所需的变量
    private String spUsername;
    private UserInfo userInfo;
    private UserInfoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setImmersionMode(this);
        setContentView(R.layout.activity_user_info);

        //3.设置标题栏（可选）
        StatusUtils.initToolbar(this,"个人信息",true,false);
        //4.从数据库、网络、intent、或存储中获取数据，初始化界面控件（可选）
        initData();
        //5.获取所有控件对象，设置监听器（必须）
        initView();
    }

    private void initData() {
        spUsername = SharedUtils.readValue(this,"loginUser");

        service = new UserInfoServiceImpl(this);
        userInfo = service.get(spUsername);
        userInfo = readFromInternal();
        if(userInfo==null){
            userInfo=new UserInfo();
            userInfo.setUsername(spUsername);
            userInfo.setNickname("npc");
            userInfo.setSignature("npc");
            userInfo.setSex("男");
            service.save(userInfo);
            saveToInternal(userInfo);
        }
    }
    private void initView(){
        //1.获取控件对象
        tvUsername = findViewById(R.id.tv_username);
        tvNickname = findViewById(R.id.tv_nickname);
        tvSex = findViewById(R.id.tv_sex);
        tvSignature = findViewById(R.id.tv_signature);

        nicknameLayout = findViewById(R.id.rl_nickname);
        sexLayout = findViewById(R.id.rl_sex);
        signatureLayout = findViewById(R.id.rl_signature);

        //2.设置数据库获取的数据
        tvUsername.setText(userInfo.getUsername());
        tvNickname.setText(userInfo.getNickname());
        tvSex.setText(userInfo.getSex());
        tvSignature.setText(userInfo.getSignature());
        //3.设置监听器
        nicknameLayout.setOnClickListener(this);
        sexLayout.setOnClickListener(this);
        signatureLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_nickname:
            modifyNickname();
            break;
            case R.id.rl_sex:
            modifySex();
            break;
            case R.id.rl_signature:
            modifySignature();
            break;
        }
    }


    private void modifyNickname() {
        //1.获取已有的内容
        String nickname = tvNickname.getText().toString();
        //2.根据需要传递数据到下一个Activity
        Intent intent = new Intent(UserInfoActivity.this, ModifyUserInfoActivity.class);
        //Bundle对象用于传递有明确类型的简单类型和复杂数据类型的数据（简单类型数据也可以用Intent传入）
        //Bundle需要加载到Intent中才能传递
        Bundle bundle = new Bundle();
        bundle.putString("title","设置昵称");//标题栏的标题
        bundle.putString("value",nickname);//内容
        bundle.putInt("flag",1);//用于区分修改昵称还是签名
        intent.putExtras(bundle);
        //3.启动下一个界面
        startActivityForResult(intent,1);
    }

    private void modifySignature() {
        String signature = tvSignature.getText().toString();
        Intent intent = new Intent(UserInfoActivity.this,ModifyUserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title","设置签名");
        bundle.putString("value",signature);
        bundle.putInt("flag",1);
        intent.putExtras(bundle);
        startActivityForResult(intent,1);
    }

    private void modifySex() {
        final  String[] datas={"男","女"};
        String sex = tvSex.getText().toString();
        //获取性别所在的索引
        final List<String> sexs = Arrays.asList(datas);
        int selected = sexs.indexOf(sex);
        new AlertDialog.Builder(this)
                .setTitle("性别")
                .setSingleChoiceItems(datas, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String sex = datas[which];
                        tvSex.setText(sex);
                        userInfo.setSex(sex);
                        service.modify(userInfo);
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //1.对空数据，返回异常做判断
        if(data==null || resultCode!=RESULT_OK){
            Toast.makeText(this,"未知错误",Toast.LENGTH_SHORT).show();
            return;
        }
        //2.根据requestCode进行对应的保存
        //2.1获取data数据
        if (requestCode == 1){
            //2.2设置userInfo对应的属性值，更新界面对应的控件内容
            String value = data.getStringExtra("nickname");
            tvNickname.setText(value);
            userInfo.setNickname(value);
        }else if (requestCode == 2){
            String value = data.getStringExtra("signature");
            tvSignature.setText(value);
            userInfo.setSignature(value);
        }
        //2.3保存到数据库
        service.modify(userInfo);
    }

    private static final String FILE_NAME = "userinfo.txt";
    //2.内部存储
    //保存
    private void saveToInternal(UserInfo userInfo){
        //内部存储目录：data/data/包名/files/
        try{
            //1.打开文件输出流
            FileOutputStream out = this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            //2.创建BufferedWriter对象
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            //3.写入数据
            writer.write(JSON.toJSONString(userInfo));
            //4.关闭输出流;
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //读取
    private UserInfo readFromInternal(){
        UserInfo userInfo = null;
        try{
            FileInputStream in = this.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data =reader.readLine();
            userInfo = JSON.parseObject(data,UserInfo.class);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return userInfo;
    }
}
