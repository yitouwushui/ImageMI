package com.yitouwushui.imagemi.mvp.picture;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.yitouwushui.imagemi.bean.ImageBean;
import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.uitls.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yitouwushui on 2017/4/23.
 */
public class PicturePresenter implements PictureContract.Presenter {


    ContentResolver contentProvider;

    @NonNull
    private PictureContract.IView mIView;

    public PicturePresenter(@NonNull final PictureContract.IView mIView, Context context) {
        this.mIView = mIView;
        this.contentProvider = context.getApplicationContext().getContentResolver();
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
                ArrayList<MyImage> myImageList = new ArrayList<>();
                ArrayList<ImageBean> imageBeenList = new ArrayList<>();
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
                        }
                        if (date >= time) {
                            imageBeenList.add(imageBean);
                        } else {
                            MyImage myImage = new MyImage();
                            myImage.setId(time);
                            myImage.setData(TimeUtils.getDayString(time));
                            myImage.setImageBeanList(imageBeenList);
                            myImageList.add(myImage);
                            imageBeenList = new ArrayList<>();
                            imageBeenList.add(imageBean);
                            time = TimeUtils.getDayTimeByTime(date);
                        }
                    }
                    // 结果后最后一个相册集
                    MyImage myImage = new MyImage();
                    myImage.setData(TimeUtils.getDayString(time));
                    myImage.setImageBeanList(imageBeenList);
                    myImageList.add(myImage);
                    // 回调界面
                    PicturePresenter.this.mIView.queryPicture(myImageList);
                }
            }
        }).start();
    }

    @Override
    public void start() {

    }
}
