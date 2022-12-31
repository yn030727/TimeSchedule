package com.example.module_challenge.ui.fragments_recyclerview;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.logic.model.ChallengePunch;

import java.util.List;

//RecyclerView的适配器
//这里的RecyclerView是挑战打卡界面的每日打卡信息
public class FirstFragmentAdapter extends RecyclerView.Adapter<FirstFragmentViewHolder>{
    List<ChallengePunch>  punchList ;

    public FirstFragmentAdapter(List<ChallengePunch> list){
        this.punchList = list;
    }

    @NonNull
    @Override
    public FirstFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstFragmentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return punchList.size();
    }
}
class FirstFragmentViewHolder extends RecyclerView.ViewHolder{

    public FirstFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
