package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.model.data.challenge_Database;
import com.example.module_challenge.model.data.challenge_data;
import com.example.module_challenge.model.data.challenge_data_dao;
import com.example.module_challenge.model.symbol.ChallengePunch;
import com.example.module_challenge.ui.fragments_recyclerview.SeventhFragmentAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量
//  1.初始化任务

@Route(path = "/challenge/Challenge_Fragment_SeventhCard_carrying")
public class Challenge_Fragment_SeventhCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_seventhCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_seventhCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_seventhCard_recyclerview_carrying;//RecyclerView的适配器加载
    SeventhFragmentAdapter seventhFragmentAdapter;
    List<ChallengePunch> punchList ;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UI的初始化
        View  view = inflater.inflate(R.layout.fragment_seventh_card_carrying,container,false);
        challenge_seventhCard_progress_carrying = view.findViewById(R.id.challenge_seventhCard_progress_carrying);
        challenge_seventhCard_back_carrying = view.findViewById(R.id.challenge_seventhCard_back_carrying);
        challenge_seventhCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_seventhFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();
        challenge_data data = dao.getChallengeById(7);
        int progress = data.getProgress();
        double db = (double) (progress)/7;
        int num = (int)(db*100);
        challenge_seventhCard_progress_carrying.setText("已完成"+num+"%");

        //RecyclerView
        challenge_seventhCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_seventhFragment_RecyclerView);
        punchList = new ArrayList<>();
        initPunch();
        seventhFragmentAdapter = new SeventhFragmentAdapter(punchList,challenge_seventhCard_progress_carrying);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        challenge_seventhCard_recyclerview_carrying.setAdapter(seventhFragmentAdapter);
        challenge_seventhCard_recyclerview_carrying.setLayoutManager(linearLayoutManager);

        //back_button
        challenge_seventhCard_back_carrying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
                EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
            }
        });


        return view;
    }



    //1.初始化任务
    public void initPunch(){
        //第一个是0
        punchList.add(new ChallengePunch("第一天",R.drawable.challenge_cardfragment_carrying_write,"列出你曾经遇到过的困难","丈夫贵不挠,成败何足论"));
        punchList.add(new ChallengePunch("第二天",R.drawable.challenge_cardfragment_carrying_study,"学习你之前认为很困难的知识","天行健,君子以自强不息"));
        punchList.add(new ChallengePunch("第三天",R.drawable.challenge_cardfragment_carrying_search,"发现当下的困难","精感石没羽,岂云惮险艰"));
        punchList.add(new ChallengePunch("第四天",R.drawable.challenge_cardfragment_carrying_computer,"尝试通过各种渠道解决困难","宝剑锋从磨砺出,梅花香自苦寒来" ));
        punchList.add(new ChallengePunch("第五天",R.drawable.challenge_cardfragment_carrying_friend,"与自己的朋友交流","玉经磨多成器,剑拔沉埋便倚天"));
        punchList.add(new ChallengePunch("第六天",R.drawable.challenge_cardfragment_carrying_moutain,"做曾经不敢去做的事情","雄关漫道真如铁,而今迈步从头越"));
        punchList.add(new ChallengePunch("第七天",R.drawable.challenge_cardfragment_carrying_heart,"拾起一颗永远炽热的心！","长风破浪会有时,直挂云帆济沧海"));


    }
}
