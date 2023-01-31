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
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.greenrobot.eventbus.EventBus;

import eventbus.EventChallenge_CardActivity_Back;
import eventbus.EventEditSchedule_MainActivity_Back;


@Route(path = "/editschedule/editschedulefragment")
public class EditScheduleFragment extends Fragment implements View.OnClickListener {
    TextView edit_schedule_create_textView;
    TextView editschedule_cancel_textView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editschedule_fragment , container , false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        edit_schedule_create_textView = view.findViewById(R.id.editschedule_create_textView);
        editschedule_cancel_textView = view.findViewById(R.id.editschedule_cancel_textView);

        edit_schedule_create_textView.setTypeface(typeface);
        editschedule_cancel_textView.setTypeface(typeface);
        editschedule_cancel_textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        editschedule_cancel_textView.setOnClickListener(this);
        return view;
    }



    //1. 点击事件
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.editschedule_cancel_textView){
            //点击取消界面
            EventBus.getDefault().postSticky(new EventEditSchedule_MainActivity_Back(true));
        }
    }
}
