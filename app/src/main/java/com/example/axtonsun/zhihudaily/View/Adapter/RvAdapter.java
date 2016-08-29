package com.example.axtonsun.zhihudaily.View.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Activity.StoriesDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private List<Stories> stories;
    private Context context;
    public RvAdapter(Context context){
        this.context = context;
        stories = new ArrayList<>();
    }
    public RvAdapter(Context context,List<Stories> storiesList){
        this.context = context;
        stories = new ArrayList<>();
        this.stories = storiesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false));
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Utility.getImageLoader().displayImage(stories.get(position).getImages(),
                holder.news_image,Utility.getDisplayImageOptions());
        holder.news_title.setText(stories.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoriesDetailActivity.startActivity(context, stories.get(position));
            }
        });

    }
    @Override
    public int getItemCount() {
        return stories.size();
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
    public void refreshNewsList(List<Stories> storiesList) {
        stories.clear();
        stories.addAll(storiesList);
    }
}
