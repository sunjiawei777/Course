package com.example.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.ExerciseDetail;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDetailAdapter extends RecyclerView.Adapter<ExerciseDetailAdapter.ViewHolder> {
    private List<ExerciseDetail> details;
    private List<String> selectedPos;  //记录点击的位置
    private OnSelectListener onSelectListener; //监听选项的选择事件，用于修改相应的图标

    //创建自定义类
    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView subject,tvA,tvB,tvC,tvD;
        ImageView ivA,ivB,ivC,ivD;

        public ViewHolder(View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.tv_subject);
            tvA=itemView.findViewById(R.id.tv_a);
            tvB=itemView.findViewById(R.id.tv_b);
            tvC=itemView.findViewById(R.id.tv_c);
            tvD=itemView.findViewById(R.id.tv_d);
            ivA=itemView.findViewById(R.id.iv_a);
            ivB=itemView.findViewById(R.id.iv_b);
            ivC=itemView.findViewById(R.id.iv_c);
            ivD=itemView.findViewById(R.id.iv_d);
        }
    }

    public interface OnSelectListener{
        void onSelectA(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectB(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectC(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
        void onSelectD(int position,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD);
    }

    public ExerciseDetailAdapter(List<ExerciseDetail> details,OnSelectListener onSelectListener){
        this.details=details;
        selectedPos=new ArrayList<>();
        this.onSelectListener=onSelectListener;
    }

    @Override
    public ExerciseDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_exercises_detail,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ExerciseDetailAdapter.ViewHolder viewHolder, final int i) {
        ExerciseDetail detail=details.get(i);
        viewHolder.subject.setText(detail.getSubject());
        viewHolder.tvA.setText(detail.getA());
        viewHolder.tvB.setText(detail.getB());
        viewHolder.tvC.setText(detail.getC());
        viewHolder.tvD.setText(detail.getD());

        viewHolder.ivA.setImageResource(R.drawable.ic_exercise_a);
        viewHolder.ivB.setImageResource(R.drawable.ic_exercise_b);
        viewHolder.ivC.setImageResource(R.drawable.ic_exercise_c);
        viewHolder.ivD.setImageResource(R.drawable.ic_exercise_d);

        viewHolder.ivA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos=String.valueOf(i);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                }else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectA(i,viewHolder.ivA,viewHolder.ivB,viewHolder.ivC,viewHolder.ivD);
            }
        });

        viewHolder.ivB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos=String.valueOf(i);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                }else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectB(i,viewHolder.ivA,viewHolder.ivB,viewHolder.ivC,viewHolder.ivD);
            }
        });

        viewHolder.ivC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos=String.valueOf(i);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                }else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectC(i,viewHolder.ivA,viewHolder.ivB,viewHolder.ivC,viewHolder.ivD);
            }
        });

        viewHolder.ivD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos=String.valueOf(i);
                if (selectedPos.contains(pos)){
                    selectedPos.remove(pos);
                }else {
                    selectedPos.add(pos);
                }
                onSelectListener.onSelectD(i,viewHolder.ivA,viewHolder.ivB,viewHolder.ivC,viewHolder.ivD);
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public static void setABCDEnable(boolean value,ImageView ivA,ImageView ivB,ImageView ivC,ImageView ivD){
        ivA.setEnabled(value);
        ivB.setEnabled(value);
        ivC.setEnabled(value);
        ivD.setEnabled(value);
    }

}