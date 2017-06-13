package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.axtonsun.zhihudaily.R;

import java.util.Random;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class StartActivity extends AppCompatActivity {
    private ImageView start;
    private int[] images = {R.drawable.start4,R.drawable.start5};
    private boolean isFirst;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏 高宽都是全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_main);

        SharedPreferences preferences = getSharedPreferences("first",MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirst",true);
        if (isFirst) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initImage();
                    gotoMainActivity();
                }
            },1500);
           preferences.edit().putBoolean("isFirst",false).apply();
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoMainActivity();
                }
            },0);

        }
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(StartActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void initImage() {
        start = (ImageView) findViewById(R.id.startImage);
        Random random = new Random();
        int index = random.nextInt(images.length);
        start.setImageResource(images[index]);
        //进行缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        //动画播放完成后保持形状
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        start.startAnimation(scaleAnimation);
    }

}
