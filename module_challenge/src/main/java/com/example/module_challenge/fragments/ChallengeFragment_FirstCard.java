package com.example.module_challenge.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_challenge.R;
import com.example.module_challenge.model.data.challenge_Database;
import com.example.module_challenge.model.data.challenge_data;
import com.example.module_challenge.model.data.challenge_data_dao;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;

//这是第一个挑战的详细介绍界面(之后几个与其类似，将不会有功能导图)
//功能及实现：
//代码目录：
//   0.初始化Fragment的相关操作
//   1.back图片和接下挑战按钮的功能介绍



@Route(path = "/challenge/ChallengeFragment_FirstCard")
public class ChallengeFragment_FirstCard extends Fragment implements View.OnClickListener {
    TextView challenge_firstCard_saying;
    ImageView challenge_firstCard_back;
    Button challenge_firstCard_button;
    //当前挑战项目是否被挑战，如果已经点击了同意挑战，那么就将这个值变为true
    //当挑战结束后，再将这个值重新变为false
    Boolean challenge_firstCard_status = false; //当前是否正在挑战中（关于青史界面是需要已经完成一次挑战的信息，不用写在这里）
    //数据库引擎
    //private challenge_DBEngine dbEngine;
    //在主线程跑数据库(测试环境)
    challenge_Database database;
    private challenge_data_dao dao;

    //0.初始化Fragment的相关操作
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_card,container,false);
        //设置瘦金体
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");


        challenge_firstCard_saying = view.findViewById(R.id.challenge_firstcard_saying);
        challenge_firstCard_saying.setTypeface(typeface);
        challenge_firstCard_back = view.findViewById(R.id.challenge_firstCard_back);
        challenge_firstCard_button = view.findViewById(R.id.challenge_firstCard_button);
        //dbEngine = new challenge_DBEngine(getContext());
        database = challenge_Database.getInstance(getContext());
        dao = database.getChallenge_data_dao();


        challenge_firstCard_back.setOnClickListener(this);
        challenge_firstCard_button.setOnClickListener(this);
        return view;
    }




    //1.两个相关的点击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.challenge_firstCard_back) {
            //点击back按键继续给main模块发送黏性事件，告诉它我要切换回之前的Fragment
            EventBus.getDefault().postSticky(new EventChallenge_CardActivity_Back(true));
        }else if(v.getId() == R.id.challenge_firstCard_button){
            challenge_firstCard_status = true;
            Log.d("Ning_Challenge", "firstCard : " + challenge_firstCard_status);
            //点击之后要更新数据库里面的内容
            //先查看数据库里面的当前信息状况： 1.没有 说明第一次挑战，那么添加到数据库中  2.false 已经完成过一次或多次该挑战，变为true  3.true  正在进行挑战当中，无需进行接下来的操作
            //PS：经过代码修改后，数据库中四个数据都是Integer类型，卡片的名字用1到7代替
            //challenge_data   id:当前卡片的名字 FirstCard(1)   challenge:1表示开始中，0表示没有   progress：表示进度 0到7     Complete: 表示之前是否完成过，和青史界面有关
            //1.点击接下挑战
            //2.访问Room，查找是否有当前id

            //3.没有
            //第一次挑战,将 id=FirstCard challenge=1 progress=0 complete=0

            //4.false
            //已经完成一次或多次挑战，重新再来挑战一次 (complete原先的值还是1，不需要改变)
            //id = FirstCard(不变)  challenge=1 progress=0(不变)  complete=1(不变)

            //5.true 正在进行挑战中，可以输出提示信息，并不需要做任何操作

            challenge_data data = dao.getChallengeById(1);
            if(data != null){
                Log.d("Ning_Room", "FirstCardFragment QueryById id： " + data.id);
                Log.d("Ning_Room", "FirstCardFragment QueryById challenge： " + data.challenge);
                Log.d("Ning_Room", "FirstCardFragment QueryById progress： " + data.progress);
                Log.d("Ning_Room", "FirstCardFragment QueryById complete： " + data.complete);
            }
            if(data == null){
                //3.没有
                dao.insertChallenge_data(new challenge_data(1,1,0,0));
                Toast.makeText(getContext(),"您接下了挑战，客官",Toast.LENGTH_SHORT).show();
            }else if(data.id == 1){
                if(data.challenge == 0){
                    //4.重新挑战
                    dao.updateChallenge_data(new challenge_data(1,1,0,1));
                    Toast.makeText(getContext(),"您再次接下了挑战",Toast.LENGTH_SHORT).show();
                }else if(data.challenge == 1){
                    //5.已经在挑战中了
                    Toast.makeText(getContext(),"不需要重复接下挑战哦，小主！",Toast.LENGTH_SHORT).show();
                }
            }







            //数据库引擎异步操作数据库
            //问题：主线程的进行受子线程的随时影响
            //但如果异步处理数据库中的数据，那么子线程会慢于主线程，所以直接返回了之前的答案

            /*            challenge_data data = dbEngine.queryByIdChallenge(1);
            Log.d("Ning_Room", "FirstCardFragment QueryById ： " + data.id);
            if(data.id == -1){
                //3.没有
                dbEngine.insertChallenge(new challenge_data(1,1,0,0));
                Log.d("Ning_Room", "FirstCardFragment insert ： " + data.id);
                Log.d("Ning_Room","FirstCardFragment insert : " + data.challenge);
            }else if(data.id == 1){
                if(data.challenge == 0){
                    //4.重新挑战
                    challenge_data upData = new challenge_data(1,1,0,1);
                    dbEngine.updateChallenge(upData);
                }else if(data.challenge == 1){
                    //5.已经在挑战中了
                    Toast.makeText(getContext(),"不需要重复接下挑战哦，小主！",Toast.LENGTH_SHORT).show();
                }
            }*/

        }
    }
}
