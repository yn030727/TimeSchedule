package com.example.module_challenge.fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_challenge.R;

import java.util.ArrayList;


//第二界面:石以砥焉
//按钮点击改变大小，来表示界面的跳转
//最好的办法就是在当前界面创建一个容器，点击切换两个界面
//功能:
//代码目录:
//  0.声明变量

@Route(path = "/challenge/ChallengeFragment")
public class ChallengeFragment extends Fragment implements View.OnClickListener {
    //0.声明变量
    TextView challenge_title_challenge_text ;//第二节面顶部两个标题
    TextView challenge_title_honor_text;
    ArrayList<Fragment> challenge_fragments;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge,container,false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        //实例化
        challenge_title_challenge_text = view.findViewById(R.id.challenge_title_challenge_text);
        challenge_title_honor_text = view.findViewById(R.id.challenge_title_honor_text);
        challenge_title_challenge_text.setTypeface(typeface);
        challenge_title_honor_text.setTypeface(typeface);
        //事件的监听
        challenge_title_challenge_text.setOnClickListener(this);
        challenge_title_honor_text.setOnClickListener(this);

        //初始化Fragment
        replaceFragment((Fragment) ARouter.getInstance().build("/challenge/ChallengeFragment_Realtime").navigation());
        return view;
    }



    //文本框点击跳转
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.challenge_title_challenge_text) {
            challenge_title_challenge_text.setTextSize(30);
            challenge_title_honor_text.setTextSize(24);
            replaceFragment((Fragment) ARouter.getInstance().build("/challenge/ChallengeFragment_Realtime").navigation());
        } else if (id == R.id.challenge_title_honor_text) {
            challenge_title_challenge_text.setTextSize(24);
            challenge_title_honor_text.setTextSize(30);
            replaceFragment((Fragment) ARouter.getInstance().build("/honor/HonorFragment") .navigation());
        }
    }
    //点击切换Fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Challenge_main_fragmentContainer,fragment);
        fragmentTransaction.commit();
    }



    //初始化两个界面的Fragment
    private void initFragments(){
        challenge_fragments = new ArrayList<>();
        Fragment challengeFragment_Realtime = (Fragment) ARouter.getInstance().build("/challenge/ChallengeFragment_Realtime").navigation();
        Fragment honorFragment = (Fragment) ARouter.getInstance().build("/honor/HonorFragment").navigation();
        challenge_fragments.add(challengeFragment_Realtime);
        challenge_fragments.add(honorFragment);
    }
}
