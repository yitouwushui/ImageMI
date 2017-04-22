package com.yitouwushui.imagemi.mvp.base;

/**
 * Created by yitouwushui on 2017/4/22.
 */
public interface MvpView {

    void showNetworkFail();

    void showNetworkFail(String err);

    void showToast(String toast);
}
