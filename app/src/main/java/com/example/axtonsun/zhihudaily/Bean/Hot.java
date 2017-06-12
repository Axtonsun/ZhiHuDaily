package com.example.axtonsun.zhihudaily.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AxtonSun on 2016/11/29.
 */

public class Hot implements Serializable{

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean implements Serializable{
        /**
         * news_id : 9013693
         * url : http://news-at.zhihu.com/api/2/news/9013693
         * thumbnail : http://pic3.zhimg.com/c0d708639383670ba6c9009010c8163a.jpg
         * title : 瞎扯 · 如何正确地吐槽
         */

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
