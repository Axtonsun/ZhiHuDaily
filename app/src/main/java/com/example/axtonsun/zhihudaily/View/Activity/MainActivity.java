package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.axtonsun.zhihudaily.View.Adapter.VpAdapter;
import com.example.axtonsun.zhihudaily.View.Fragment.HotList;
import com.example.axtonsun.zhihudaily.View.Fragment.RvList;
import com.example.axtonsun.zhihudaily.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private boolean isNight;
    private SharedPreferences preferences;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("axton",this.MODE_PRIVATE);
        initNaviView();
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    private void initNaviView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText(getTime()));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("热门文章"));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        VpAdapter viewPagerAdapter = new VpAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RvList(), getTime());//添加Fragment
        viewPagerAdapter.addFragment(new HotList(),"热门文章");
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
                    case R.id.navigation_item1:
                        break;
                    case R.id.navigation_item2:
                        Intent intent = new Intent(MainActivity.this,FavouriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_item3:
                        isNight = preferences.getBoolean("night", false);
                        if (isNight) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            preferences.edit().putBoolean("night", false).apply();
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            preferences.edit().putBoolean("night", true).apply();
                        }
                        recreate();
                        break;
                    case R.id.navigation_sub_item3:

                        break;
                }
                return true;
            }
        });
    }
    public String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
        return format.format(c.getTime());
    }

}