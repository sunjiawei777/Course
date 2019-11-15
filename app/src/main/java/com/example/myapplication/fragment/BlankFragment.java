package com.example.myapplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ExerciseAdapter;
import com.example.myapplication.entity.Exercise;
import com.example.myapplication.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private static final String ARG_PARAM = "param";

    private String mParam;
    private ListView lvExercise;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }
    String[] data={"软件1711","软件1721"};
    List<Exercise> exercises;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        //准备的列表
//        exercises = initData();

        //1.获取列表控件
        lvExercise = view.findViewById(R.id.list_view);
        //2.创建集合类控件需要的Adapter数据适配器（作用：UI与ArrayLt数据的桥梁）
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getActivity(),android.R.layout.simple_list_item_1,data);
        ExerciseAdapter adapter = new ExerciseAdapter(getActivity(),exercises);
        //3.设置ListView的Adapter
        lvExercise.setAdapter(adapter);

        //4.ListView的item事件监听
        lvExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise exercise = (Exercise) parent.getItemAtPosition(position);
            }
        });

        return view;
    }
    private void initData(){
        exercises = new ArrayList<>();

        try{
            //1.从assets目录中获取资源的输入流
            InputStream input=getResources().getAssets().open("exercise_title.json");
            //2.将Inputstream转为String
            String json= IOUtils.convert(input, StandardCharsets.UTF_8);
            //3.利用fastjson将字符串转为字符集
            exercises=IOUtils.convert(json,Exercise.class);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void genreateData() {
        exercises = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Exercise exercise = new Exercise();
            exercise.setId(i + 1);
            switch (i) {
                case 0:
                    exercise.setTitle("第一章 Android基础入门");
                    exercise.setSubTitle("攻击5题");
                    exercise.setBackground(R.drawable.user);
                    break;
                case 1:
                    exercise.setTitle("第2章 探究活动Activity");
                    exercise.setSubTitle("共计5题");
                    break;
                case 2:
                    exercise.setTitle("第3章 Android UI开发");
                    exercise.setSubTitle("共计5题");
                    break;
                case 3:
                    exercise.setTitle("第4章 探究碎片Fragment");
                    exercise.setSubTitle("共计5题");
                    break;
                case 4:
                    exercise.setTitle("第5章 广播接收者");
                    exercise.setSubTitle("共计5题");
                    break;
                case 5:
                    exercise.setTitle("第6章 数据存储");
                    exercise.setSubTitle("共计5题");
                    break;
                case 6:
                    exercise.setTitle("第7章 内容提供者");
                    exercise.setSubTitle("共计5题");
                    break;
                case 7:
                    exercise.setTitle("第8章 手机多媒体");
                    exercise.setSubTitle("共计5题");
                    break;
                case 8:
                    exercise.setTitle("第9章 网络编程");
                    exercise.setSubTitle("共计5题");
                    break;
                case 9:
                    exercise.setTitle("第10章 服务");
                    exercise.setSubTitle("共计5题");
                    break;
                case 10:
                    exercise.setTitle("第11章 基于位置的服务");
                    exercise.setSubTitle("共计5题");
                    break;
                case 11:
                    exercise.setTitle("第12章 Material Design实战");
                    exercise.setSubTitle("共计5题");
                    break;
                case 12:
                    exercise.setTitle("第13章 高级技巧");
                    exercise.setSubTitle("共计5题");
                    break;
                case 13:
                    exercise.setTitle("第14章 开发天气App");
                    exercise.setSubTitle("共计5题");
                    break;
                case 14:
                    exercise.setTitle("第15章 项目发布上线");
                    exercise.setSubTitle("共计5题");
                    break;
                default:
                    break;
            }
            exercises.add(exercise);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
