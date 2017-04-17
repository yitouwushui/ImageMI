package com.yitouwushui.imagemi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yitouwushui.imagemi.AlbumFragment;
import com.yitouwushui.imagemi.PictureFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2017/4/17.
 */

public class TabAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    String[] titles = {"照片", "相册"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new PictureFragment());
        fragments.add(new AlbumFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
