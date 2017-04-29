package com.yitouwushui.imagemi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.yitouwushui.imagemi.adapter.TabAdapter;
import com.yitouwushui.imagemi.application.MyApplication;
import com.yitouwushui.imagemi.mvp.picture.PictureFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
        ButterKnife.bind(this);
//        tabLayout.setSelectedTabIndicatorColor(color);
//        tabLayout.setSelectedTabIndicatorHeight(8);
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorTitleBackground));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorTitleFont), getResources().getColor(R.color.colorTitleFontSelect));
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (MyApplication.isSelectionMode) {
            MyApplication.isSelectionMode = false;
            PictureFragment pictureFragment = (PictureFragment) tabAdapter.getItem(0);
            pictureFragment.exitSelectionMode();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tabLayout, R.id.viewPager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabLayout:
                break;
            case R.id.viewPager:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
