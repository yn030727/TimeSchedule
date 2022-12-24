package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;


@Route(path = "/challenge/ChallengeFragment_ThirdCard")
public class ChallengeFragment_ThirdCard extends Fragment {
    TextView thirdSaying ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_card,container,false);
        //设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");

        thirdSaying = view.findViewById(R.id.challenge_thirdCard_saying);
        thirdSaying.setTypeface(typeface);


        return view;
    }
}
