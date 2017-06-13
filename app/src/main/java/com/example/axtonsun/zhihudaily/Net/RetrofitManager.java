package com.example.axtonsun.zhihudaily.Net;

import android.content.Context;

import com.example.axtonsun.zhihudaily.AppContext;
import com.example.axtonsun.zhihudaily.Bean.Before;
import com.example.axtonsun.zhihudaily.Bean.DetailStories;
import com.example.axtonsun.zhihudaily.Bean.Hot;
import com.example.axtonsun.zhihudaily.Bean.Latest;
import com.example.axtonsun.zhihudaily.Utility.API;
import com.example.axtonsun.zhihudaily.Utility.Utility;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Axton on 2017/6/10.
 */

public class RetrofitManager {

    private ZhiHuService mService;
    private OkHttpClient mBuilder;
    private Retrofit retrofit;

    private File cacheFile;
    private int cacheSize;
    private Cache cache;
    public static RetrofitManager builder(){
        return new RetrofitManager();
    }

    private RetrofitManager() {
        cacheFile = new File(AppContext.getContext().getCacheDir(),"response");//设置缓存路径
        cacheSize = 50 * 1024 * 1024; // 10 MiB 设置缓存大小
        cache = new Cache(cacheFile, cacheSize);

        mBuilder = new OkHttpClient.Builder()
                .addInterceptor(netInterceptor)
                .addNetworkInterceptor(netInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .client(mBuilder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.BASE_ZHIHU_URL)
                .build();
        mService = retrofit.create(ZhiHuService.class);
    }


    //拦截器
    private static Interceptor netInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!Utility.checkNetworkConnection(AppContext.getContext())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if(Utility.checkNetworkConnection(AppContext.getContext())){
                //有网的时候读接口上的@Headers里的配置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else{
                int maxTime = 60 * 60 * 24 * 7;//缓存过期时间为七天
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    public Observable<Latest> getLatest(){
        return mService.getLatest();
    }

    public Observable<Before> getBefore(int date){
        return mService.getBefore(date);
    }
    public Observable<Hot> getHot(){
        return mService.getHot();
    }
    public Observable<DetailStories> getDetail(int id){
        return mService.getDetail(id);
    }

}
