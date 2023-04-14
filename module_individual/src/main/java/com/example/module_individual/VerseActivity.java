package com.example.module_individual;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/individual/VerseActivity")
public class VerseActivity extends AppCompatActivity implements View.OnClickListener {
    TextView socializing_verse_back;
    TextView socializing_verse_text;
    TextView socializing_verse1;
    TextView socializing_verse2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font_shoujin.ttf");
        socializing_verse_back = findViewById(R.id.socializing_verse_back);
        socializing_verse_text = findViewById(R.id.socializing_verse_text);
        socializing_verse_back.setOnClickListener(this);
        socializing_verse_back.setTypeface(typeface);
        socializing_verse_text = findViewById(R.id.socializing_verse_text);
        socializing_verse_text.setTypeface(typeface);
        socializing_verse1 = findViewById(R.id.socializing_verse1);
        socializing_verse2 = findViewById(R.id.socializing_verse2);
        socializing_verse1.setTypeface(typeface);
        socializing_verse2.setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.socializing_verse_back){
            finish();
        }
    }
}