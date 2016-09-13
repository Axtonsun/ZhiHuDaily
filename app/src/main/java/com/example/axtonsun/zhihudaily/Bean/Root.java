package com.example.axtonsun.zhihudaily.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AxtonSun on 2016/9/13.
 */
public class Root implements Serializable{
    private String date;

    private List<Stories> stories ;

    public Root(String date, List<Stories> stories) {
        this.date = date;
        this.stories = stories;
    }

    public  void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setStories(List<Stories> stories){
        this.stories = stories;
    }
    public List<Stories> getStories(){
        return this.stories;
    }

}
