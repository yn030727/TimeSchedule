package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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
import com.example.module_challenge.logic.data.challenge_Database;
import com.example.module_challenge.logic.data.challenge_data;
import com.example.module_challenge.logic.data.challenge_data_dao;
import com.example.module_challenge.logic.model.CurrentChallengeCard;
import com.example.module_challenge.logic.model.YouChallengeCard;
import com.example.module_challenge.ui.currentchallenge.CurrentChallengeAdapter;
import com.example.module_challenge.ui.uchallenge.YouChallengeAdapter;

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
//   2.判断是否有挑战正在进行中

@Route(path="/challenge/ChallengeFragment_Realtime")
public class ChallengeFragment_Realtime extends Fragment {
    //0.变量声明
    private List<CurrentChallengeCard> currentChallengeCardList;//和RecyclerView加载有关的变量
    private RecyclerView recyclerView;
    private CurrentChallengeAdapter challengeAdapter;
    private TextView challenge_current_text1;
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    private List<YouChallengeCard> youChallengeCardList;//和RecyclerView2加载有关的变量
    private RecyclerView you_recyclerView;
    private TextView challenge_your_text;
    private YouChallengeAdapter youChallengeAdapter;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //(1)初始设置
        View view = inflater.inflate(R.layout.fragment_challenge_realtime,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        //(2)获取实例
        currentChallengeCardList = new ArrayList<CurrentChallengeCard>();
        challengeAdapter = new CurrentChallengeAdapter(currentChallengeCardList,typeface);
        recyclerView = view.findViewById(R.id.challenge_current_recyclerview);

        youChallengeCardList = new ArrayList<YouChallengeCard>();
        you_recyclerView = view.findViewById(R.id.challenge_your_recyclerView);
        challenge_your_text = view.findViewById(R.id.challenge_your_text);

        challenge_current_text1 = view.findViewById(R.id.challenge_current_text1);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();

        //设置瘦金体
        challenge_current_text1.setTypeface(typeface);


        //(3)初始化RecyclerView
            //1.初始化所有的挑战卡片
        initCurrentCard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setAdapter(challengeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

            //2.初始化接下挑战的挑战卡片

        if(JudgmentChallenges()){
            challenge_your_text.setText(" 实 时 挑 战 ");
            challenge_your_text.setTypeface(typeface);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext());
            linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
            youChallengeAdapter = new YouChallengeAdapter(youChallengeCardList);
            you_recyclerView.setAdapter(youChallengeAdapter);
            you_recyclerView.setLayoutManager(linearLayoutManager1);
        }





        return view;
    }

    //1.初始化集合
    public void initCurrentCard(){
        currentChallengeCardList.add(new CurrentChallengeCard("平旦而出挑战","一年之计在于春，一日之计在于晨",R.drawable.challenge_card_image5,2,"/challenge/FirstCardActivity"));  //早起
        currentChallengeCardList.add(new CurrentChallengeCard("收拾行宫挑战","茅檐长扫净无苔，花木成畦手自栽",R.drawable.challenge_card_image2,2,"/challenge/FirstCardActivity"));  //打扫卫生
        currentChallengeCardList.add(new CurrentChallengeCard("六艺进修挑战","发挥六艺无遗精，考黜百家如拉朽",R.drawable.challenge_card_image4,2,"/challenge/FirstCardActivity"));   //学习知识挑战
        currentChallengeCardList.add(new CurrentChallengeCard("佛性禅心挑战","非淡泊无以明志，非宁静无以致远",R.drawable.challenge_card_image6,2,"/challenge/FirstCardActivity"));   //安静
        currentChallengeCardList.add(new CurrentChallengeCard("五谷为养挑战","春种一粒粟，秋收万颗子",R.drawable.challenge_card_image3,2,"/challenge/FirstCardActivity"));   //绿色饮食挑战
        currentChallengeCardList.add(new CurrentChallengeCard("广结益友挑战","相知无远近，万里尚为邻",R.drawable.challenge_card_image8,2,"/challenge/FirstCardActivity"));   //人际关系挑战
        currentChallengeCardList.add(new CurrentChallengeCard("冰消冻释挑战","穷且益坚，不坠青云之志",R.drawable.challenge_card_image1,2,"/challenge/FirstCardActivity"));   //解决困难
    }

    //2.判断是否有挑战正在进行中
    //查询数据库中的所有元素，如果有则返回true，没有则返回false
    public boolean JudgmentChallenges(){
        Log.d("Ning_YourCard", " Begin to JudgmentChallenges");
        //清空之前的List集合
        youChallengeCardList.clear();
        //获取整个List集合
        List<challenge_data> dataList = dao.getChallengeList();
        boolean temp = false;
        for(int i=0 ; i<dataList.size() ; i++){
            challenge_data data = dataList.get(i);
            //说明在挑战中
            Log.d("Ning_YourCard : " , data.id + "");
            if(data.challenge == 1){
                Log.d("Ning_YourCard : ", data.id + " Victory" );
                youChallengeCardList.add(JudgmentWhichChallenge(data.id,data.progress));
                temp = true;
            }
        }
        return temp;
    }
    public YouChallengeCard JudgmentWhichChallenge(int index , int progress){
        //52465381
        if(index == 1){
            return new YouChallengeCard("平旦而出挑战",progress,R.drawable.challenge_card_image5);
        }else if(index == 2){
            return new YouChallengeCard("收拾行宫挑战",progress,R.drawable.challenge_card_image2);
        }else if(index == 3){
            return new YouChallengeCard("六艺进修挑战",progress,R.drawable.challenge_card_image4);
        }else if(index == 4){
            return new YouChallengeCard("佛性禅心挑战",progress,R.drawable.challenge_card_image6);
        }else if(index == 5){
            return new YouChallengeCard("五谷为养挑战",progress,R.drawable.challenge_card_image3);
        }else if(index == 6){
            return new YouChallengeCard("广结益友挑战",progress,R.drawable.challenge_card_image8);
        }else{
            return new YouChallengeCard("冰消冻释挑战",progress,R.drawable.challenge_card_image1);
        }
    }
}
