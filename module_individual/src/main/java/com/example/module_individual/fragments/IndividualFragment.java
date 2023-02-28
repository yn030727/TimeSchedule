package com.example.module_individual.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_individual.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;
import java.util.Set;

import GeneralInformation.LoginInformation;
import eventbus.EventEditLogin;
import eventbus.EventEditSchedule_MainActivity_Back;
import eventbus.EventLoginInformation;
import eventbus.EventSchedule;

@Route(path = "/individual/IndividualFragment")
public class IndividualFragment extends Fragment {

    LoginInformation loginInformation = new LoginInformation();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual,container,false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.d("FUFUFFFFFUUUUU", "IndividualFragment");
        EventBus.getDefault().register(this);

        //判断登录状态，不同状态跳转不同的fragment
//        LoginInformation loginInformation = new LoginInformation();
//        if(loginInformation.isLogin){
//            //已登录，跳转IndividualLoginFragment
//            view = inflater.inflate(R.layout.fragment_individual_login,container,false);
//        }else {
//            //未登录，显示未登录IndividualNotLoginFragment
//            view = inflater.inflate(R.layout.fragment_individual_not_login,container,false);
//        }

        Log.d("FUFU:IndividualFragment", "islogin = "+loginInformation.isLogin);
        if(loginInformation.isLogin){
            Log.d("FUFU:IndividualFragment", "islogin = true");
            Bundle nBundle = new Bundle();
            nBundle.putString("account", loginInformation.getAccount());
            nBundle.putString("user", loginInformation.getUser());
            nBundle.putBoolean("isLogin", loginInformation.isLogin);
            //已登录
            Log.d("HERE", "onCreateView: loverbundle111111111111");
            Fragment loginFragment = IndividualLoginFragment.getInstance(nBundle);
            transaction.replace(R.id.fragment_individual, loginFragment);

        }else{
            Log.d("FUFU:IndividualFragment", "islogin = false");
            Fragment notLoginFragment = new IndividualNotLoginFragment();
            transaction.replace(R.id.fragment_individual, notLoginFragment);
        }
        transaction.commit();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true)
    public void showEventLoginInformation(EventEditLogin eventLoginInformation){
        loginInformation = new LoginInformation();
        Log.d("FUFU:IndividualFragment", "getEventMessage");
        loginInformation.isLogin = eventLoginInformation.getLogin();
        loginInformation.setAccount(eventLoginInformation.getAccount());
        loginInformation.setUser(eventLoginInformation.getUser());
        Log.d("FUFU:IndividualFragment",loginInformation.getUser());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Ning","onStart");

        if(loginInformation.isLogin){
            Bundle nBundle = new Bundle();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            nBundle.putString("account", loginInformation.getAccount());
            nBundle.putString("user", loginInformation.getUser());
            nBundle.putBoolean("isLogin", loginInformation.isLogin);
            //已登录
            Log.d("HERE", "onCreateView: loverbundle111111111111");
            Fragment loginFragment = IndividualLoginFragment.getInstance(nBundle);
            transaction.replace(R.id.fragment_individual, loginFragment);
            transaction.commit();
            Log.d("HERE", "onCreateView: loverbundle111111111111");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
