package com.example.module_challenge.ui.fragments_recyclerview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_challenge.R;
import com.example.module_challenge.model.data.challenge_Database;
import com.example.module_challenge.model.data.challenge_data;
import com.example.module_challenge.model.data.challenge_data_dao;
import com.example.module_challenge.model.symbol.ChallengePunch;

import java.util.List;

//RecyclerView的适配器
//这里的RecyclerView是挑战打卡界面的每日打卡信息
public class ThirdFragmentAdapter extends RecyclerView.Adapter<ThirdFragmentViewHolder>{
    List<ChallengePunch>  punchList ;
    challenge_Database database;//在主线程跑数据库(测试环境)
    private challenge_data_dao dao;
    TextView carrying_progress;

    public ThirdFragmentAdapter(List<ChallengePunch> list , TextView progress){
        this.punchList = list;
        this.carrying_progress = progress;
    }

    @NonNull
    @Override
    public ThirdFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        database = challenge_Database.getInstance(parent.getContext());
        dao = database.getChallenge_data_dao();
        View view = View.inflate(parent.getContext(), R.layout.layout_fragment_third_carrying_recyclerview , null);
        ThirdFragmentViewHolder thirdFragmentViewHolder = new ThirdFragmentViewHolder(view);
        return thirdFragmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdFragmentViewHolder holder, int position) {
        int layer = position;
        challenge_data data = dao.getChallengeById(3);
        int progress1 = data.getProgress();
        ChallengePunch punch = punchList.get(position);
        holder.day.setText(punch.getChallenge_day_carrying());
        holder.taskName.setText(punch.getChallenge_name_carrying());
        holder.taskDescription.setText(punch.getChallenge_introduce_carrying());
        holder.imageView.setImageResource(punch.getChallenge_image_carrying());
        if(layer < progress1){
            holder.card.setBackgroundResource(R.drawable.challenge_card_shape2);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                //无论如何，每一次点击都会导致card背景色的变化，和界面内进度文本(获得控件，修改值，值也可以从数据库中获取)，以及界面外进度条的变化(改变数据库内的)
                //判断上一天的是否被点亮
                challenge_data data = dao.getChallengeById(3);
                int progress = data.getProgress();
                int challenge = data.getChallenge();
                int complete = data.getComplete();
                if(layer == progress){
                    //完成当前挑战
                    dao.updateChallenge_data(new challenge_data(3,challenge,progress+1,complete));
                    double db = (double) (progress+1)/7;
                    int num = (int)(db*100);
                    carrying_progress.setText("已完成"+num+"%");
                    holder.card.setBackgroundResource(R.drawable.challenge_card_shape2);
                    Toast.makeText(v.getContext(), "您已经完成了当天挑战！", Toast.LENGTH_SHORT).show();
                    Log.d("Ning_FirstFragmentAdapter  ", "card onClick");
                }else if(layer > progress){
                    Toast.makeText(v.getContext(), "请先完成前面的挑战",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(), "您已经完成过挑战了",Toast.LENGTH_SHORT).show();
                    Log.d("Ning_FirstFragmentAdapter  ", holder.card.getBackground()+"");
                }
                if(progress == 6){
                    //完成全部挑战

                    //可以写一个恭喜界面

                    Toast.makeText(v.getContext(), "您已经完成了全部挑战",Toast.LENGTH_SHORT).show();
                    dao.updateChallenge_data(new challenge_data(3 ,0,0,1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return punchList.size();
    }
}
class ThirdFragmentViewHolder extends RecyclerView.ViewHolder{

    TextView day;
    TextView taskName;
    TextView taskDescription;
    ImageView imageView;
    View card;
    public ThirdFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.challenge_thirdFragment_carrying_textView);
        taskName = itemView.findViewById(R.id.challenge_thirdFragment_carrying_taskName);
        taskDescription = itemView.findViewById(R.id.challenge_thirdFragment_carrying_taskDescription);
        imageView = itemView.findViewById(R.id.challenge_thirdFragment_carrying_image);
        card = itemView.findViewById(R.id.challenge_thirdFragment_carrying_constraint);
    }
}