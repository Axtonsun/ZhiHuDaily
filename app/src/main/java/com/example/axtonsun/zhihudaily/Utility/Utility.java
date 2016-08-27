package com.example.axtonsun.zhihudaily.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.axtonsun.zhihudaily.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class Utility {

    public static ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.headimage) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.headimage)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.headimage)  //设置图片加载/解码过程中错误时候显示的图片
                .displayer(new FadeInBitmapDisplayer(0))//是否图片加载好后渐入的动画时间
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
        return activityNetwork != null && activityNetwork.isConnected();
    }

    public static void noNetworkAlert(Context context) {
        Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show();
    }
}
