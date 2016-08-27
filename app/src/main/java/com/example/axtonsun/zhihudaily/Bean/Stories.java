package com.example.axtonsun.zhihudaily.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class Stories implements Serializable{

    public String title;

    private String ga_prefix;

    private String images;

    private int type;

    private int id;

    public Stories() {
    }


    public Stories(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.images = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
