package com.example.module_socializing.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_socializing.R;
import com.example.module_socializing.data.ShareData;

import java.util.List;

public class SocializingShareItemAdapter extends RecyclerView.Adapter<SocializingShareItemAdapter.ShareItemHolder> {
    private List<ShareData> mShareDataList;
    private int itemWidth;

    public SocializingShareItemAdapter(List<ShareData> ShareDataList){
        mShareDataList = ShareDataList;
    }

    @NonNull
    @Override
    public ShareItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.socializing_share_recycler_item, parent, false);
        ShareItemHolder holder = new ShareItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShareItemHolder holder, int position) {
        ShareData data = mShareDataList.get(position);
        //holder.noteHeadProfile.setImageResource(data.profit);
        holder.noteTitle.setText(data.getTitle());
        Log.d("Here", "position =  " + position);
        Log.d("Here", "Title =  " + data.getTitle());
        Log.d("Here", "id =  " + data.getName());
        Log.d("Here", "like =  " + data.getLike());
        holder.noteId.setText(data.getName());
        if(position == 0){
            holder.notePicture.setImageResource(R.drawable.ic_launcher_background);
            holder.noteHeadProfile.setImageResource(R.drawable.head2);
        }else if(position == 1){
            holder.notePicture.setImageResource(R.drawable.picture2);
            holder.noteHeadProfile.setImageResource(R.drawable.head1);
        }else if(position == 2){
            holder.noteHeadProfile.setImageResource(R.drawable.head1);
            holder.notePicture.setImageResource(R.drawable.picture3);
        }else if(position == 3){
            holder.notePicture.setImageResource(R.drawable.picture4);
            holder.noteHeadProfile.setImageResource(R.drawable.head2);
        }else if(position == 4){
            holder.notePicture.setImageResource(R.drawable.picture5);
            holder.noteHeadProfile.setImageResource(R.drawable.head1);
        }
        //holder.notePicture.setImageResource(data.photo);
        holder.likeNum.setText(data.getLike());
        //holder.noteTitle.setText(data.title);

    }

    @Override
    public int getItemCount() {
        return mShareDataList.size();
    }

    class ShareItemHolder extends RecyclerView.ViewHolder{
        ImageView notePicture;
        TextView noteTitle;
        ImageFilterView noteHeadProfile;
        TextView noteId;
        ImageView noteLike;
        TextView likeNum;

        public ShareItemHolder(@NonNull View itemView) {
            super(itemView);
            notePicture = itemView.findViewById(R.id.note_picture);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteHeadProfile = itemView.findViewById(R.id.note_head_profile);
            noteId = itemView.findViewById(R.id.note_id);
            noteLike = itemView.findViewById(R.id.note_like);
            likeNum = itemView.findViewById(R.id.note_like_number);
        }
    }
}
