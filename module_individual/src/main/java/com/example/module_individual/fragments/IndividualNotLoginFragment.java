package com.example.module_individual.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_individual.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import GeneralInformation.LoginInformation;
import eventbus.EventEditLogin;

public class IndividualNotLoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("FUFU:IndividualNotLogin","onCreateView");
        View view = inflater.inflate(R.layout.fragment_individual_not_login, container, false);
        //设置登录跳转
        Button btn_toLogin = view.findViewById(R.id.individual_not_login_tologin);
        btn_toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/loginActivity").navigation();
            }
        });
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        Button btn_tologin = view.findViewById(R.id.individual_not_login_tologin);
        btn_toLogin.setTypeface(typeface);
        return view;
    }

}