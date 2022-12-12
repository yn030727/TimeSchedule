package com.example.module_challenge.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;

//这是一整个挑战Fragment
//在里面添加两个RecyclerView，放置挑战信息小卡片


@Route(path="/challenge/ChallengeFragment_Realtime")
public class ChallengeFragment_Realtime extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_challenge_realtime,container,false);
        return view;
    }
}
