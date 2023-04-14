package com.example.module_socializing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route( path = "/socializing/AlbumActivity")
public class AlbumActivity extends AppCompatActivity {

    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font_shoujin.ttf");

        cancel = findViewById(R.id.socializing_album_back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}