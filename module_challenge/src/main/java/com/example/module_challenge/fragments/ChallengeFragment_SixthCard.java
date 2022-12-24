package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;


@Route(path = "/challenge/ChallengeFragment_SixthCard")
public class ChallengeFragment_SixthCard extends Fragment {
    TextView sixthSaying;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sixth_card,container,false);
        sixthSaying = view.findViewById(R.id.challenge_sixthCard_saying);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        sixthSaying.setTypeface(typeface);

        return view;
    }
}
