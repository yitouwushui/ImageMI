package com.yitouwushui.imagemi.mvp.picture;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.yitouwushui.imagemi.uitls.LogUtils;
import com.yitouwushui.imagemi.bean.ImageBean;
import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.uitls.TimeUtils;

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
                                        MediaStore.Images.Media.DATE_TAKEN},
                                MediaStore.Images.Media.MIME_TYPE + "=? or "
                                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                                new String[]{"image/jpeg", "image/png"},
                                MediaStore.Images.Media.DATE_TAKEN + " DESC"); // 降序排列
                ArrayList<MyImage> myImageList = new ArrayList<MyImage>();
                ArrayList<ImageBean> imageBeenList = new ArrayList<ImageBean>();
                long time = 0;
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        long date = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
                        ImageBean imageBean = new ImageBean();
                        imageBean.setId(id);
                        imageBean.setImagePath(path);
                        imageBean.setDateL(date);

                        if (time == 0) {
                            time = TimeUtils.getDayTimeByTime(date);
                        } else {
                            if (date >= time) {
                                imageBeenList.add(imageBean);
                                LogUtils.i("ID=" + id + "  path:" + path + "  dare:" + TimeUtils.getStringByDate(date) + "  time" + TimeUtils.getStringByDate(time));
                            } else {
                                if (imageBeenList.size() != 0) {
                                    MyImage myImage = new MyImage();
                                    myImage.setId(time);
                                    myImage.setData(TimeUtils.getDayString(time));
                                    myImage.setImageBeanList(imageBeenList);
                                    myImageList.add(myImage);
                                    imageBeenList = new ArrayList<>();
                                    LogUtils.i("日期:" + myImage.getData() + "  size:" + myImage.getImageBeanList().size() + "  dare:" + date);
                                }
                                time = TimeUtils.getDayTimeByTime(date);
                            }
                            continue;
                        }
                    }
                    // 结果后最后一个相册集
                    MyImage myImage = new MyImage();
                    myImage.setData(TimeUtils.getDayString(time));
                    myImage.setImageBeanList(imageBeenList);
                    myImageList.add(myImage);
                    // 回调界面
                    pictureModelCallBack.querySuccess(myImageList);
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
