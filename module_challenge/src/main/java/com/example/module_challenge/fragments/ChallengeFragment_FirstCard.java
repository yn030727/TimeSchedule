package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;

//这是第一个挑战的详细介绍界面(之后几个与其类似，将不会有功能导图)
//功能及实现：
//代码目录：
//   0.初始化Fragment的相关操作
//   1.back图片和接下挑战按钮的功能介绍



@Route(path = "/challenge/ChallengeFragment_FirstCard")
public class ChallengeFragment_FirstCard extends Fragment implements View.OnClickListener {
    TextView challenge_firstCard_saying;
    ImageView challenge_firstCard_back;
    Button challenge_firstCard_button;
    //当前挑战项目是否被挑战，如果已经点击了同意挑战，那么就将这个值变为true
    //当挑战结束后，再将这个值重新变为false
    Boolean challenge_firstCard_status = false; //当前是否正在挑战中（关于青史界面是需要已经完成一次挑战的信息，不用写在这里）


    //0.初始化Fragment的相关操作
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_card,container,false);
        //设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");


        challenge_firstCard_saying = view.findViewById(R.id.challenge_firstcard_saying);
        challenge_firstCard_saying.setTypeface(typeface);
        challenge_firstCard_back = view.findViewById(R.id.challenge_firstCard_back);
        challenge_firstCard_button = view.findViewById(R.id.challenge_firstCard_button);


        challenge_firstCard_back.setOnClickListener(this);
        challenge_firstCard_button.setOnClickListener(this);
        return view;
    }




    //1.两个相关的点击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.challenge_firstCard_back) {
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }else if(v.getId() == R.id.challenge_firstCard_button){
            challenge_firstCard_status = true;
            Log.d("Ning_Challenge", "firstCard : " + challenge_firstCard_status);
            //点击之后要更新数据库里面的内容
            //先查看数据库里面的当前信息状况： 1.没有 说明第一次挑战，那么添加到数据库中  2.false 已经完成过一次或多次该挑战，变为true  3.true  正在进行挑战当中，无需进行接下来的操作

        }
    }
}
