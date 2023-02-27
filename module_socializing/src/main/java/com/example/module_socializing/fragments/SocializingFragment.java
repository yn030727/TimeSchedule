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

@Route( path = "/socializing/SocializingFragment")
public class SocializingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socializing,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");

        TextView socializing_main_title = view.findViewById(R.id.socializing_main_title_tv);

        Log.d("SocializingShare", "start");

        RecyclerView recyclerView = view.findViewById(R.id.socializing_recycler);
        recyclerView.setPadding(8,8,8,8);
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        List<ShareData> mList = new ArrayList<>();
        ShareData data = new ShareData();
        data.like = "2";
        data.title = "title222";
        data.name = "用户2";
        mList.add(data);
        ShareData data1 = new ShareData();
        data1.like = "3";
        data1.title = "这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的标题";
        data1.name = "用户1";
        mList.add(data1);
        ShareData data2 = new ShareData();
        data2.like = "3";
        data2.title = "title555333333333";
        data2.name = "用户2";
        mList.add(data2);
        ShareData data3 = new ShareData();
        data3.like = "46";
        data3.title = "title4625";
        data3.name = "用户5";
        mList.add(data3);
        ShareData data4 = new ShareData();
        data4.like = "3";
        data4.title = "title555";
        data4.name = "用户51";
        mList.add(data4);
        SocializingShareItemAdapter socializingShareItemAdapter = new SocializingShareItemAdapter(mList);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(socializingShareItemAdapter);
        return view;
    }
}
