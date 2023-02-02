package com.example.module_editscheudle;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;
import eventbus.EventEditSchedule_MainActivity_Back;


@Route(path = "/editschedule/editschedulefragment")
public class EditScheduleFragment extends Fragment implements View.OnClickListener {
    TextView edit_schedule_create_textView;
    TextView edit_schedule_cancel_textView;
    ConstraintLayout edit_schedule_add_function;
    TextView edit_schedule_add_function_textView;
    TextView edit_schedule_add_normal;
    TextView edit_schedule_add_study;
    TextView edit_schedule_add_healthy;
    TextView edit_schedule_add_sport;
    TextView edit_schedule_add_life;
    TextView edit_schedule_add_money;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editschedule_fragment , container , false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        edit_schedule_create_textView = view.findViewById(R.id.editschedule_create_textView);
        edit_schedule_cancel_textView = view.findViewById(R.id.editschedule_cancel_textView);
        edit_schedule_add_function = view.findViewById(R.id.editschedule_add_function);
        edit_schedule_add_function_textView = view.findViewById(R.id.editschedule_add_function_textView);
        edit_schedule_add_normal = view.findViewById(R.id.editschedule_add_normal);
        edit_schedule_add_study = view.findViewById(R.id.editschedule_add_study);
        edit_schedule_add_healthy = view.findViewById(R.id.editschedule_add_healthy);
        edit_schedule_add_sport = view.findViewById(R.id.editschedule_add_sport);
        edit_schedule_add_life = view.findViewById(R.id.editschedule_add_life);
        edit_schedule_add_money = view.findViewById(R.id.editschedule_add_money);

        edit_schedule_create_textView.setTypeface(typeface);
        edit_schedule_cancel_textView.setTypeface(typeface);
        edit_schedule_cancel_textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        edit_schedule_add_function_textView.setTypeface(typeface);
        edit_schedule_add_function_textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        edit_schedule_add_normal.setTypeface(typeface);
        edit_schedule_add_study.setTypeface(typeface);
        edit_schedule_add_healthy.setTypeface(typeface);
        edit_schedule_add_sport.setTypeface(typeface);
        edit_schedule_add_life.setTypeface(typeface);
        edit_schedule_add_money.setTypeface(typeface);

        edit_schedule_cancel_textView.setOnClickListener(this);
        edit_schedule_add_normal.setOnClickListener(this);
        edit_schedule_add_study.setOnClickListener(this);
        edit_schedule_add_healthy.setOnClickListener(this);
        edit_schedule_add_sport.setOnClickListener(this);
        edit_schedule_add_life.setOnClickListener(this);
        edit_schedule_add_money.setOnClickListener(this);
        return view;
    }



    //1. 点击事件
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.editschedule_cancel_textView){
            //点击取消界面
            EventBus.getDefault().postSticky(new EventEditSchedule_MainActivity_Back(true));
        }else if(v.getId() == R.id.editschedule_add_normal){

        }else if(v.getId() == R.id.editschedule_add_study){

        }else if(v.getId() == R.id.editschedule_add_healthy){

        }else if(v.getId() == R.id.editschedule_add_sport){

        }else if(v.getId() == R.id.editschedule_add_life){

        }else if(v.getId() == R.id.editschedule_add_money){

        }
    }
}
