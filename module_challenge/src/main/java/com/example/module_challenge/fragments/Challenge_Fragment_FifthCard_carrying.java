package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.content.pm.LabeledIntent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.logic.data.challenge_Database;
import com.example.module_challenge.logic.data.challenge_data;
import com.example.module_challenge.logic.data.challenge_data_dao;
import com.example.module_challenge.logic.model.ChallengePunch;
import com.example.module_challenge.ui.fragments_recyclerview.FifthFragmentAdapter;
import com.example.module_challenge.ui.fragments_recyclerview.FourthFragmentAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;


//这是第一个挑战开始挑战后的打卡界面(之后几个类似，不会有功能导图)
//功能及实现：
//代码目录：
//  0.声明变量
//  1.初始化任务

@Route(path = "/challenge/Challenge_Fragment_FifthCard_carrying")
public class Challenge_Fragment_FifthCard_carrying extends Fragment {
    //0.声明变量
    TextView challenge_fifthCard_progress_carrying;//界面内的进度信息文本介绍
    ImageView challenge_fifthCard_back_carrying;//返回主界面
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    RecyclerView challenge_fifthCard_recyclerview_carrying;//RecyclerView的适配器加载
    FifthFragmentAdapter fifthFragmentAdapter;
    List<ChallengePunch> punchList ;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UI的初始化
        View  view = inflater.inflate(R.layout.fragment_fifth_card_carrying,container,false);
        challenge_fifthCard_progress_carrying = view.findViewById(R.id.challenge_fifthCard_progress_carrying);
        challenge_fifthCard_back_carrying = view.findViewById(R.id.challenge_fifthCard_back_carrying);
        challenge_fifthCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_fifthFragment_RecyclerView);
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();
        challenge_data data = dao.getChallengeById(5);
        int progress = data.getProgress();
        double db = (double) (progress)/7;
        int num = (int)(db*100);
        challenge_fifthCard_progress_carrying.setText("已完成"+num+"%");

        //RecyclerView
        challenge_fifthCard_recyclerview_carrying = view.findViewById(R.id.challenge_yourCard_fifthFragment_RecyclerView);
        punchList = new ArrayList<>();
        initPunch();
        fifthFragmentAdapter = new FifthFragmentAdapter(punchList,challenge_fifthCard_progress_carrying);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        challenge_fifthCard_recyclerview_carrying.setAdapter(fifthFragmentAdapter);
        challenge_fifthCard_recyclerview_carrying.setLayoutManager(linearLayoutManager);

        //back_button
        challenge_fifthCard_back_carrying.setOnClickListener(new View.OnClickListener() {
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
        punchList.add(new ChallengePunch("第一天",R.drawable.challenge_cardfragment_carrying_junk,"将自己的垃圾食品藏起来","养生之道，莫先于饮食"));
        punchList.add(new ChallengePunch("第二天",R.drawable.challenge_cardfragment_carrying_salad,"来一份土豆沙拉","桑蚕五谷十分收，官司无甚差科"));
        punchList.add(new ChallengePunch("第三天",R.drawable.challenge_cardfragment_carrying_greenfood,"为自己做一份绿色的健康餐","五谷垂颖，桑麻铺棻"));
        punchList.add(new ChallengePunch("第四天",R.drawable.challenge_cardfragment_carrying_fruit,"榨杯橙汁，补充维生素","槁死三彭仇，澡换五谷肠" ));
        punchList.add(new ChallengePunch("第五天",R.drawable.challenge_cardfragment_carrying_doujiang,"早起喝一杯豆浆吧","感通玄贶时无比，五谷精华从兹始"));
        punchList.add(new ChallengePunch("第六天",R.drawable.challenge_cardfragment_carrying_greenfood,"为自己做一份绿色的健康餐","五谷垂颖，桑麻铺棻"));
        punchList.add(new ChallengePunch("第七天",R.drawable.challenge_cardfragment_carrying_greenday,"轻食五谷配酸奶，完美的一周","五谷为养，五蓄为助"));


    }
}
