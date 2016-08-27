package com.example.axtonsun.zhihudaily;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }
    private void initImageLoader(Context context){
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),"imageLoader/Cache");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .diskCache(new UnlimitedDiskCache(cacheDir)).writeDebugLogs()
                .imageDownloader(new BaseImageDownloader(context,30 * 1000,30 * 1000))
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
