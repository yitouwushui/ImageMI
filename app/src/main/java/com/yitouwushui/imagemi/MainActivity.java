package com.yitouwushui.imagemi;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yitouwushui.imagemi.adapter.TabAdapter;
import com.yitouwushui.imagemi.application.MyApplication;
import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.mvp.picture.PictureFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PictureFragment.OnActionFragmentListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    View head;

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
        PictureFragment pictureFragment = (PictureFragment) tabAdapter.getItem(0);
        pictureFragment.setOnActionFragmentListener(MainActivity.this);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (MyApplication.isSelectionMode) {
            MyApplication.isSelectionMode = false;
            if (head.getParent() != null) {
                getWindowManager().removeView(head);
            }
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
    protected void onPause() {
        WindowManager windowManager = getWindowManager();
        if (head.getParent() != null) {
            windowManager.removeView(head);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLongClick(View view) {
        head = MainActivity.this.getLayoutInflater().inflate(R.layout.picture_header, null);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT);
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        WindowManager windowManager = MainActivity.this.getWindowManager();
        windowManager.addView(head, layoutParams);
    }


}
