package com.example.module_socializing.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_socializing.R;
import com.example.module_socializing.SpacesItemDecoration;
import com.example.module_socializing.adapter.SocializingShareItemAdapter;
import com.example.module_socializing.data.ShareData;

import java.util.ArrayList;
import java.util.List;

//个人社交模块
//1.变量声明
//2.相关方法

@Route( path = "/socializing/SocializingFragment")
public class SocializingFragment extends Fragment implements View.OnClickListener {
    //1.变量声明
    List<ShareData> mList;
    ImageView individual_add_article;
    ImageView individual_album;
    ImageView individual_statstics;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socializing,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        TextView socializing_main_title = view.findViewById(R.id.socializing_main_title_tv);

        Log.d("SocializingShare", "start");

        //RecyclerView的初始化相关操作
        RecyclerView recyclerView = view.findViewById(R.id.socializing_recycler);

        recyclerView.setPadding(8,8,8,8);

        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        initData();
        SocializingShareItemAdapter socializingShareItemAdapter = new SocializingShareItemAdapter(mList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(socializingShareItemAdapter);

        //常用对象的初始化配置
        socializing_main_title.setTypeface(typeface);
        individual_add_article = view.findViewById(R.id.imageView);
        individual_add_article.setOnClickListener(this);
        individual_album = view.findViewById(R.id.socializing_settings_photo);
        individual_album.setOnClickListener(this);
        individual_statstics= view.findViewById(R.id.socializing_settings_date);
        individual_statstics.setOnClickListener(this);



        return view;
    }



    //2.相关方法


    //(1).初始化和RecyclerView有关的集合
    public void initData(){
        mList = new ArrayList<>();
        mList.add(new ShareData(R.drawable.socializing_head_iamge1_test,"测试用户1","今日打卡完成进度(3/3)\n1.完成老师剩下的作业\n2.完成项目UI的改善\n3.学习一些新的Android知识" , R.drawable.socializing_head_iamge1_test , "1天前"));
        mList.add(new ShareData(R.drawable.socializing_head_portrait,"测试用户2", "孔子创立儒家学派。孔子的思想核心是“仁”。他认为仁就是爱人，人与人之间要互相爱护，融洽相处；要做到待人宽容，“已所不欲，勿施于人”。孔子强调统治者要以德治民，爱惜民力，取信于民，反对苛政和任意刑杀。孔子首创私人讲学，主张“有教无类”，打破了贵族垄断文化教育的局面。\n" +
                "（2）孟子和荀子是儒家学派的两位重要代表人物。孟子发展了孔子“仁”的思想，主张实行“仁政”，进一步提出“民为贵，社稷次之，君为轻”的民本思想。在伦理观上，孟子主张“性本善”。"  , "1天前"));
        mList.add(new ShareData(R.drawable.socializing_head_portrait,"小傅同学","孔子创立儒家学派。孔子的思想核心是“仁”。他认为仁就是爱人，人与人之间要互相爱护，融洽相处；要做到待人宽容，“已所不欲，勿施于人”。孔子强调统治者要以德治民，爱惜民力，取信于民，反对苛政和任意刑杀。孔子首创私人讲学，主张“有教无类”，打破了贵族垄断文化教育的局面。\n" +
                "（2）孟子和荀子是儒家学派的两位重要代表人物。孟子发展了孔子“仁”的思想，主张实行“仁政”，进一步提出“民为贵，社稷次之，君为轻”的民本思想。在伦理观上，孟子主张“性本善”。" , R.drawable.socializing_head_portrait , "2天前") );

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.imageView){
            ARouter.getInstance().build("/socializing/ArticleActivity").navigation();
        }
        if(id == R.id.socializing_settings_photo) {
            Log.d("FUFU", "onClick: socializing_settings_photo");
            ARouter.getInstance().build("/socializing/AlbumActivity").navigation();
        }
        if(id == R.id.socializing_settings_date) {
            ARouter.getInstance().build("/socializing/StatsticsActivity").navigation();
        }
    }
}
