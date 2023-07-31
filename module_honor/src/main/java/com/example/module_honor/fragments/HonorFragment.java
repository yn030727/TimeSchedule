package com.example.module_honor.fragments;

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
import com.example.module_honor.R;
import com.example.module_honor.model.symbol.ChallengeHonor;
import com.example.module_honor.model.symbol.ConsecutiveDay;
import com.example.module_honor.ui.ChallengeProgressAdapter;
import com.example.module_honor.ui.ConsecutiveDayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventHonor_Challenge_Progress;


//第三界面:石以砥焉-青史界面
//两个主要信息得数据统计
//功能:

//代码目录:
//  0.声明变量
//  1.初始化集合
//  2.订阅者事件
@Route(path = "/honor/HonorFragment")
public class HonorFragment extends Fragment {
    //0.声明变量

    TextView honor_fragment_textview1;
    TextView honor_fragment_share_text;
    TextView honor_fragment_consecutiveDay_text;
    TextView honor_fragment_consecutiveDay_KnottedRope;
    TextView honor_fragment_textview1_challenge;
    TextView honor_fragment_share_text_challenge;
    TextView honor_fragment_challengeProgress_text;
    RecyclerView honor_fragment_consecutiveDay_recyclerView;
    ConsecutiveDayAdapter consecutiveDayAdapter;
    List<ConsecutiveDay> consecutiveDayList;
    int[] honor_challenge_progress;
    int challenge_progress;
    //挑战的RecyclerView
    RecyclerView honor_fragment_challengeProgress_recyclerView;
    ChallengeProgressAdapter challengeProgressAdapter;
    List<ChallengeHonor> challengeHonorList;

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
        honor_fragment_share_text_challenge = view.findViewById(R.id.honor_fragment_share_text_challenge);
        honor_fragment_textview1_challenge = view.findViewById(R.id.honor_fragment_textview1_challenge);
        honor_fragment_challengeProgress_text = view.findViewById(R.id.honor_fragment_challengeProgress_text);
        honor_fragment_textview1_challenge.setTypeface(typeface);
        honor_fragment_share_text_challenge.setTypeface(typeface);
        honor_fragment_share_text.setTypeface(typeface);
        honor_fragment_textview1.setTypeface(typeface);
        honor_fragment_consecutiveDay_text.setTypeface(typeface);
        honor_fragment_consecutiveDay_KnottedRope.setTypeface(typeface);
        //初始化EventBus
        EventBus.getDefault().register(this);
        //RecyclerView
        consecutiveDayList = new ArrayList<>();
        initConsecutiveDayList();
        honor_fragment_consecutiveDay_recyclerView = view.findViewById(R.id.honor_fragment_consecutiveDay_recyclerView);
        consecutiveDayAdapter = new ConsecutiveDayAdapter(consecutiveDayList,typeface);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        honor_fragment_consecutiveDay_recyclerView.setAdapter(consecutiveDayAdapter);
        honor_fragment_consecutiveDay_recyclerView.setLayoutManager(linearLayoutManager);
        //挑战的RecyclerView
        challengeHonorList = new ArrayList<>();
        initChallengeProgressList();
        honor_fragment_challengeProgress_recyclerView = view.findViewById(R.id.honor_fragment_challengeProgress_recyclerView);
        challengeProgressAdapter = new ChallengeProgressAdapter(challengeHonorList,typeface,honor_challenge_progress,challenge_progress);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        honor_fragment_challengeProgress_recyclerView.setAdapter(challengeProgressAdapter);
        honor_fragment_challengeProgress_recyclerView.setLayoutManager(linearLayoutManager1);

        double db = (double) challenge_progress/7;
        int num = (int)(db*100);
        honor_fragment_challengeProgress_text.setText(num+"%");
        honor_fragment_challengeProgress_text.setTypeface(typeface);


        return view;
    }

    //1.初始化集合
    //初始化打卡数据的集合
    public void initConsecutiveDayList(){
        //之后这里需要做网络请求的判断，以及处理，查看自己连续签到了几天
        //并且根据签到的天数传进去不同的颜色
        //在RecyclerView里面通过setBackgroundResource可以改变(可以参考挑战打卡界面)
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"一日结绳","记录1天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"三日结绳","记录3天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"七日结绳","记录7天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"一旬结绳","记录10天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"三旬结绳","记录30天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"百日结绳","记录100天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"半载结绳","记录182天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image_nocomplete,"结绳而治","记录365天"));
        consecutiveDayList.add(new ConsecutiveDay(R.drawable.honor_fragment_consecutiveday_image2_nocomplete,"史家绝唱","记录1000天"));
    }
    //初始化挑战记录集合
    public void initChallengeProgressList(){
        challengeHonorList.add(new ChallengeHonor("平旦而出挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("收拾行宫挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("六艺进修挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("佛性禅心挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("五谷为养挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("广结益友挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
        challengeHonorList.add(new ChallengeHonor("冰消冻释挑战",R.drawable.honor_fragment_challengeprogress_image_nocomplete));
    }




    //2.订阅者事件
    //接收到事件之后执行，初始化
    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public void HonorChallengeProgress(EventHonor_Challenge_Progress eventHonorChallengeProgress){
        Log.d("Ning_Honor " ,"HonorChallengeProgress EventBus start");
        honor_challenge_progress = eventHonorChallengeProgress.getChallenges();
        challenge_progress = eventHonorChallengeProgress.getProgress();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
