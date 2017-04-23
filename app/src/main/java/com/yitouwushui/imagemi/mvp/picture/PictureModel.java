package com.yitouwushui.imagemi.mvp.picture;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.yitouwushui.imagemi.Uitls.LogUtils;
import com.yitouwushui.imagemi.bean.ImageBean;
import com.yitouwushui.imagemi.bean.MyImage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yitouwushui on 2017/4/23.
 */

public class PictureModel implements PictureContract.IModel {

    private PictureModelCallBack pictureModelCallBack;
    ContentResolver contentProvider;

    /**
     * @param context
     * @param pictureModelCallBack
     */
    public PictureModel(Context context, PictureModelCallBack pictureModelCallBack) {
        this.contentProvider = context.getApplicationContext().getContentResolver();
        this.pictureModelCallBack = pictureModelCallBack;
    }

    public PictureModel(PictureModelCallBack pictureModelCallBack) {

    }

    @Override
    public void add() {

    }

    @Override
    public void del(List<MyImage> myImageList) {
    }

    @Override
    public void del(int[] imageId) {

    }

    @Override
    public void query() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    @Override
    public void query(Date date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = contentProvider
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                new String[]{MediaStore.Images.Media._ID,
                                        MediaStore.Images.Media.DATA,
                                        MediaStore.Images.Media.DATE_ADDED},
                                MediaStore.Images.Media.MIME_TYPE + "=? or "
                                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                                new String[]{"image/jpeg","image/png"},
                                MediaStore.Images.Media.DATE_ADDED + " DESC"); // 降序排列
                ArrayList<ImageBean> arrayList = new ArrayList<ImageBean>();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        String date = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                        ImageBean imageBean = new ImageBean();
                        imageBean.setId(id);
                        imageBean.setImagePath(path);
                        imageBean.setDate(date);

                        LogUtils.i("MediaStore.Images.Media_ID=" + id + "  path:" + path + "  dare:" + date);
                    }
                }
            }
        }).start();
    }

    interface PictureModelCallBack {
        /**
         * 查询成功
         *
         * @param myImageList
         */
        public void querySuccess(List<MyImage> myImageList);

        /**
         * 查询一天的图片
         *
         * @param myImage
         */
        public void querySuccess(MyImage myImage);

        /**
         * 查询一张图片
         *
         * @param bitmap
         */
        public void querySuccess(Bitmap bitmap);

        /**
         * 发生错误
         */
        public abstract void error();
    }
}
