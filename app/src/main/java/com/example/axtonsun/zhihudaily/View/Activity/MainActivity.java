package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;

import com.example.axtonsun.zhihudaily.Bean.DayNight;
import com.example.axtonsun.zhihudaily.Bean.DetailStories;
import com.example.axtonsun.zhihudaily.Bean.Stories;
import com.example.axtonsun.zhihudaily.Utility.SharedPreferencesUtils;
import com.example.axtonsun.zhihudaily.Net.RetrofitManager;
import com.example.axtonsun.zhihudaily.View.Adapter.VpAdapter;
import com.example.axtonsun.zhihudaily.View.Fragment.HotList;
import com.example.axtonsun.zhihudaily.View.Fragment.RvList;
import com.example.axtonsun.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();

    private List<Integer> downloadId = new ArrayList<>();

    private SharedPreferencesUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNaviView();
    }
    private void initNaviView() {
        utils = new SharedPreferencesUtils(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("今日要闻"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("热门文章"));
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabTitles.add("今日要闻");
        tabTitles.add("热门文章");
        fragments.add(new RvList());
        fragments.add(new HotList());
        VpAdapter viewPagerAdapter = new VpAdapter(getSupportFragmentManager(),fragments,tabTitles);

        viewPager.setAdapter(viewPagerAdapter);//设置适配器
        mTabLayout.setupWithViewPager(viewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            MenuItem menuItem;
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                menuItem = item;
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        break;
                    case R.id.nav_photo:
                        Intent intent = new Intent(MainActivity.this,FavouriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_down:
                        for (Stories data : ((RvList) fragments.get(0)).getDownloadData()) {
                            downloadId.add(data.getId());
                        }
//                        menuItemDownload.setVisible(false);
                        Snackbar.make(MainActivity.this.getWindow().getDecorView(), "开始离线...", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        download(downloadId.get(0));
                        break;
                    case R.id.nav_about:
                        Intent intent1 = new Intent(MainActivity.this,AboutViewActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_night_mode:
                        if (utils.isDay()) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            utils.setMode(DayNight.NIGHT);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                          utils.setMode(DayNight.DAY);
                        }
                        recreate();
                        break;
                }
                return true;
            }
        });
    }

    private void download(int id) {
        RetrofitManager.builder().getDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailStories>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(DetailStories latest) {
                        downloadId.remove(0);
                        if (downloadId.size() > 0) {
                            download(downloadId.get(0));
                        }else{
                            //menuItemDownload.setVisible(true);
                            Snackbar.make(MainActivity.this.getWindow().getDecorView(), "离线完成", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                    }
                });
    }

}