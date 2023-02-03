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

    }

    public void init_Data_healthy(){
        scheduleArrayList = new ArrayList<>();

    }
    public void init_Data_sport(){
        scheduleArrayList = new ArrayList<>();

    }
    public void init_Data_life(){
        scheduleArrayList = new ArrayList<>();

    }
    public void init_Data_money(){
        scheduleArrayList = new ArrayList<>();

    }



}

