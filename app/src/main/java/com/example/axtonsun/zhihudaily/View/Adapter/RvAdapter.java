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

import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.R;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Activity.StoriesDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL_ITEM = 0;
    private static final int TYPE_FOOTER_ITEM = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //默认为0
    private int load_more_status = 0;


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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    /*        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
            return myViewHolder;*/
            if (viewType == TYPE_NORMAL_ITEM) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_rv, parent, false);
                //可以做一些属性设置   甚至事件监听绑定
                final NormalViewHolder vh = new NormalViewHolder(view);
                return vh;
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_footer, parent, false);
                //可以做一些属性设置   甚至事件监听绑定
                FooterViewHolder vh = new FooterViewHolder(view);
                return vh;
            }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalViewHolder) {
            Utility.getImageLoader().displayImage(stories.get(position).getImages(),
                    ((NormalViewHolder)holder).news_image, Utility.getDisplayImageOptions());
            ((NormalViewHolder)holder).news_title.setText(stories.get(position).getTitle());
            ((NormalViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StoriesDetailActivity.startActivity(context, stories.get(position));
                }
            });
        }else if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.foot_view_item_tv.setText("上拉加载更多");
                    footerViewHolder.foot_view_item_tv.setVisibility(View.VISIBLE);
                    footerViewHolder.progressBar.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    footerViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    //footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
                    footerViewHolder.progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
    @Override
    public int getItemCount() {
        return stories.size() + 1;
        //return stories==null?0:stories.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            return TYPE_FOOTER_ITEM;
        }else {
            return TYPE_NORMAL_ITEM;
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder{
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
    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        public TextView foot_view_item_tv;
        public ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            foot_view_item_tv = (TextView) itemView.findViewById(R.id.tv_content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_view);
        }
    }
    public void refreshNewsList(List<Stories> storiesList) {
        Log.v("ssssss", String.valueOf(storiesList) + " " + String.valueOf(stories));
        stories.clear();
        stories.addAll(storiesList);

    }
    public void setMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();//取消不会有上拉加载的动画效果
        //notifyDataSetChanged方法通过一个外部的方法控制如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容。
    }
}
