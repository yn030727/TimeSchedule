package com.example.module_editscheudle;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_editscheudle.model.schedule;
import com.example.module_editscheudle.ui.EditScheduleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eventbus.EventChallenge_CardActivity_Back;
import eventbus.EventEditSchedule_MainActivity_Back;

//界面:创建计划界面
//将计划添加到集合当中，并交给module_calendar
//功能:
//代码目录:
//  0.声明变量，基础设置
//  1.点击事件
//  2.默认分类按钮点击变色事件
@Route(path = "/editschedule/editschedulefragment")
public class EditScheduleFragment extends Fragment implements View.OnClickListener {


    //0.声明变量，基础设置
    TextView edit_schedule_create_textView;
    TextView edit_schedule_cancel_textView;
    ConstraintLayout edit_schedule_add_function;
    TextView edit_schedule_add_function_textView;
    TextView edit_schedule_add_normal;
    TextView edit_schedule_add_study;
    TextView edit_schedule_add_healthy;
    TextView edit_schedule_add_sport;
    TextView edit_schedule_add_life;
    TextView edit_schedule_add_money;
    TextView edit_schedule_add_cur_text;
    ArrayList<schedule> scheduleArrayList;
    ArrayList<schedule> curArrayList;
    HashMap<String , Boolean> stringscheduleHashMap;
    RecyclerView scheduleRecyclerView;
    Typeface typeface;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editschedule_fragment , container , false);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        edit_schedule_create_textView = view.findViewById(R.id.editschedule_create_textView);
        edit_schedule_cancel_textView = view.findViewById(R.id.editschedule_cancel_textView);
        edit_schedule_add_function = view.findViewById(R.id.editschedule_add_function);
        edit_schedule_add_function_textView = view.findViewById(R.id.editschedule_add_function_textView);
        edit_schedule_add_normal = view.findViewById(R.id.editschedule_add_normal);
        edit_schedule_add_study = view.findViewById(R.id.editschedule_add_study);
        edit_schedule_add_healthy = view.findViewById(R.id.editschedule_add_healthy);
        edit_schedule_add_sport = view.findViewById(R.id.editschedule_add_sport);
        edit_schedule_add_life = view.findViewById(R.id.editschedule_add_life);
        edit_schedule_add_money = view.findViewById(R.id.editschedule_add_money);
        edit_schedule_add_cur_text = view.findViewById(R.id.editschedule_add_normal);

        edit_schedule_create_textView.setTypeface(typeface);
        edit_schedule_cancel_textView.setTypeface(typeface);
        edit_schedule_cancel_textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        edit_schedule_add_function_textView.setTypeface(typeface);
        edit_schedule_add_function_textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        edit_schedule_add_normal.setTypeface(typeface);
        edit_schedule_add_study.setTypeface(typeface);
        edit_schedule_add_healthy.setTypeface(typeface);
        edit_schedule_add_sport.setTypeface(typeface);
        edit_schedule_add_life.setTypeface(typeface);
        edit_schedule_add_money.setTypeface(typeface);


        edit_schedule_cancel_textView.setOnClickListener(this);
        edit_schedule_add_normal.setOnClickListener(this);
        edit_schedule_add_study.setOnClickListener(this);
        edit_schedule_add_healthy.setOnClickListener(this);
        edit_schedule_add_sport.setOnClickListener(this);
        edit_schedule_add_life.setOnClickListener(this);
        edit_schedule_add_money.setOnClickListener(this);
        edit_schedule_add_normal.setEnabled(false);
        edit_schedule_add_study.setEnabled(true);
        edit_schedule_add_healthy.setEnabled(true);
        edit_schedule_add_sport.setEnabled(true);
        edit_schedule_add_life.setEnabled(true);
        edit_schedule_add_money.setEnabled(true);



        //关于RecyclerView的加载
        init_Data_Hash();
        init_Data_normal();  //初始加载normal
        scheduleRecyclerView = view.findViewById(R.id.editschedule_add_recyclerView);
        EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        scheduleRecyclerView.setAdapter(editScheduleAdapter);
        scheduleRecyclerView.setLayoutManager(linearLayoutManager);


        return view;

    }



    //1. 点击事件
    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.editschedule_cancel_textView){
            //点击取消界面
            EventBus.getDefault().postSticky(new EventEditSchedule_MainActivity_Back(true));
        }else if(v.getId() == R.id.editschedule_add_normal){
            //点击重新加载RecyclerView
            Log.d("Ning_Module_EditSchedule" , "TextView - editschedule_add_normal");
            setEnable(edit_schedule_add_normal);

            //改变RecyclerView
            init_Data_normal();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);


        }else if(v.getId() == R.id.editschedule_add_study){
            Log.d("Ning_Module_EditSchedule","TextView - editschedule_add_study");
            setEnable(edit_schedule_add_study);

            //改变RecyclerView
            init_Data_study();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        }else if(v.getId() == R.id.editschedule_add_healthy){
            Log.d("Ning_Module_EditSchedule","TextView - editschedule_add_healthy");
            setEnable(edit_schedule_add_healthy);

            //改变RecyclerView
            init_Data_healthy();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        }else if(v.getId() == R.id.editschedule_add_sport){
            Log.d("Ning_Module_EditSchedule","TextView - editschedule_add_sport");
            setEnable(edit_schedule_add_sport);

            //改变RecyclerView
            init_Data_sport();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        }else if(v.getId() == R.id.editschedule_add_life){
            Log.d("Ning_Module_EditSchedule","TextView - editschedule_add_life");
            setEnable(edit_schedule_add_life);

            //改变RecyclerView
            init_Data_life();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        }else if(v.getId() == R.id.editschedule_add_money){
            Log.d("Ning_Module_EditSchedule","TextView - editschedule_add_money");
            setEnable(edit_schedule_add_money);

            //改变RecyclerView
            init_Data_money();
            EditScheduleAdapter editScheduleAdapter = new EditScheduleAdapter(scheduleArrayList , stringscheduleHashMap ,typeface);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
            scheduleRecyclerView.setAdapter(editScheduleAdapter);
            scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    //2.默认分类按钮点击变色事件
    private void setEnable(TextView textView){
        List<TextView> buttonList = new ArrayList<>();
        if(buttonList.size() == 0){
            buttonList.add(edit_schedule_add_normal);
            buttonList.add(edit_schedule_add_study);
            buttonList.add(edit_schedule_add_healthy);
            buttonList.add(edit_schedule_add_sport);
            buttonList.add(edit_schedule_add_life);
            buttonList.add(edit_schedule_add_money);
        }

        for(int i=0 ; i<buttonList.size() ; i++){
            buttonList.get(i).setEnabled(true);
        }
        textView.setEnabled(false);
    }



/**
 *                            -  关于RecyclerView的相关建设
 *                  1.点击不同的分类，将集合内的数据重新初始化，   initData_noraml();
 *                  2.对于Item来说，需要三个Image，一个Text
 *                  3.点击Item添加到临时集合当中（点击第二次Item，图片切换回去，并从临时集合中删除），点击创建后回到之前的界面，并上传临时集合
 *                  4.打开不同分类的时候，先加载临时集合，如果当前Item在这个集合当中，那么将圆形换成勾选状态
 *                  5.创建之后，将临时集合交给module_calendar ， 同时将当前的状态上交给数据库
 */
    public void init_Data_Hash(){
        stringscheduleHashMap = new HashMap<>();
        stringscheduleHashMap.put("取快递", false);
        stringscheduleHashMap.put("点外卖" , false);
        stringscheduleHashMap.put("倒垃圾" , false);
        stringscheduleHashMap.put("早起一杯水" , false);
        stringscheduleHashMap.put("不熬夜" , false);
        stringscheduleHashMap.put("每日护肤" , false);
        stringscheduleHashMap.put("早晚刷牙" , false);
        stringscheduleHashMap.put("控制情绪" , false);
        stringscheduleHashMap.put("喂猫" , false);
        stringscheduleHashMap.put("遛狗" , false);
        stringscheduleHashMap.put("远眺1分钟" , false);
        stringscheduleHashMap.put("做家务" , false);
        stringscheduleHashMap.put("冥想30分钟" , false);
        stringscheduleHashMap.put("浇花" , false);
        stringscheduleHashMap.put("联系朋友" , false);
        stringscheduleHashMap.put("带饭盒" , false);
        stringscheduleHashMap.put("戴口罩" , false);
        stringscheduleHashMap.put("每日反思" , false);
        stringscheduleHashMap.put("看新番" , false);
        stringscheduleHashMap.put("看一部电影" , false);
        stringscheduleHashMap.put("阅览新闻" , false);
        stringscheduleHashMap.put("打扫房间" , false);
        stringscheduleHashMap.put("每天收能量" , false);
        stringscheduleHashMap.put("听音乐" , false);
        stringscheduleHashMap.put("每天排便" , false);

        stringscheduleHashMap.put("学习" , false);
        stringscheduleHashMap.put("阅读" , false);
        stringscheduleHashMap.put("背单词" , false);
        stringscheduleHashMap.put("写日记" , false);
        stringscheduleHashMap.put("一周读本书" , false);
        stringscheduleHashMap.put("练字" , false);
        stringscheduleHashMap.put("学车" , false);
        stringscheduleHashMap.put("学编程" , false);
        stringscheduleHashMap.put("练习乐器" , false);
        stringscheduleHashMap.put("学习新技能" , false);
        stringscheduleHashMap.put("绘画" , false);
        stringscheduleHashMap.put("亲子共学" , false);
        stringscheduleHashMap.put("学数学" , false);
        stringscheduleHashMap.put("学英语" , false);
        stringscheduleHashMap.put("写作业" , false);
        stringscheduleHashMap.put("英语阅读" , false);
        stringscheduleHashMap.put("摄影" , false);
        stringscheduleHashMap.put("学新语言" , false);

        stringscheduleHashMap.put("早起" , false);
        stringscheduleHashMap.put("喝八杯水" , false);
        stringscheduleHashMap.put("11点前睡觉" , false);
        stringscheduleHashMap.put("吃早餐" , false);
        stringscheduleHashMap.put("吃代餐" , false);
        stringscheduleHashMap.put("滴眼药水" , false);
        stringscheduleHashMap.put("记得微笑" , false);
        stringscheduleHashMap.put("吃水果" , false);
        stringscheduleHashMap.put("午休30分钟" , false);
        stringscheduleHashMap.put("自己做饭" , false);
        stringscheduleHashMap.put("眼保健操" , false);
        stringscheduleHashMap.put("戒奶茶饮料" , false);
        stringscheduleHashMap.put("过九不食" , false);
        stringscheduleHashMap.put("戒糖减脂" , false);
        stringscheduleHashMap.put("按时吃药" , false);
        stringscheduleHashMap.put("吃素" , false);
        stringscheduleHashMap.put("喝牛奶" , false);
        stringscheduleHashMap.put("跳绳" , false);
        stringscheduleHashMap.put("打坐" , false);
        stringscheduleHashMap.put("泡脚" , false);
        stringscheduleHashMap.put("补充维生素" , false);

        stringscheduleHashMap.put("板球" , false);
        stringscheduleHashMap.put("滑冰" , false);
        stringscheduleHashMap.put("冰球" , false);
        stringscheduleHashMap.put("橄榄球" , false);
        stringscheduleHashMap.put("皮划艇" , false);
        stringscheduleHashMap.put("睡前瘦腿" , false);
        stringscheduleHashMap.put("记录体重" , false);
        stringscheduleHashMap.put("减肥" , false);
        stringscheduleHashMap.put("俯卧撑20个" , false);
        stringscheduleHashMap.put("跑步" , false);
        stringscheduleHashMap.put("动感单车" , false);
        stringscheduleHashMap.put("打篮球" , false);
        stringscheduleHashMap.put("跳舞" , false);
        stringscheduleHashMap.put("练习马术" , false);
        stringscheduleHashMap.put("爬山" , false);
        stringscheduleHashMap.put("举铁" , false);
        stringscheduleHashMap.put("拳击" , false);
        stringscheduleHashMap.put("棒球" , false);
        stringscheduleHashMap.put("徒步" , false);

        stringscheduleHashMap.put("约会" , false);
        stringscheduleHashMap.put("剃头发" , false);
        stringscheduleHashMap.put("掏耳朵" , false);
        stringscheduleHashMap.put("修理指甲" , false);
        stringscheduleHashMap.put("美甲" , false);
        stringscheduleHashMap.put("美发" , false);

        stringscheduleHashMap.put("不乱花钱" , false);
        stringscheduleHashMap.put("记账" , false);
        stringscheduleHashMap.put("每天攒钱" , false);
        stringscheduleHashMap.put("每月存钱" , false);


    }



    public void init_Data_normal(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("取快递",com.example.module_baselibs.R.drawable.editschedule_image_kuaidi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("取快递")));
        scheduleArrayList.add(new schedule("点外卖",com.example.module_baselibs.R.drawable.editschedule_image_waimai,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("点外卖")));
        scheduleArrayList.add(new schedule("倒垃圾",com.example.module_baselibs.R.drawable.editschedule_image_laji,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("倒垃圾")));
        scheduleArrayList.add(new schedule("早起一杯水",com.example.module_baselibs.R.drawable.editschedule_image_heshui,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("早起一杯水")));
        scheduleArrayList.add(new schedule("不熬夜",com.example.module_baselibs.R.drawable.editschedule_image_aoye,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("不熬夜")));
        scheduleArrayList.add(new schedule("每日护肤",com.example.module_baselibs.R.drawable.editschedule_image_hufu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每日护肤")));
        scheduleArrayList.add(new schedule("早晚刷牙",com.example.module_baselibs.R.drawable.editschedule_image_shuaya,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("早晚刷牙")));
        scheduleArrayList.add(new schedule("控制情绪",com.example.module_baselibs.R.drawable.editschedule_image_qingxu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("控制情绪")));
        scheduleArrayList.add(new schedule("喂猫",com.example.module_baselibs.R.drawable.editschedule_image_weimao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("喂猫")));
        scheduleArrayList.add(new schedule("遛狗",com.example.module_baselibs.R.drawable.editschedule_image_liugou,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("遛狗")));
        scheduleArrayList.add(new schedule("远眺1分钟",com.example.module_baselibs.R.drawable.editschedule_image_yuantiao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("远眺1分钟")));
        scheduleArrayList.add(new schedule("做家务",com.example.module_baselibs.R.drawable.editschedule_image_jiawu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("做家务")));
        scheduleArrayList.add(new schedule("冥想30分钟",com.example.module_baselibs.R.drawable.editschedule_image_minxiang,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("冥想30分钟")));
        scheduleArrayList.add(new schedule("浇花",com.example.module_baselibs.R.drawable.editschedule_image_jiaohua,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("浇花")));
        scheduleArrayList.add(new schedule("联系朋友",com.example.module_baselibs.R.drawable.editschedule_image_lianxi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("联系朋友")));
        scheduleArrayList.add(new schedule("带饭盒",com.example.module_baselibs.R.drawable.editschedule_image_fanhe,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("带饭盒")));
        scheduleArrayList.add(new schedule("戴口罩",com.example.module_baselibs.R.drawable.editschedule_image_kouzhao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("戴口罩")));
        scheduleArrayList.add(new schedule("每日反思",com.example.module_baselibs.R.drawable.editschedule_image_fansi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每日反思")));
        scheduleArrayList.add(new schedule("看新番",com.example.module_baselibs.R.drawable.editschedule_image_xinfan,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("看新番")));
        scheduleArrayList.add(new schedule("看一部电影",com.example.module_baselibs.R.drawable.editschedule_image_dianying,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("看一部电影")));
        scheduleArrayList.add(new schedule("阅览新闻",com.example.module_baselibs.R.drawable.editschedule_image_xinweng,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("阅览新闻")));
        scheduleArrayList.add(new schedule("打扫房间",com.example.module_baselibs.R.drawable.editschedule_image_dasaofangjian,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("打扫房间")));
        scheduleArrayList.add(new schedule("每天收能量",com.example.module_baselibs.R.drawable.editschedule_image_nengliang,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每天收能量")));
        scheduleArrayList.add(new schedule("听音乐",com.example.module_baselibs.R.drawable.editschedule_image_yinyue,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("听音乐")));
        scheduleArrayList.add(new schedule("每天排便",com.example.module_baselibs.R.drawable.editschedule_image_paibian,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每天排便")));
    }

    public void init_Data_study(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("学习",com.example.module_baselibs.R.drawable.editschedule_image_xuexi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学习")));
        scheduleArrayList.add(new schedule("阅读",com.example.module_baselibs.R.drawable.editschedule_image_yuedu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("阅读")));
        scheduleArrayList.add(new schedule("背单词",com.example.module_baselibs.R.drawable.editschedule_image_beidanci,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("背单词")));
        scheduleArrayList.add(new schedule("写日记",com.example.module_baselibs.R.drawable.editschedule_image_xiereji,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("写日记")));
        scheduleArrayList.add(new schedule("一周读本书",com.example.module_baselibs.R.drawable.editschedule_image_dushu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("一周读本书")));
        scheduleArrayList.add(new schedule("练字",com.example.module_baselibs.R.drawable.editschedule_image_lianzi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("练字")));
        scheduleArrayList.add(new schedule("学车",com.example.module_baselibs.R.drawable.editschedule_image_xueche,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学车")));
        scheduleArrayList.add(new schedule("学编程",com.example.module_baselibs.R.drawable.editschedule_image_biancheng,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学编程")));
        scheduleArrayList.add(new schedule("练习乐器",com.example.module_baselibs.R.drawable.editschedule_image_yueqi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("练习乐器")));
        scheduleArrayList.add(new schedule("学习新技能",com.example.module_baselibs.R.drawable.editschedule_image_xinjineng,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学习新技能")));
        scheduleArrayList.add(new schedule("绘画",com.example.module_baselibs.R.drawable.editschedule_image_huihua,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("绘画")));
        scheduleArrayList.add(new schedule("亲子共学",com.example.module_baselibs.R.drawable.editschedule_image_qinzi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("亲子共学")));
        scheduleArrayList.add(new schedule("学数学",com.example.module_baselibs.R.drawable.editschedule_image_xueshuxue,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学数学")));
        scheduleArrayList.add(new schedule("学英语",com.example.module_baselibs.R.drawable.editschedule_image_xueyingyu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学英语")));
        scheduleArrayList.add(new schedule("写作业",com.example.module_baselibs.R.drawable.editschedule_image_xiezuoye,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("写作业")));
        scheduleArrayList.add(new schedule("英语阅读",com.example.module_baselibs.R.drawable.editschedule_image_yingyuyuedu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("英语阅读")));
        scheduleArrayList.add(new schedule("摄影",com.example.module_baselibs.R.drawable.editschedule_image_sheying,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("摄影")));
        scheduleArrayList.add(new schedule("学新语言",com.example.module_baselibs.R.drawable.editschedule_image_xuexinyuyan,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("学新语言")));
    }

    public void init_Data_healthy(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("早起",com.example.module_baselibs.R.drawable.editschedule_image_zaoqi,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("早起")));
        scheduleArrayList.add(new schedule("喝八杯水",com.example.module_baselibs.R.drawable.editschedule_image_hebabei,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("喝八杯水")));
        scheduleArrayList.add(new schedule("11点前睡觉",com.example.module_baselibs.R.drawable.editschedule_image_11dianshui,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("11点前睡觉")));
        scheduleArrayList.add(new schedule("吃早餐",com.example.module_baselibs.R.drawable.editschedule_image_chizaocan,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("吃早餐")));
        scheduleArrayList.add(new schedule("吃代餐",com.example.module_baselibs.R.drawable.editschedule_image_chidaican,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("吃代餐")));
        scheduleArrayList.add(new schedule("滴眼药水",com.example.module_baselibs.R.drawable.editschedule_image_yanyaoshui,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("滴眼药水")));
        scheduleArrayList.add(new schedule("记得微笑",com.example.module_baselibs.R.drawable.editschedule_image_jideweixiao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("记得微笑")));
        scheduleArrayList.add(new schedule("吃水果",com.example.module_baselibs.R.drawable.editschedule_image_chishuiguo,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("吃水果")));
        scheduleArrayList.add(new schedule("午休30分钟",com.example.module_baselibs.R.drawable.editschedule_image_wuxiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("午休30分钟")));
        scheduleArrayList.add(new schedule("自己做饭",com.example.module_baselibs.R.drawable.editschedule_image_zuofan,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("自己做饭")));
        scheduleArrayList.add(new schedule("眼保健操",com.example.module_baselibs.R.drawable.editschedule_image_yanbaojiancao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("眼保健操")));
        scheduleArrayList.add(new schedule("戒奶茶饮料",com.example.module_baselibs.R.drawable.editschedule_image_jienaicha,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("戒奶茶饮料")));
        scheduleArrayList.add(new schedule("过九不食",com.example.module_baselibs.R.drawable.editschedule_image_guojiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("过九不食")));
        scheduleArrayList.add(new schedule("戒糖减脂",com.example.module_baselibs.R.drawable.editschedule_image_jietang,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("戒糖减脂")));
        scheduleArrayList.add(new schedule("按时吃药",com.example.module_baselibs.R.drawable.editschedule_image_chiyao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("按时吃药")));
        scheduleArrayList.add(new schedule("吃素",com.example.module_baselibs.R.drawable.editschedule_image_chisu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("吃素")));
        scheduleArrayList.add(new schedule("喝牛奶",com.example.module_baselibs.R.drawable.editschedule_image_heniunai,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("喝牛奶")));
        scheduleArrayList.add(new schedule("跳绳",com.example.module_baselibs.R.drawable.editschedule_image_tiaosheng,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("跳绳")));
        scheduleArrayList.add(new schedule("打坐",com.example.module_baselibs.R.drawable.editschedule_image_dazuo,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("打坐")));
        scheduleArrayList.add(new schedule("泡脚",com.example.module_baselibs.R.drawable.editschedule_image_paojiao,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("泡脚")));
        scheduleArrayList.add(new schedule("补充维生素",com.example.module_baselibs.R.drawable.editschedule_image_weishengsu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("补充维生素")));

    }
    public void init_Data_sport(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("板球",com.example.module_baselibs.R.drawable.editschedule_image_banqiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("板球")));
        scheduleArrayList.add(new schedule("滑冰",com.example.module_baselibs.R.drawable.editschedule_image_liubing,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("滑冰")));
        scheduleArrayList.add(new schedule("冰球",com.example.module_baselibs.R.drawable.editschedule_image_bingqiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("冰球")));
        scheduleArrayList.add(new schedule("橄榄球",com.example.module_baselibs.R.drawable.editschedule_image_ganlanqiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("橄榄球")));
        scheduleArrayList.add(new schedule("皮划艇",com.example.module_baselibs.R.drawable.editschedule_image_pihuating,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("皮划艇")));
        scheduleArrayList.add(new schedule("睡前瘦腿",com.example.module_baselibs.R.drawable.editschedule_image_shoutui,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("睡前瘦腿")));
        scheduleArrayList.add(new schedule("记录体重",com.example.module_baselibs.R.drawable.editschedule_image_tizhong,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("记录体重")));
        scheduleArrayList.add(new schedule("减肥",com.example.module_baselibs.R.drawable.editschedule_image_jianfei,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("减肥")));
        scheduleArrayList.add(new schedule("俯卧撑20个",com.example.module_baselibs.R.drawable.editschedule_image_fuwocheng,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("俯卧撑20个")));
        scheduleArrayList.add(new schedule("跑步",com.example.module_baselibs.R.drawable.editschedule_image_paobu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("跑步")));
        scheduleArrayList.add(new schedule("动感单车",com.example.module_baselibs.R.drawable.editschedule_image_donggandanche,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("动感单车")));
        scheduleArrayList.add(new schedule("打篮球",com.example.module_baselibs.R.drawable.editschedule_image_dalanqiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("打篮球")));
        scheduleArrayList.add(new schedule("跳舞",com.example.module_baselibs.R.drawable.editschedule_image_tiaowu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("跳舞")));
        scheduleArrayList.add(new schedule("练习马术",com.example.module_baselibs.R.drawable.editschedule_image_mashu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("练习马术")));
        scheduleArrayList.add(new schedule("爬山",com.example.module_baselibs.R.drawable.editschedule_image_pashan,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("爬山")));
        scheduleArrayList.add(new schedule("举铁",com.example.module_baselibs.R.drawable.editschedule_image_jutie,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("举铁")));
        scheduleArrayList.add(new schedule("拳击",com.example.module_baselibs.R.drawable.editschedule_image_quanji,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("拳击")));
        scheduleArrayList.add(new schedule("棒球",com.example.module_baselibs.R.drawable.editschedule_image_bangqiu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("棒球")));
        scheduleArrayList.add(new schedule("徒步",com.example.module_baselibs.R.drawable.editschedule_image_tubu,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("徒步")));

    }
    public void init_Data_life(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("约会",com.example.module_baselibs.R.drawable.editschedule_image_yuehui,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("约会")));
        scheduleArrayList.add(new schedule("剃头发",com.example.module_baselibs.R.drawable.editschedule_image_titoufa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("剃头发")));
        scheduleArrayList.add(new schedule("掏耳朵",com.example.module_baselibs.R.drawable.editschedule_image_erduo,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("掏耳朵")));
        scheduleArrayList.add(new schedule("修理指甲",com.example.module_baselibs.R.drawable.editschedule_image_xiuzhijia,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("修理指甲")));
        scheduleArrayList.add(new schedule("美甲",com.example.module_baselibs.R.drawable.editschedule_image_meijai,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("美甲")));
        scheduleArrayList.add(new schedule("美发",com.example.module_baselibs.R.drawable.editschedule_image_meifa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("美发")));

    }
    public void init_Data_money(){
        scheduleArrayList = new ArrayList<>();
        scheduleArrayList.add(new schedule("不乱花钱",com.example.module_baselibs.R.drawable.editschedule_image_meifa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("不乱花钱")));
        scheduleArrayList.add(new schedule("记账",com.example.module_baselibs.R.drawable.editschedule_image_meifa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("记账")));
        scheduleArrayList.add(new schedule("每天攒钱",com.example.module_baselibs.R.drawable.editschedule_image_meifa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每天攒钱")));
        scheduleArrayList.add(new schedule("每月存钱",com.example.module_baselibs.R.drawable.editschedule_image_meifa,com.example.module_baselibs.R.drawable.editschedule_image_weigouxuan,stringscheduleHashMap.get("每月存钱")));
    }



}

