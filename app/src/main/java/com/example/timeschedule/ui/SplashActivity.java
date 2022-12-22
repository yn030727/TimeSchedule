package com.example.timeschedule.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.timeschedule.R;

public class SplashActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
/*        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        Log.d("Ning","SendMessage");
        handler.sendEmptyMessageDelayed(0,1700);
    }
    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg){
            Log.d("Ning","Splash");
            ARouter.getInstance().build("/main/MainActivity").navigation(SplashActivity.this);
            finish();
        }
    };
}