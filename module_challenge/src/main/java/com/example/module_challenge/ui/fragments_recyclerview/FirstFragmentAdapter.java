package com.example.module_challenge.ui.fragments_recyclerview;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
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
        View view = View.inflate(parent.getContext(), R.layout.layout_fragment_first_carrying_recyclerview , null);
        FirstFragmentViewHolder firstFragmentViewHolder = new FirstFragmentViewHolder(view);
        return firstFragmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstFragmentViewHolder holder, int position) {
        ChallengePunch punch = punchList.get(position);
        holder.day.setText(punch.getChallenge_day_carrying());
        holder.taskName.setText(punch.getChallenge_name_carrying());
        holder.taskDescription.setText(punch.getChallenge_introduce_carrying());
        holder.imageView.setImageResource(punch.getChallenge_image_carrying());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return punchList.size();
    }
}
class FirstFragmentViewHolder extends RecyclerView.ViewHolder{

    TextView day;
    TextView taskName;
    TextView taskDescription;
    ImageView imageView;
    View card;
    public FirstFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.challenge_firstFragment_carrying_textView);
        taskName = itemView.findViewById(R.id.challenge_firstFragment_carrying_taskName);
        taskDescription = itemView.findViewById(R.id.challenge_firstFragment_carrying_taskDescription);
        imageView = itemView.findViewById(R.id.challenge_firstFragment_carrying_image);
        card = itemView.findViewById(R.id.challenge_firstFragment_carrying_constraint);
    }
}
