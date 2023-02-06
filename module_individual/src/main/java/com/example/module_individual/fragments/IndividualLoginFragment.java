package com.example.module_individual.fragments;

import android.media.metrics.Event;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.module_individual.R;

import org.greenrobot.eventbus.EventBus;

import GeneralInformation.LoginInformation;

public class IndividualLoginFragment extends Fragment {

    public static IndividualLoginFragment getInstance(Bundle bundle) {
        IndividualLoginFragment individualLoginFragment = new IndividualLoginFragment();
        individualLoginFragment.setArguments(bundle);
        return individualLoginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual_login, container, false);

        Log.d("HERE", "onCreateView: intologinfragment111111111111");


        TextView login_users = view.findViewById(R.id.individual_login_user);
        login_users.setText(getArguments().getString("user"));

        EventBus.getDefault().register(this);
        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setUser(getArguments().getString("user"));
        loginInformation.setAccount(getArguments().getString("account"));
        loginInformation.isLogin = getArguments().getBoolean("isLogin");
        EventBus.getDefault().post(loginInformation);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //反注册
        EventBus.getDefault().unregister(this);
    }
}

