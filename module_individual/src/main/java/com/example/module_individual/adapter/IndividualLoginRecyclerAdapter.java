package com.example.module_individual.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_individual.R;

public class IndividualLoginRecyclerAdapter extends RecyclerView.Adapter<IndividualLoginRecyclerAdapter.RecyclerItemHolder> {

    @NonNull
    @Override
    public RecyclerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_login_recycler_item, parent, false);
        RecyclerItemHolder holder = new RecyclerItemHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerItemHolder holder, int position) {

        Log.d("22222", "onBindViewHolder: position - " + position);

        if(position == 0){
            holder.drawerTextView.setText("服务");
            holder.drawerImageView.setImageResource(R.drawable.individual_recycler_serives);
        } else if( position == 1){
            holder.drawerTextView.setText("收藏");
            holder.drawerImageView.setImageResource(R.drawable.individual_recycler_collect);
        } else if( position == 2){
            holder.drawerTextView.setText("朋友圈");
            holder.drawerImageView.setImageResource(R.drawable.individual_recycler_share);
        } else if( position == 3){
            holder.drawerTextView.setText("挑战记录");
            holder.drawerImageView.setImageResource(R.drawable.individual_recycler_record);
        } else if( position == 4){
            holder.drawerTextView.setText("设置");
            holder.drawerImageView.setImageResource(R.drawable.induvidual_recycler_setting);
        } else if( position == 5){
            holder.drawerTextView.setText("钱包");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 6){
            holder.drawerTextView.setText("免流量");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 7){
            holder.drawerTextView.setText("好物体验");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 8){
            holder.drawerTextView.setText("订单");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 9){
            holder.drawerTextView.setText("购物车");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 10){
            holder.drawerTextView.setText("卡券");
        } else if( position == 11){
            holder.drawerTextView.setText("心愿单");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        } else if( position == 12){
            holder.drawerTextView.setText("小红书会员");
        } else if( position == 13){
            holder.drawerTextView.setText("社区公约");
            holder.drawerImageView.setImageResource(R.drawable.me_drawer_find_friend);
        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class RecyclerItemHolder extends RecyclerView.ViewHolder {
        ImageView drawerImageView;
        TextView drawerTextView;

        public RecyclerItemHolder(@NonNull View itemView) {
            super(itemView);
            drawerImageView = (ImageView) itemView.findViewById(R.id.individual_login_recycler_drawer_item_imageview);
            drawerTextView = (TextView) itemView.findViewById(R.id.individual_login_recycler_drawer_item_textview);
        }
    }

}
