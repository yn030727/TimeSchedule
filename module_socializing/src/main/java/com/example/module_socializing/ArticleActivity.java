package com.example.module_socializing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;


@Route( path = "/socializing/ArticleActivity")
public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {


    //1.定义变量
    TextView socializing_article_back;
    TextView socializing_article_share;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font_shoujin.ttf");


        //变量初始化
        socializing_article_back = findViewById(R.id.socializing_article_back);
        socializing_article_back.setTypeface(typeface);
        socializing_article_back.setOnClickListener(this);
        socializing_article_share = findViewById(R.id.socializing_article_share);
        socializing_article_share.setTypeface(typeface);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.socializing_article_back){
            finish();
        }
    }
}