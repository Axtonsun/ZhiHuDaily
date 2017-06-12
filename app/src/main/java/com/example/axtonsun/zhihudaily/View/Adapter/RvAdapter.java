package com.example.axtonsun.zhihudaily.View.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Activity.StoriesDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.NormalViewHolder> {

    private List<Stories> stories;
    private Context context;



    public RvAdapter(Context context,List<Stories> storiesList){
        this.context = context;
        this.stories = storiesList;
    }
    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout
                .item_rv, parent, false);
        //可以做一些属性设置   甚至事件监听绑定
        return new NormalViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder,int position) {
        final Stories l  = stories.get(position);
        if (stories.get(position).getImages() == null){
            Glide.with(context).load(l.getImages()).into(holder.news_image);
        }else {
            Glide.with(context)
                    .load(l.getImages().get(0))
                    .placeholder(R.drawable.load)
                    .into(holder.news_image);
        }
        holder.news_title.setText(stories.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoriesDetailActivity.startActivity(context,l);
            }
        });
    }


    @Override
    public int getItemCount() {
        return stories.size();
    }


    class NormalViewHolder extends RecyclerView.ViewHolder{
        TextView news_title;
        ImageView news_image;
        CardView cardView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_image = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }

}
