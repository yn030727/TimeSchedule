package com.example.module_socializing.adapter;

import android.media.Image;
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

import org.w3c.dom.Text;

import java.util.List;

public class SocializingShareItemAdapter extends RecyclerView.Adapter<ShareItemHolder> {
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
        holder.note_headProfile.setImageResource(data.getHead());
        holder.note_id.setText(data.getName());
        holder.note_text.setText(data.getText());
        if(data.getImage() != -1){
            holder.note_image.setImageResource(data.getImage());
        }
        holder.note_time.setText(data.getTime());
    }

    @Override
    public int getItemCount() {
        return mShareDataList.size();
    }

}
class ShareItemHolder extends RecyclerView.ViewHolder{
    ImageView note_image;
    TextView note_text;
    ImageFilterView note_headProfile;
    TextView note_id;
    TextView note_time;
    ImageView note_chat;
    ImageView note_good;
    ImageView note_share;

    public ShareItemHolder(@NonNull View itemView) {
        super(itemView);
        note_headProfile = itemView.findViewById(R.id.note_head_profile);
        note_id = itemView.findViewById(R.id.note_id);
        note_text = itemView.findViewById(R.id.note_text);
        note_image = itemView.findViewById(R.id.imageView2);
        note_time = itemView.findViewById(R.id.note_time);
        note_good = itemView.findViewById(R.id.note_good);
        note_chat = itemView.findViewById(R.id.note_chat);
        note_share = itemView.findViewById(R.id.note_share);
    }
}
