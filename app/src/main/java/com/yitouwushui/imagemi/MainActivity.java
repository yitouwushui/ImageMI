package com.yitouwushui.imagemi;

import android.Manifest;
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

import com.tbruyelle.rxpermissions.RxPermissions;
import com.yitouwushui.imagemi.adapter.TabAdapter;
import com.yitouwushui.imagemi.application.MyApplication;
import com.yitouwushui.imagemi.mvp.picture.PictureFragment;
import com.yitouwushui.imagemi.uitls.LogUtils;
import com.yitouwushui.imagemi.uitls.PermissionsUtils;
import com.yitouwushui.imagemi.uitls.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author ding
 */
public class MainActivity extends AppCompatActivity implements PictureFragment.OnActionFragmentListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    View header;
    WindowManager wm;
    WindowManager.LayoutParams lp;

    TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.StatusBarLightMode(this);
        setContentView(R.layout.activity_main);
        init();


//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(
//                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
//                .cookieJar(new CookieManger(context))
//                .addInterceptor(loginInterceptor)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .build();
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .build();
    }

    private void init() {
        ButterKnife.bind(this);
        PermissionsUtils.initClient(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            LogUtils.i("permissions","Manifest.permission.READ_EXTERNAL_STORAGE + 获取成功");
                        } else {
                            LogUtils.i("permissions","Manifest.permission.READ_EXTERNAL_STORAGE + 获取失败");
                        }
                    }
                });
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
        if (header!= null && header.getParent() != null) {
            wm.removeView(header);
        }
        super.onDestroy();
    }

    @Override
    public void onLongClick(View v) {
        if (header == null) {
            header = MainActivity.this.getLayoutInflater().inflate(R.layout.picture_header, null);
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
            // 重要 这就是添加动画的地方
            lp.windowAnimations = R.style.anim_view;
            wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        }
        if (header.getParent() == null) {
            wm.addView(header, lp);
        }
    }

}

