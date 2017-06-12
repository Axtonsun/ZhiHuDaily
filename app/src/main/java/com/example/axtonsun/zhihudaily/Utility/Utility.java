package com.example.axtonsun.zhihudaily.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.axtonsun.zhihudaily.R;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class Utility {

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
        return activityNetwork != null && activityNetwork.isConnected();
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void noNetworkAlert(Context context) {
        Toast.makeText(context, "有网络存在吗??!", Toast.LENGTH_SHORT).show();
    }
    public static String getTime() {
        /**
         * d 月中的某一天。一位数的日期没有前导零。
         * MMM 月份的缩写名称，在 AbbreviatedMonthNames 中定义。
         * yyyy 包括纪元的四位数的年份。
         */
        //Calendar c = Calendar.getInstance();//获取一个Calendar对象并可以进行时间的计算
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //DateFormat df = DateFormat.getDateInstance();
        //return df.format(c.getTime());
        return format.format(new Date());
    }
}
