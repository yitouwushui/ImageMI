package com.yitouwushui.imagemi.mvp.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.yitouwushui.imagemi.bean.MyImage;

import java.util.Date;
import java.util.List;

/**
 * Created by yitouwushui on 2017/4/23.
 */
public class PicturePresenter implements PictureContract {

    @NonNull
    private IView mIView;
    @NonNull
    private IModel mIModel;

    public PicturePresenter(@NonNull final IView mIView, Context context) {
        this.mIView = mIView;
        this.mIModel = new PictureModel(context, new PictureModel.PictureModelCallBack() {
            @Override
            public void querySuccess(List<MyImage> myImageList) {
                PicturePresenter.this.mIView.queryPicture(myImageList);
            }

            @Override
            public void querySuccess(MyImage myImage) {
                PicturePresenter.this.mIView.queryPicture(myImage);
            }

            @Override
            public void querySuccess(Bitmap bitmap) {

            }

            @Override
            public void error() {
                PicturePresenter.this.mIView.error();
            }
        });

    }

    public void deletePicture() {

    }

    /**
     * 查询所有图片
     */
    public void queryPicture() {
        mIModel.query();
    }

    /**
     * 查询图片某一天的图片
     */
    public void queryPicture(Date date) {
        mIModel.query(date);
    }
}
