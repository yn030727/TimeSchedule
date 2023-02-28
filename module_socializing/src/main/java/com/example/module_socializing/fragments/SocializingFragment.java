package com.example.module_socializing.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
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
public class SocializingFragment extends Fragment {
    //1.变量声明
    List<ShareData> mList;
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
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        initData();
        SocializingShareItemAdapter socializingShareItemAdapter = new SocializingShareItemAdapter(mList);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(socializingShareItemAdapter);



        return view;
    }



    //2.相关方法


    //(1).初始化和RecyclerView有关的集合
    public void initData(){
        mList = new ArrayList<>();
        mList.add(new ShareData("2","title222","用户2"));
        mList.add(new ShareData("3","这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的标题","用户1"));
        mList.add(new ShareData("3","title55333333","用户2"));
        mList.add(new ShareData("46","title4625","用户5"));
        mList.add(new ShareData("3","title555","用户51"));
    }
}
