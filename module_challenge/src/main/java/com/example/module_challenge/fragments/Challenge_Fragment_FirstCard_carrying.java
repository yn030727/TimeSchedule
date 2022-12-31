package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.logic.data.challenge_Database;
import com.example.module_challenge.logic.data.challenge_data_dao;

import org.greenrobot.eventbus.Subscribe;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量

@Route(path = "/challenge/Challenge_Fragment_FirstCard_carrying")
public class Challenge_Fragment_FirstCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_firstCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_firstCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_firstCard_recyclerview_carrying;//RecyclerView的适配器加载



    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_first_card_carrying,container,false);
        challenge_firstCard_progress_carrying = view.findViewById(R.id.challenge_firstCard_progress_carrying);
        challenge_firstCard_back_carrying = view.findViewById(R.id.challenge_firstCard_back_carrying);
        challenge_firstCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_firstFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();


        return view;
    }
}
