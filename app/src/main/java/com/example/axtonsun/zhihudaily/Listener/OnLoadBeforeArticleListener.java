package com.example.axtonsun.zhihudaily.Listener;

import com.example.axtonsun.zhihudaily.Bean.Root;

/**
 * Created by AxtonSun on 2016/9/13.
 */
public interface OnLoadBeforeArticleListener {

    void onSuccess(Root articleBefore);

    void onFailure();
}
