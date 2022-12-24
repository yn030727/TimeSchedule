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


@Route(path = "/challenge/ChallengeFragment_FifthCard")
public class ChallengeFragment_FifthCard extends Fragment {
    TextView fifthSaying;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fifth_card,container,false);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        fifthSaying = view.findViewById(R.id.challenge_fifthCard_saying);
        fifthSaying.setTypeface(typeface);
        return view;
    }
}
