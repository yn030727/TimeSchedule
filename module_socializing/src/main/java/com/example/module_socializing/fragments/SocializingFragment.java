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
        data.name = "程小曼";
        mList.add(data);
        ShareData data1 = new ShareData();
        data1.like = "3";
        data1.title = "title555";
        data1.name = "程小曼1";
        mList.add(data1);
        SocializingShareItemAdapter socializingShareItemAdapter = new SocializingShareItemAdapter(mList);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(socializingShareItemAdapter);
        return view;
    }
}
