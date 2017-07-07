package com.yitouwushui.imagemi.application;

import android.app.Application;

/**
 * Created by ding on 2017/4/25.
 */

public class MyApplication extends Application {

    /**
     * 是否处于选择模式中
     */
    public static volatile boolean isSelectionMode = false;

    @Override
    public void onCreate() {
        super.onCreate();



    }
}
