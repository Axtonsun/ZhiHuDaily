package com.example.axtonsun.zhihudaily.View.Adapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Activity.StoriesDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvHotAdapter extends RecyclerView.Adapter<RvHotAdapter.MyViewHolder> {
    private List<Hot.RecentBean> hotStories;
    private Context context;

    public RvHotAdapter(Context context,List<Hot.RecentBean> hotStories) {
        this.hotStories = hotStories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false));
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context)
                .load(hotStories.get(position).getThumbnail())
                .placeholder(R.drawable.load)
                .into(holder.news_image);
        holder.news_title.setText(hotStories.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoriesDetailActivity.startHotActivity(context, hotStories.get(position));
            }
        });

    }
    @Override
    public int getItemCount() {
        return hotStories.size();
        //return stories==null?0:stories.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView news_title;
        ImageView news_image;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_image = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }

}