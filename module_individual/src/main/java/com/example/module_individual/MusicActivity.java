package com.example.module_individual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.w3c.dom.Text;

import java.io.IOException;

@Route(path = "/individual/MusicActivity")
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    TextView individual_music_back;
    TextView individual_music_text;
    TextView individual_music_name;
    TextView individual_music_writer;
    ImageView individual_music_start;
    ImageView individual_music_right;
    ImageView individual_music_left;
    ImageView individual_music_love;
    ImageView individual_music_list;
    MediaPlayer mediaPlayer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font_shoujin.ttf");
        individual_music_back = findViewById(R.id.socializing_music_back);
        individual_music_text = findViewById(R.id.socializing_music_text);
        individual_music_back.setTypeface(typeface);
        individual_music_text.setTypeface(typeface);
        individual_music_back.setOnClickListener(this);
        individual_music_name = findViewById(R.id.individual_music_name);
        individual_music_writer = findViewById(R.id.individual_music_writer);
        individual_music_name.setTypeface(typeface);
        individual_music_start = findViewById(R.id.individual_music_start);
        individual_music_left = findViewById(R.id.individual_music_left);
        individual_music_right = findViewById(R.id.individual_music_right);
        individual_music_love = findViewById(R.id.individual_music_love);
        individual_music_list = findViewById(R.id.individual_music_list);

        initMediaPlayer();
        individual_music_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.socializing_music_back){
            finish();
        }else if(id == R.id.individual_music_start){
            if(individual_music_start.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.individual_music_start).getConstantState() ){
                individual_music_start.setImageResource(R.drawable.individual_music_pause);
                mediaPlayer.start();
            }else{
                //暂停
                individual_music_start.setImageResource(R.drawable.individual_music_start);
                mediaPlayer.pause();
            }
        }
    }


    public void initMediaPlayer(){
        AssetManager assetManager = getAssets();
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd("individual_huangmeixi.mp3");
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor() , fileDescriptor.getStartOffset() , fileDescriptor.getStartOffset());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}