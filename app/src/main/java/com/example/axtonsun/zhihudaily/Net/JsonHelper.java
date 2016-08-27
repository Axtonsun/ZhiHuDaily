package com.example.axtonsun.zhihudaily.Net;

import com.example.axtonsun.zhihudaily.Bean.HotStories;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Bean.NewsDetail;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by AxtonSun on 2016/8/22.
 */
public class JsonHelper {

    public static List<Stories> parseJsonToList(String json) throws JSONException {
        JSONObject lastNews = new JSONObject(json);
        JSONArray lastArray = lastNews.getJSONArray("stories");
        List<Stories> storiesList = new ArrayList<Stories>();
        for (int i = 0; i < lastArray.length(); i++) {
            JSONObject newsInJson = lastArray.getJSONObject(i);
            int id = newsInJson.optInt("id");
            String title = newsInJson.optString("title");
            String image = "";
            if (newsInJson.has("images")) {
                image = (String) newsInJson.getJSONArray("images").get(0);

            }
            Stories stories = new Stories(id, title, image);
            storiesList.add(stories);
        }
        return storiesList;
    }

    public static List<HotStories> parseJsonToHotList(String string)throws JSONException{
        JSONObject hotNews = new JSONObject(string);
        JSONArray hotArray = hotNews.getJSONArray("top_stories");
        List<HotStories> storiesList= new ArrayList<>();
        for (int j = 0;j<hotArray.length();j++){
            JSONObject jsonObject = hotArray.getJSONObject(j);
            int id = jsonObject.optInt("id");
            String hotTitle = jsonObject.optString("title");
            String hotImage = "";
            if (jsonObject.has("image")){
                hotImage = jsonObject.optString("image");
            }
            HotStories hotStories = new HotStories(id,hotTitle,hotImage);
            storiesList.add(hotStories);
        }
        return storiesList;
    }

    public static NewsDetail parseJsonToDetail(String json) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(json, NewsDetail.class);
    }

}
