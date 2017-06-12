package com.example.axtonsun.zhihudaily.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/19.
 */
public class VpAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments;//添加的Fragment的集合
    private final List<String> mFragmentsTitles;//每个Fragment对应的title的集合


    public VpAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mFragmentsTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mFragmentsTitles = mFragmentsTitles;
    }

    @Override
    public Fragment getItem(int position) {
        //得到对应position的Fragment
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        //返回Fragment的数量
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //得到对应position的Fragment的title
        return mFragmentsTitles.get(position);
    }
}
