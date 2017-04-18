package com.yitouwushui.imagemi.Uitls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

/**
 * Created by ding on 2017/4/18.
 */

public class ImageUtils {

    /**
     * 按正方形裁切图片
     */
    public static Bitmap ImageCrop(Bitmap bitmap, int imageWidth, int imageHeight) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
//
        int wh = imageWidth > imageHeight ? imageHeight : imageWidth;// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null, false);
    }

    /**
     * 压缩图片
     *
     * @param newWid
     * @param newHei
     * @param imagePath
     * @return
     */
    public static Bitmap getNewSampleBitmap(int newWid, int newHei, String imagePath) {
        File file = new File(imagePath);
        Log.i("123", "exists:" + file.exists());
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            options.inSampleSize = calculateScaleInSampleSize(options, newWid, newHei);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);
        }
        return null;
    }

    /**
     * 官方推荐的计算sample的公式
     */
    private static int calculateScaleInSampleSize(BitmapFactory.Options options, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        int height;
        int width;
        if (options.outWidth >= options.outHeight) {
            // 实际图片宽大于高
            height = options.outHeight;
            width = options.outWidth;
        } else {
            // 实际图片高大于宽
            width = options.outHeight;
            height = options.outWidth;
        }
        if (height > targetHeight || width > targetWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= targetHeight
                    && (halfWidth / inSampleSize) >= targetWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
