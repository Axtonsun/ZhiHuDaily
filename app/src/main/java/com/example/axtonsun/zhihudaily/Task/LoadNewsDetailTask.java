package com.example.axtonsun.zhihudaily.Task;


import android.os.AsyncTask;
import android.webkit.WebView;

import com.example.axtonsun.zhihudaily.Bean.NewsDetail;
import com.example.axtonsun.zhihudaily.Net.HttpUtil;
import com.example.axtonsun.zhihudaily.Net.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by AxtonSun on 2016/8/22.
 */
public class LoadNewsDetailTask extends AsyncTask<Integer, Void, NewsDetail> {//大写开头是包装类 有好多方法

    private WebView mWebView;

    public LoadNewsDetailTask(WebView mWebView) {
        this.mWebView = mWebView;
    }

    @Override
    protected NewsDetail doInBackground(Integer... params) {//表示任意多个相同类型的参数
                                                            //例如Boolean... booleans,就是表示任意多个bool量，调用的时候就用 booleans[0],booleans[1]...
        NewsDetail mNewsDetail = null;
        try {
            mNewsDetail = JsonHelper.parseJsonToDetail(HttpUtil.getNewsDetail(params[0]));
        } catch (IOException | JSONException e) {

        } finally {
            return mNewsDetail;
        }
    }

    @Override
    protected void onPostExecute(NewsDetail mNewsDetail) {
        String headerImage;
        if (mNewsDetail.getImage() == null || mNewsDetail.getImage() == "") {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";

        } else {
            headerImage = mNewsDetail.getImage();
        }
        //主要操作是 append 和 insert 方法，可重载这些方法，以接受任意类型的数据
        StringBuilder sb = new StringBuilder();
        //append 方法始终将这些字符添加到缓冲区的末端
        //insert 方法则在指定的点添加字符。
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(mNewsDetail.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(mNewsDetail.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        /**
         * WebView 内容
         * <link rel="stylesheet" type="text/css" href="news_detail.css" />
         * WebView的图片和版权 格式
         * "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
         */
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_detail.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + mNewsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());//sb.toString(); 把对象转成String类型

        mWebView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
                                    // 与Html相关的路径    | 传入获得的Html代码 | 代码类型通常"text/html" | 代码的编码方式 通常"UTF-8" | 历史URL??
        //loadDataWithBaseURL() 第一个和第五个为Null时 ===》 loadData()
    }
}
