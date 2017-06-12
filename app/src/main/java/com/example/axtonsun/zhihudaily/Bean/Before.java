package com.example.axtonsun.zhihudaily.Bean;

import java.util.List;

/**
 * Created by Axton on 2017/6/11.
 */

public class Before {
    private String date;
    private List<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }
}
