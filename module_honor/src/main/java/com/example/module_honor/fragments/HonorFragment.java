package com.example.module_honor.fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_honor.R;


//第三界面:石以砥焉-青史界面
//两个主要信息得数据统计
//功能:

//代码目录:
//  0.声明变量
//  1.点击事件
@Route(path = "/honor/HonorFragment")
public class HonorFragment extends Fragment {
    //0.声明变量

    TextView honor_fragment_textview1;
    TextView honor_fragment_share_text;
    TextView honor_fragment_consecutiveDay_text;
    TextView honor_fragment_consecutiveDay_KnottedRope;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_honor,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");

        //变量实例化
        honor_fragment_textview1 = view.findViewById(R.id.honor_fragment_textview1);
        honor_fragment_share_text = view.findViewById(R.id.honor_fragment_share_text);
        honor_fragment_consecutiveDay_text = view.findViewById(R.id.honor_fragment_consecutiveDay_text);
        honor_fragment_consecutiveDay_KnottedRope = view.findViewById(R.id.honor_fragment_consecutiveDay_KnottedRope);
        honor_fragment_share_text.setTypeface(typeface);
        honor_fragment_textview1.setTypeface(typeface);
        honor_fragment_consecutiveDay_text.setTypeface(typeface);
        honor_fragment_consecutiveDay_KnottedRope.setTypeface(typeface);


        return view;
    }
}
