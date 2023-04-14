package com.example.module_individual.adapter;

import android.graphics.Typeface;
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


    public IndividualLoginRecyclerAdapter(){

    }

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
        }


    }

    @Override
    public int getItemCount() {
        return 4;
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
