package com.example.axtonsun.zhihudaily.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class HttpUtil {

   public static String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
   public static String NEWSLIST_BEFORE = "http://news.at.zhihu.com/api/4/news/before/";
   public static String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";

   public static String get(String urlAddr) throws IOException {
      HttpURLConnection con = null;
      try {
         URL url = new URL(urlAddr);
         con = (HttpURLConnection) url.openConnection();
         // 设定请求的方法为"GET"，默认是GET
         con.setRequestMethod("GET");
         //con.setUseCaches(true);
         // 设定传送的内容类型是可序列化的java对象
         // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛
         con.setRequestProperty("User-Agent", "Mozilla/5.0");
         //con.getResponseCode() 获得URL响应状态码返回代码为200 说明服务器已成功处理了请求。
         //通常，这表示服务器提供了请求的网页
         if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {//HTTP 状态码 200：OK。
            //http请求实际上直到con.getInputStream()这个函数里面才正式发送出去
            //将内存缓冲区中封装号的完整的HTTP请求电文发送到服务端
            // [将请求结果转换成String类型]
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));//InputStreamReader字符输入流
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
            }
            in.close();
            return response.toString();
         } else {
            throw new IOException("Network Error - response code: " + con.getResponseCode());
         }
      } finally {
         if (con != null) {
            con.disconnect();//取消连接
         }
      }
   }
   public static String getLastNewsList() throws IOException {
      return get(NEWSLIST_LATEST);
   }

   public static String getBeforeNewsList(String date)throws IOException{
      return get(NEWSLIST_BEFORE + date);
   }
   public static String getNewsDetail(int id) throws IOException {return get(NEWSDETAIL + id);}
}
