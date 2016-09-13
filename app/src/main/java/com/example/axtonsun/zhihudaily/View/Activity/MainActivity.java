package com.example.axtonsun.zhihudaily.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axtonsun.zhihudaily.Bean.DayNight;
import com.example.axtonsun.zhihudaily.Helper.DayNightHelper;
import com.example.axtonsun.zhihudaily.Utility.Utility;
import com.example.axtonsun.zhihudaily.View.Adapter.VpAdapter;
import com.example.axtonsun.zhihudaily.View.Fragment.HotList;
import com.example.axtonsun.zhihudaily.View.Fragment.RvList;
import com.example.axtonsun.zhihudaily.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private boolean isNight;
    //private SharedPreferences preferences;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public ViewPager viewPager;
    private DayNightHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        helper = new DayNightHelper(this);
        if (helper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
        setContentView(R.layout.activity_main);
        initNaviView();
    }
    private void initNaviView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText(Utility.getTime()));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("热门文章"));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        VpAdapter viewPagerAdapter = new VpAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RvList(),Utility.getTime());//添加Fragment
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
                        if (helper.isDay()){
                            helper.setMode(DayNight.NIGHT);
                            setTheme(R.style.NightTheme);
                        }else {
                            helper.setMode(DayNight.DAY);
                            setTheme(R.style.DayTheme);
                        }
                        recreate();
                        break;
                    case R.id.navigation_sub_item3:
                        Toast.makeText(MainActivity.this, "作者:AxtonSun", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}