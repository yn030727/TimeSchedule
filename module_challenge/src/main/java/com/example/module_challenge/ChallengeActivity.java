package com.example.module_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

public class ChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Ning","ChallengeActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
    }
}