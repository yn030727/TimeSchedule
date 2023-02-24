package com.example.module_individual.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_individual.R;

import java.util.Locale;

import GeneralInformation.LoginInformation;

@Route(path = "/individual/IndividualFragment")
public class IndividualFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual,container,false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.d("1111111111111111111111", "IndividualFragment");

        //判断登录状态，不同状态跳转不同的fragment
//        LoginInformation loginInformation = new LoginInformation();
//        if(loginInformation.isLogin){
//            //已登录，跳转IndividualLoginFragment
//            view = inflater.inflate(R.layout.fragment_individual_login,container,false);
//        }else {
//            //未登录，显示未登录IndividualNotLoginFragment
//            view = inflater.inflate(R.layout.fragment_individual_not_login,container,false);
//        }

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setUser("程小曼");
        loginInformation.isLogin = true;
        if(loginInformation.isLogin){
            Log.d("HERE", "onCreateView: login111111111111");
            Bundle nBundle = new Bundle();
            nBundle.putString("account", loginInformation.getAccount());
            nBundle.putString("user", loginInformation.getUser());
            nBundle.putBoolean("isLogin", loginInformation.isLogin);
            //已登录
            Log.d("HERE", "onCreateView: loverbundle111111111111");
            Fragment loginFragment = IndividualLoginFragment.getInstance(nBundle);
            transaction.replace(R.id.fragment_individual, loginFragment);
        }else{
            Fragment notLoginFragment = new IndividualNotLoginFragment();
            transaction.replace(R.id.fragment_individual, notLoginFragment);
        }
        transaction.commit();
        return view;
    }
}
