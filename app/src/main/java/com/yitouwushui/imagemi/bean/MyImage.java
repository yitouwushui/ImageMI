package com.yitouwushui.imagemi.bean;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2017/4/14.
 */
public class MyImage {
    public int id;
    public String content;
    public List<Integer> images = new ArrayList<>();

    public MyImage(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public MyImage(int id, String content, List<Integer> images) {
        this.id = id;
        this.content = content;
        this.images.addAll(images);
    }

    public MyImage() {
    }
}
