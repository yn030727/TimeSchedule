package com.example.module_main.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_main.R;

import java.util.ArrayList;
import java.util.List;


@Route(path="/main/MainActivity")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //APP层需要声明的数据
    Button app_navigation_schedule_btn;
    Button app_navigation_challenge_btn;
    Button app_navigation_social_btn;
    Button app_navigation_person_btn;
    TextView app_navigation_schedule_text;
    TextView app_navigation_challenge_text;
    TextView app_navigation_social_text;
    TextView app_navigation_person_text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/main_font_shoujin.ttf");
        //设置方式案例
/*         TextView textView = findViewById(R.id.textView);
        textView.setTypeface(typeface);*/

        //2.初始化变量
        app_navigation_challenge_btn = findViewById(R.id.app_navigation_challenge_btn);//底部导航栏的石以砥焉界面跳转按钮
        app_navigation_schedule_btn = findViewById(R.id.app_navigation_schedule_btn);//底部导航栏的暂留芳华界面跳转按钮
        app_navigation_social_btn = findViewById(R.id.app_navigation_social_btn);//底部导航栏的一觞一咏界面跳转按钮
        app_navigation_person_btn = findViewById(R.id.app_navigation_person_btn);//底部导航栏的舍间陋室界面跳转按钮
        app_navigation_schedule_text = findViewById(R.id.app_navigation_schedule_text);
        app_navigation_challenge_text = findViewById(R.id.app_navigation_challenge_text);
        app_navigation_social_text = findViewById(R.id.app_navigation_social_text);
        app_navigation_person_text = findViewById(R.id.app_navigation_person_text);

        //3.初始化按钮的点击事件
        app_navigation_challenge_btn.setOnClickListener(this);
        app_navigation_schedule_btn.setOnClickListener(this);
        app_navigation_social_btn.setOnClickListener(this);
        app_navigation_person_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.app_navigation_challenge_btn) {
            setEnable(app_navigation_challenge_btn);
            initText();
            app_navigation_challenge_text.setTextColor(Color.rgb(111, 24, 32));
        } else if (id == R.id.app_navigation_schedule_btn) {
            setEnable(app_navigation_schedule_btn);
            initText();
            app_navigation_schedule_text.setTextColor(Color.rgb(111, 24, 32));
        } else if (id == R.id.app_navigation_social_btn) {
            setEnable(app_navigation_social_btn);
            initText();
            app_navigation_social_text.setTextColor(Color.rgb(111, 24, 32));
        } else if (id == R.id.app_navigation_person_btn) {
            setEnable(app_navigation_person_btn);
            initText();
            app_navigation_person_text.setTextColor(Color.rgb(111, 24, 32));
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
}