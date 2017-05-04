package com.yitouwushui.imagemi;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yitouwushui.imagemi.adapter.TabAdapter;
import com.yitouwushui.imagemi.application.MyApplication;
import com.yitouwushui.imagemi.mvp.picture.PictureFragment;
import com.yitouwushui.imagemi.uitls.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PictureFragment.OnActionFragmentListener {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    View header;
    WindowManager wm;
    WindowManager.LayoutParams lp;

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
            if (header.getParent() != null) {
                wm.removeView(header);
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
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (header.getParent() != null) {
            wm.removeView(header);
        }
        super.onDestroy();
    }

    @Override
    public void onLongClick(View v) {
        if (header == null) {
            header = MainActivity.this.getLayoutInflater().inflate(R.layout.picture_header, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) getResources().getDimension(R.dimen.main_tab_height));
            int statusHeight = ScreenUtils.getStatusHeight(this);
//            header.setPadding(0, statusHeight, 0, 0);
            layoutParams.height += statusHeight;
            header.setLayoutParams(layoutParams);

//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                0, 0, PixelFormat.TRANSPARENT);
//        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        layoutParams.x = 0;
//        layoutParams.y = 0;
//        WindowManager windowManager = MainActivity.this.getWindowManager();
//        windowManager.addView(this.header, layoutParams);

            lp = new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION,
                    // 设置为无焦点状态
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, // 没有边界
                    // 半透明效果
                    PixelFormat.TRANSLUCENT);
            lp.gravity = Gravity.TOP;
//            lp.verticalMargin = statusHeight;
            // 重要 这就是添加动画的地方
            lp.windowAnimations = R.style.anim_view;
            wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        }
        wm.addView(header, lp);

    }

}

