package com.example.module_individual;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/individual/PerimeterActivity")
public class PerimeterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView socializing_perimeter_back;
    TextView socializing_perimeter_text;
    WebView socializing_perimeter_web;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perimeter);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main_font_shoujin.ttf");
        socializing_perimeter_back = findViewById(R.id.socializing_perimeter_back);
        socializing_perimeter_text = findViewById(R.id.socializing_perimeter_share);
        socializing_perimeter_back.setTypeface(typeface);
        socializing_perimeter_text.setTypeface(typeface);
        socializing_perimeter_back.setOnClickListener(this);
        socializing_perimeter_web = (WebView) findViewById(R.id.socializing_perimeter_web);
        socializing_perimeter_web.getSettings().setJavaScriptEnabled(true);
        socializing_perimeter_web.setWebViewClient(new WebViewClient());
        socializing_perimeter_web.loadUrl("https://www.dpm.org.cn/Creative.html");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.socializing_perimeter_back){
            finish();
        }
    }
}
