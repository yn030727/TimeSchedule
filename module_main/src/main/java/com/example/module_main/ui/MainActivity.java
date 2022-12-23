package com.example.module_main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_main.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import eventbus.EventChallengeCard;
//这是主模块的第一界面
// 功能:
// 1. 初始化其他界面提供的Fragment
// 2. 初始化主界面底部导航栏，以及按钮点击跳转界面
// 代码目录
// 0.声明全局变量
// 1.设置瘦金体
// 2.初始化变量
// 3.初始化点击事件
// 4.EventBus的订阅者事件处理

@Route(path="/main/MainActivity")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //0.APP层需要声明的数据
    Button app_navigation_schedule_btn;//底部导航栏的石以砥焉界面跳转按钮
    Button app_navigation_challenge_btn;//底部导航栏的暂留芳华界面跳转按钮
    Button app_navigation_social_btn;//底部导航栏的一觞一咏界面跳转按钮
    Button app_navigation_person_btn;//底部导航栏的舍间陋室界面跳转按钮
    TextView app_navigation_schedule_text;//和按钮配套的文本
    TextView app_navigation_challenge_text;
    TextView app_navigation_social_text;
    TextView app_navigation_person_text;
    ArrayList<Fragment> fragmentArrayList; //存放各组件Fragment的集合


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/main_font_shoujin.ttf");
        //设置方式案例
/*         TextView textView = findViewById(R.id.textView);
        textView.setTypeface(typeface);*/

        //2.初始化变量
        app_navigation_challenge_btn = findViewById(R.id.app_navigation_challenge_btn);
        app_navigation_schedule_btn = findViewById(R.id.app_navigation_schedule_btn);
        app_navigation_social_btn = findViewById(R.id.app_navigation_social_btn);
        app_navigation_person_btn = findViewById(R.id.app_navigation_person_btn);
        app_navigation_schedule_text = findViewById(R.id.app_navigation_schedule_text);
        app_navigation_challenge_text = findViewById(R.id.app_navigation_challenge_text);
        app_navigation_social_text = findViewById(R.id.app_navigation_social_text);
        app_navigation_person_text = findViewById(R.id.app_navigation_person_text);
        //初始化EventBus
        EventBus.getDefault().register(this);



        //3.初始化按钮的点击事件
        app_navigation_challenge_btn.setOnClickListener(this);
        app_navigation_schedule_btn.setOnClickListener(this);
        app_navigation_social_btn.setOnClickListener(this);
        app_navigation_person_btn.setOnClickListener(this);

        //初始化Fragment
        replaceFragment( (Fragment) ARouter.getInstance().build("/calendar/CalendarFragment").navigation());
        onClick(app_navigation_schedule_btn);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.app_navigation_challenge_btn) {
            setEnable(app_navigation_challenge_btn);
            initText();
            app_navigation_challenge_text.setTextColor(Color.rgb(111, 24, 32));
            replaceFragment((Fragment) ARouter.getInstance().build("/challenge/ChallengeFragment").navigation());
        } else if (id == R.id.app_navigation_schedule_btn) {
            setEnable(app_navigation_schedule_btn);
            initText();
            app_navigation_schedule_text.setTextColor(Color.rgb(111, 24, 32));
            replaceFragment((Fragment) ARouter.getInstance().build("/calendar/CalendarFragment").navigation());
        } else if (id == R.id.app_navigation_social_btn) {
            setEnable(app_navigation_social_btn);
            initText();
            app_navigation_social_text.setTextColor(Color.rgb(111, 24, 32));
            replaceFragment((Fragment) ARouter.getInstance().build("/socializing/SocializingFragment").navigation());
        } else if (id == R.id.app_navigation_person_btn) {
            setEnable(app_navigation_person_btn);
            initText();
            app_navigation_person_text.setTextColor(Color.rgb(111, 24, 32));
            replaceFragment((Fragment) ARouter.getInstance().build("/individual/IndividualFragment").navigation());
        }
    }

    private void setEnable(Button btn){
        List<Button> buttonList = new ArrayList<>();
        if(buttonList.size() == 0){
            buttonList.add(app_navigation_challenge_btn);
            buttonList.add(app_navigation_schedule_btn);
            buttonList.add(app_navigation_social_btn);
            buttonList.add(app_navigation_person_btn);

        }
        for(int i = 0 ; i<buttonList.size() ; i++){
            buttonList.get(i).setEnabled(true);
        }
        btn.setEnabled(false);
    }

    private void initText(){
        app_navigation_challenge_text.setTextColor(Color.rgb(181,181,181));
        app_navigation_schedule_text.setTextColor(Color.rgb(181,181,181));
        app_navigation_social_text.setTextColor(Color.rgb(181,181,181));
        app_navigation_person_text.setTextColor(Color.rgb(181,181,181));
    }

    //点击切换Fragment的方法
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_maininterface_container,fragment);
        fragmentTransaction.commit();
    }


    //初始化其他界面提供的Fragment
    private void initFragments(){
        fragmentArrayList = new ArrayList<>();
        Fragment challengeFragment = (Fragment) ARouter.getInstance().build("/challenge/ChallengeFragment").navigation();
        Fragment calendarFragment = (Fragment) ARouter.getInstance().build("/calendar/CalendarFragment").navigation();
        Fragment socializingFragment = (Fragment) ARouter.getInstance().build("/socializing/SocializingFragment").navigation();
        Fragment individualFragment = (Fragment) ARouter.getInstance().build("/individual/IndividualFragment").navigation();
        Fragment FirstCardFragment = (Fragment)ARouter.getInstance().build("/challenge/ChallengeFragment_FirstCard").navigation();
        fragmentArrayList.add(challengeFragment);
        fragmentArrayList.add(calendarFragment);
        fragmentArrayList.add(socializingFragment);
        fragmentArrayList.add(individualFragment);
        fragmentArrayList.add(FirstCardFragment);
    }

    //4.EventBus订阅者事件(写成黏性事件)
    //该方法会在发布对应事件的时侯进行调用
    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public void showEventChallengeCard(EventChallengeCard card){
        Log.d("Ning","ShowEventChallengeCard");
        //true表示有按钮被点击

        if(card.getClick_card()){
            if(card.getCard_number() == 0){
                replaceFragment((Fragment)ARouter.getInstance().build("/challenge/ChallengeFragment_FirstCard").navigation());
            }else if(card.getCard_number() == 1){

            }else if(card.getCard_number() == 2){

            }else if(card.getCard_number() == 3){

            }else if(card.getCard_number() == 4){

            }else if(card.getCard_number() == 5){

            }else if(card.getCard_number() == 6){

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}