package com.yitouwushui.imagemi.bean;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2017/4/14.
 */
public class MyImage {
    public Long id;
    public String content;
    public String data;
    public List<Integer> images = new ArrayList<>();
    public List<ImageBean> imageBeanList = new ArrayList<>();

    public MyImage(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public MyImage(Long id, String content, List<Integer> images) {
        this.id = id;
        this.content = content;
        this.images.addAll(images);
    }

    public MyImage() {
    }

    public MyImage(Long id, String content, List<Integer> images, List<ImageBean> imageBeanList) {
        this.id = id;
        this.content = content;
        this.images = images;
        this.imageBeanList = imageBeanList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public List<ImageBean> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<ImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }
}
