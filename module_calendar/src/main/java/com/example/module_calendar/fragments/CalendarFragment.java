package com.example.module_calendar.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.module_calendar.R;
import com.example.module_calendar.model.Article;
import com.example.module_calendar.model.CalendarSchedule;
import com.example.module_calendar.ui.BaseActivity;
import com.example.module_calendar.ui.CalendarScheduleAdapter;
import com.example.module_calendar.ui.GroupItemDecoration;
import com.example.module_calendar.ui.GroupRecyclerView;
import com.example.module_calendar.ui.TargetAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import eventbus.EventEditSchedule;
import eventbus.EventLoginInformation;
import eventbus.EventSchedule;


//第一界面:计划表

//功能:
//代码目录:
//  0.定义变量
//  1.Fragment内的主要部分
//  2.相关方法介绍
//  3.EventBus订阅事件


@Route(path = "/calendar/CalendarFragment")
public class CalendarFragment extends Fragment implements
        CalendarView.OnCalendarSelectListener ,
        CalendarView.OnYearChangeListener,
        View.OnClickListener{


    //0.定义变量
    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    //RecyclerView mRecyclerView;
    List<String> list;
    TextView calendar_week_textview;
    TextView calendar_week_textview2;
    ImageView calendar_title_imageView;
    ImageView calendar_title_add;
    java.util.Calendar calendar1;
    EventLoginInformation schedule_login_information;
    //账号
    String calendar_account;   //用来标识当前是否存在账号
    int calendar_enter_count;  //标识第几次进入当前组件
    //计划
    HashMap<String , Boolean> receiveHashMap;
    HashMap<String , Integer> imageHashMap;
    ArrayList<CalendarSchedule> scheduleArrayList = new ArrayList<>(); //放到RecyclerView里面的更新集合
    HashMap<String , Boolean> scheduleStateHashMap = new HashMap<>(); //用来判断选中计划的完成情况
    RecyclerView calendar_schedule_recylcerview;
    Typeface typeface;




    //  1.Fragment内的主要部分
        // (1).变量声明
        // (2).左上角日期的点击事件(点击月份切换到年份选择界面)
        // (3).月份,年份,星期各类信息的初始化
        // (4).添加计划按钮的点击事件(跳转到添加计划界面)
        // (5).计划的RecyclerView相关操作





    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // (1).变量声明
        View view = inflater.inflate(R.layout.fragment_calendar,container,false);
        mTextMonthDay = view.findViewById(R.id.tv_month_day);
        mTextYear = view.findViewById(R.id.tv_year);
        mTextLunar = view.findViewById(R.id.tv_lunar);
        mRelativeTool = view.findViewById(R.id.rl_tool);
        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarLayout = view.findViewById(R.id.calendarLayout);
        mTextCurrentDay = view.findViewById(R.id.tv_current_day);
        calendar_week_textview = view.findViewById(R.id.calendar_week_textview);
        calendar_week_textview2 = view.findViewById(R.id.calendar_week_textview2);
        calendar_title_imageView = view.findViewById(R.id.calendar_title_imageView);
        calendar_title_add = view.findViewById(R.id.calendar_title_add);
        calendar1 = java.util.Calendar.getInstance();
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        calendar_week_textview.setTypeface(typeface);
        calendar_week_textview2.setTypeface(typeface);
        mTextMonthDay.setTypeface(typeface);
        calendar_week_textview.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //初始化EventBus
        EventBus.getDefault().register(this);



        // (2).点击月份切换到年份选择界面
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mCalendarLayout.isExpand()) {
                    mCalendarLayout.expand();
                    return;
                }

                mCalendarView.showYearSelectLayout(mYear);

                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });




        // (3).月份,年份,星期各类信息的初始化
        view.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        calendar_week_textview.setText(JudgeWeek4(calendar1.get(java.util.Calendar.DAY_OF_WEEK)));
        calendar_week_textview2.setText(JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK)));
        Log.d("Ning_Module_Calendar" , "week is "+JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK )));
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        Log.d("Ning_Module_Calendar" , " year is "+ year + "  month is " + month);


        // (4).添加计划按钮的点击事件(跳转到添加计划界面)
        calendar_title_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!calendar_account.equals("NULL")){
                    EventBus.getDefault().postSticky(new EventEditSchedule(true));
                }else{
                    Toast.makeText(getContext(),"请先登陆账号!" , Toast.LENGTH_SHORT).show();
                }
            }
        });


        // (5).计划的RecyclerView相关操作
        //initData
        list = new ArrayList<>();
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        //mRecyclerView.setAdapter(new TargetAdapter(list));
        //mRecyclerView.notifyDataSetChanged();

//        RecyclerViewHeader recyclerViewHeader = view.findViewById(R.id.header);
//        recyclerViewHeader.attachTo(mRecyclerView);


        // (6).从其他界面跳转过来，更新登录信息
        SharedPreferences pref = getContext().getSharedPreferences("calendar_account" , Context.MODE_PRIVATE);
        calendar_account = pref.getString("account", "NULL");
        Log.d("Ning_module_calendar" , "onCreateView : " + calendar_account);



        //(7).加载RecyclerView
        initScheduleArrayList();
        initImageHashMap();
        calendar_schedule_recylcerview = view.findViewById(R.id.Calendar_recyclerview);
        CalendarScheduleAdapter calendarScheduleAdapter = new CalendarScheduleAdapter(scheduleArrayList,typeface,scheduleStateHashMap,calendar_title_imageView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        calendar_schedule_recylcerview.setAdapter(calendarScheduleAdapter);
        calendar_schedule_recylcerview.setLayoutManager(linearLayoutManager);


        return view;


    }









    //2.相关方法介绍

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());

        return calendar;
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        calendar_week_textview.setText(JudgeWeek(calendar.getWeek()));
        calendar_week_textview2.setText(JudgeWeek3(calendar.getWeek()));
        mYear = calendar.getYear();
        Log.d("Ning_Module_Calendar" , "week is "+JudgeWeek2(calendar1.get(java.util.Calendar.DAY_OF_WEEK ) - 1));
    }

    @Override
    public void onYearChange(int year) {
    }

    @Override
    public void onClick(View v) {

    }



    public void initData(){
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
    }


    public String JudgeWeek(int week){
        if(week == 1){
            return "月曜太阴" ;
        }else if(week == 2){
            return "火曜萤惑" ;
        }else if(week == 3){
            return "水曜辰星";
        }else if(week == 4){
            return "木曜岁星";
        }else if(week == 5){
            return "金曜太白";
        }else if(week == 6){
            return "土曜镇星";
        }else{
            return "日曜太阳";
        }
    }
    public String JudgeWeek4(int week){
        if(week == 2){
            return "月曜太阴" ;
        }else if(week == 3){
            return "火曜萤惑" ;
        }else if(week == 4){
            return "水曜辰星";
        }else if(week == 5){
            return "木曜岁星";
        }else if(week == 6){
            return "金曜太白";
        }else if(week == 7){
            return "土曜镇星";
        }else{
            return "日曜太阳";
        }
    }
    public String JudgeWeek2(int week){
        if(week == 2){
            return "-周一" ;
        }else if(week == 3){
            return "-周二" ;
        }else if(week == 4){
            return "-周三";
        }else if(week == 5){
            return "-周四";
        }else if(week == 6){
            return "-周五";
        }else if(week == 7){
            return "-周六";
        }else{
            return "-周日";
        }
    }

    public String JudgeWeek3(int week){
        if(week == 1){
            return "-周一" ;
        }else if(week == 2){
            return "-周二" ;
        }else if(week == 3){
            return "-周三";
        }else if(week == 4){
            return "-周四";
        }else if(week == 5){
            return "-周五";
        }else if(week == 6){
            return "-周六";
        }else{
            return "-周日";
        }
    }

    //初始化图片集合
    public void initImageHashMap(){
        imageHashMap = new HashMap<>();
        imageHashMap.put("取快递", com.example.module_baselibs.R.drawable.editschedule_image_kuaidi);
        imageHashMap.put("点外卖" , com.example.module_baselibs.R.drawable.editschedule_image_waimai);
        imageHashMap.put("倒垃圾" , com.example.module_baselibs.R.drawable.editschedule_image_laji);
        imageHashMap.put("早起一杯水" , com.example.module_baselibs.R.drawable.editschedule_image_heshui);
        imageHashMap.put("不熬夜" , com.example.module_baselibs.R.drawable.editschedule_image_aoye);
        imageHashMap.put("每日护肤" , com.example.module_baselibs.R.drawable.editschedule_image_hufu);
        imageHashMap.put("早晚刷牙" , com.example.module_baselibs.R.drawable.editschedule_image_shuaya);
        imageHashMap.put("控制情绪" , com.example.module_baselibs.R.drawable.editschedule_image_qingxu);
        imageHashMap.put("喂猫" , com.example.module_baselibs.R.drawable.editschedule_image_weimao);
        imageHashMap.put("遛狗" , com.example.module_baselibs.R.drawable.editschedule_image_liugou);
        imageHashMap.put("远眺1分钟" , com.example.module_baselibs.R.drawable.editschedule_image_yuantiao);
        imageHashMap.put("做家务" , com.example.module_baselibs.R.drawable.editschedule_image_jiawu);
        imageHashMap.put("冥想30分钟" , com.example.module_baselibs.R.drawable.editschedule_image_minxiang);
        imageHashMap.put("浇花" , com.example.module_baselibs.R.drawable.editschedule_image_jiaohua);
        imageHashMap.put("联系朋友" , com.example.module_baselibs.R.drawable.editschedule_image_lianxi);
        imageHashMap.put("带饭盒" , com.example.module_baselibs.R.drawable.editschedule_image_fanhe);
        imageHashMap.put("戴口罩" , com.example.module_baselibs.R.drawable.editschedule_image_kouzhao);
        imageHashMap.put("每日反思" , com.example.module_baselibs.R.drawable.editschedule_image_fansi);
        imageHashMap.put("看新番" , com.example.module_baselibs.R.drawable.editschedule_image_xinfan);
        imageHashMap.put("看一部电影" , com.example.module_baselibs.R.drawable.editschedule_image_dianying);
        imageHashMap.put("阅览新闻" , com.example.module_baselibs.R.drawable.editschedule_image_xinweng);
        imageHashMap.put("打扫房间" , com.example.module_baselibs.R.drawable.editschedule_image_dasaofangjian);
        imageHashMap.put("每天收能量" , com.example.module_baselibs.R.drawable.editschedule_image_nengliang);
        imageHashMap.put("听音乐" , com.example.module_baselibs.R.drawable.editschedule_image_yinyue);
        imageHashMap.put("每天排便" , com.example.module_baselibs.R.drawable.editschedule_image_paibian);

        imageHashMap.put("学习" , com.example.module_baselibs.R.drawable.editschedule_image_xuexi);
        imageHashMap.put("阅读" , com.example.module_baselibs.R.drawable.editschedule_image_yuedu);
        imageHashMap.put("背单词" , com.example.module_baselibs.R.drawable.editschedule_image_beidanci);
        imageHashMap.put("写日记" , com.example.module_baselibs.R.drawable.editschedule_image_xiereji);
        imageHashMap.put("一周读本书" , com.example.module_baselibs.R.drawable.editschedule_image_dushu);
        imageHashMap.put("练字" , com.example.module_baselibs.R.drawable.editschedule_image_lianzi);
        imageHashMap.put("学车" , com.example.module_baselibs.R.drawable.editschedule_image_xueche);
        imageHashMap.put("学编程" , com.example.module_baselibs.R.drawable.editschedule_image_biancheng);
        imageHashMap.put("练习乐器" , com.example.module_baselibs.R.drawable.editschedule_image_yueqi);
        imageHashMap.put("学习新技能" , com.example.module_baselibs.R.drawable.editschedule_image_xinjineng);
        imageHashMap.put("绘画" , com.example.module_baselibs.R.drawable.editschedule_image_huihua);
        imageHashMap.put("亲子共学" , com.example.module_baselibs.R.drawable.editschedule_image_qinzi);
        imageHashMap.put("学数学" , com.example.module_baselibs.R.drawable.editschedule_image_xueshuxue);
        imageHashMap.put("学英语" , com.example.module_baselibs.R.drawable.editschedule_image_xueyingyu);
        imageHashMap.put("写作业" ,com.example.module_baselibs.R.drawable.editschedule_image_xiezuoye);
        imageHashMap.put("英语阅读" , com.example.module_baselibs.R.drawable.editschedule_image_yingyuyuedu);
        imageHashMap.put("摄影" , com.example.module_baselibs.R.drawable.editschedule_image_sheying);
        imageHashMap.put("学新语言" , com.example.module_baselibs.R.drawable.editschedule_image_xuexinyuyan);

        imageHashMap.put("早起" , com.example.module_baselibs.R.drawable.editschedule_image_zaoqi);
        imageHashMap.put("喝八杯水" , com.example.module_baselibs.R.drawable.editschedule_image_hebabei);
        imageHashMap.put("11点前睡觉" , com.example.module_baselibs.R.drawable.editschedule_image_11dianshui);
        imageHashMap.put("吃早餐" , com.example.module_baselibs.R.drawable.editschedule_image_chizaocan);
        imageHashMap.put("吃代餐" , com.example.module_baselibs.R.drawable.editschedule_image_chidaican);
        imageHashMap.put("滴眼药水" , com.example.module_baselibs.R.drawable.editschedule_image_yanyaoshui);
        imageHashMap.put("记得微笑" , com.example.module_baselibs.R.drawable.editschedule_image_jideweixiao);
        imageHashMap.put("吃水果" , com.example.module_baselibs.R.drawable.editschedule_image_chishuiguo);
        imageHashMap.put("午休30分钟" , com.example.module_baselibs.R.drawable.editschedule_image_wuxiu);
        imageHashMap.put("自己做饭" , com.example.module_baselibs.R.drawable.editschedule_image_zuofan);
        imageHashMap.put("眼保健操" , com.example.module_baselibs.R.drawable.editschedule_image_yanbaojiancao);
        imageHashMap.put("戒奶茶饮料" , com.example.module_baselibs.R.drawable.editschedule_image_jienaicha);
        imageHashMap.put("过九不食" , com.example.module_baselibs.R.drawable.editschedule_image_guojiu);
        imageHashMap.put("戒糖减脂" , com.example.module_baselibs.R.drawable.editschedule_image_jietang);
        imageHashMap.put("按时吃药" , com.example.module_baselibs.R.drawable.editschedule_image_chiyao);
        imageHashMap.put("吃素" ,com.example.module_baselibs.R.drawable.editschedule_image_chisu);
        imageHashMap.put("喝牛奶" , com.example.module_baselibs.R.drawable.editschedule_image_heniunai);
        imageHashMap.put("跳绳" , com.example.module_baselibs.R.drawable.editschedule_image_tiaosheng);
        imageHashMap.put("打坐" , com.example.module_baselibs.R.drawable.editschedule_image_dazuo);
        imageHashMap.put("泡脚" , com.example.module_baselibs.R.drawable.editschedule_image_paojiao);
        imageHashMap.put("补充维生素" , com.example.module_baselibs.R.drawable.editschedule_image_weishengsu);

        imageHashMap.put("板球" , com.example.module_baselibs.R.drawable.editschedule_image_banqiu);
        imageHashMap.put("滑冰" , com.example.module_baselibs.R.drawable.editschedule_image_liubing);
        imageHashMap.put("冰球" , com.example.module_baselibs.R.drawable.editschedule_image_bingqiu);
        imageHashMap.put("橄榄球" , com.example.module_baselibs.R.drawable.editschedule_image_ganlanqiu);
        imageHashMap.put("皮划艇" , com.example.module_baselibs.R.drawable.editschedule_image_pihuating);
        imageHashMap.put("睡前瘦腿" ,com.example.module_baselibs.R.drawable.editschedule_image_shoutui);
        imageHashMap.put("记录体重" , com.example.module_baselibs.R.drawable.editschedule_image_tizhong);
        imageHashMap.put("减肥" , com.example.module_baselibs.R.drawable.editschedule_image_jianfei);
        imageHashMap.put("俯卧撑20个" , com.example.module_baselibs.R.drawable.editschedule_image_fuwocheng);
        imageHashMap.put("跑步" , com.example.module_baselibs.R.drawable.editschedule_image_paobu);
        imageHashMap.put("动感单车" , com.example.module_baselibs.R.drawable.editschedule_image_donggandanche);
        imageHashMap.put("打篮球" , com.example.module_baselibs.R.drawable.editschedule_image_dalanqiu);
        imageHashMap.put("跳舞" , com.example.module_baselibs.R.drawable.editschedule_image_tiaowu);
        imageHashMap.put("练习马术" , com.example.module_baselibs.R.drawable.editschedule_image_mashu);
        imageHashMap.put("爬山" , com.example.module_baselibs.R.drawable.editschedule_image_pashan);
        imageHashMap.put("举铁" , com.example.module_baselibs.R.drawable.editschedule_image_jutie);
        imageHashMap.put("拳击" , com.example.module_baselibs.R.drawable.editschedule_image_quanji);
        imageHashMap.put("棒球" , com.example.module_baselibs.R.drawable.editschedule_image_bangqiu);
        imageHashMap.put("徒步" ,com.example.module_baselibs.R.drawable.editschedule_image_tubu);

        imageHashMap.put("约会" , com.example.module_baselibs.R.drawable.editschedule_image_yuehui);
        imageHashMap.put("剃头发" , com.example.module_baselibs.R.drawable.editschedule_image_titoufa);
        imageHashMap.put("掏耳朵" , com.example.module_baselibs.R.drawable.editschedule_image_erduo);
        imageHashMap.put("修理指甲" , com.example.module_baselibs.R.drawable.editschedule_image_xiuzhijia);
        imageHashMap.put("美甲" , com.example.module_baselibs.R.drawable.editschedule_image_meijai);
        imageHashMap.put("美发" , com.example.module_baselibs.R.drawable.editschedule_image_meifa);

        imageHashMap.put("不乱花钱" ,com.example.module_baselibs.R.drawable.editschedule_image_buluanhuaqian);
        imageHashMap.put("记账" , com.example.module_baselibs.R.drawable.editschedule_image_jizhang);
        imageHashMap.put("每天攒钱" , com.example.module_baselibs.R.drawable.editschedule_image_zanqian);
        imageHashMap.put("每月存钱" , com.example.module_baselibs.R.drawable.editschedule_image_cunqian);
    }
    //初始化RecyclerView集合
    public void initScheduleArrayList(){
        //1.从数据库中获取已经存在的计划
        // 2.从数据库中获取计划的完成信息(HashMap)
    }


    //2.订阅事件
    //此事件对应的是接收到个人界面传过来的登录信息，然后改变(必须点开过个人主页界面，才会将数据传递过来)
    //回到Fragment之后，才会调用当前方法
    //通过数据库存储一个用来表示账号的数据，进入第一个界面之后，就先获取账号，如果不符合要求，那么、之后的点击添加计划操作就提醒不合理
    //重复进入第一界面重复读取数据库，只要登录了账号那么，数据库内的数据就一定是正确的
    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public void showEventLoginInformation(EventLoginInformation eventLoginInformation){
        Log.d("Ning","showEventLoginInformation");
        Log.d("Ning" , eventLoginInformation.getUser());
        //打开数据库，只要从个人界面回来
        //读取登录状态，更新数据库，更新本地变量
        SharedPreferences.Editor editor = getContext().getSharedPreferences("calendar_account",Context.MODE_PRIVATE).edit();
        calendar_account = eventLoginInformation.getAccount();
        editor.putString("account",calendar_account);
        Log.d("Ning_module_calendar","showEventLoginInformation : " + calendar_account);
        editor.apply();

    }

    //3.订阅事件
    //此事件对应的是接收创建计划界面传递过来的选择添加的计划信息，并根据信息，更新UI，并上传到数据库
    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public void showEventSchedule(EventSchedule eventSchedule){
        Log.d("Ning_module_calendar", "showEventSchedule");
        receiveHashMap = eventSchedule.getHashMap();
        Set<String> strings = receiveHashMap.keySet();
        initImageHashMap();
        for(String s : strings){
            if(receiveHashMap.get(s)){
                //为true表示被选中
                Log.d("Ning_module_calendar" , "receiveHashMap : " + s + " " + "true" );
                //选中计划之后一定会添加
                //添加会在原有的集合中进行添加
                //判断是否存在计划，是放在编辑模块，所以这边的计划一定是还未添加过的，那么直接添加即可
                scheduleArrayList.add(new CalendarSchedule(s,imageHashMap.get(s),false));
                scheduleStateHashMap.put(s,false);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
