package com.example.module_challenge.fragments;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.logic.model.CurrentChallengeCard;
import com.example.module_challenge.ui.currentchallenge.CurrentChallengeAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//这是一整个挑战Fragment
//功能及实现：
//   1.在里面添加两个RecyclerView，放置挑战信息小卡片.（CurrentChallengeRecyclerView , UChallengeRecyclerView）
//   2.上面的RecyclerView如果没有添加任何卡片的情况下就不会显示出来
//代码目录：
//   0.变量声明
//   1.初始化集合

@Route(path="/challenge/ChallengeFragment_Realtime")
public class ChallengeFragment_Realtime extends Fragment {
    //0.变量声明
    private List<CurrentChallengeCard> currentChallengeCardList;//和RecyclerView加载有关的变量
    private RecyclerView recyclerView;
    private CurrentChallengeAdapter challengeAdapter;
    private TextView challenge_current_text1;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始设置
        View view = inflater.inflate(R.layout.fragment_challenge_realtime,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        //获取实例
        currentChallengeCardList = new ArrayList<CurrentChallengeCard>();
        challengeAdapter = new CurrentChallengeAdapter(currentChallengeCardList,typeface);
        recyclerView = view.findViewById(R.id.challenge_current_recyclerview);
        challenge_current_text1 = view.findViewById(R.id.challenge_current_text1);
        //初始化RecyclerView
        initCurrentCard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setAdapter(challengeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        //设置瘦金体
        challenge_current_text1.setTypeface(typeface);

        return view;
    }

    //1.初始化集合
    public void initCurrentCard(){
        currentChallengeCardList.add(new CurrentChallengeCard("平旦而出挑战","一年之计在于春，一日之计在于晨",R.drawable.challenge_card_image1,2));  //早起
        currentChallengeCardList.add(new CurrentChallengeCard("收拾行宫挑战","花径不曾缘客扫，蓬门今始为君开",1,2));  //打扫卫生
        currentChallengeCardList.add(new CurrentChallengeCard("六艺进修挑战","发挥六艺无遗精，考黜百家如拉朽",1,2));   //学习知识挑战
        currentChallengeCardList.add(new CurrentChallengeCard("佛性禅心挑战","非淡泊无以明志，非宁静无以致远",1,2));   //安静
        currentChallengeCardList.add(new CurrentChallengeCard("五谷为养挑战","春种一粒粟，秋收万颗子",1,2));   //绿色饮食挑战
        currentChallengeCardList.add(new CurrentChallengeCard("广结益友挑战","相知无远近，万里尚为邻",1,2));   //人际关系挑战
        currentChallengeCardList.add(new CurrentChallengeCard("冰消冻释挑战","穷且益坚，不坠青云之志",1,2));   //解决困难
    }
}
