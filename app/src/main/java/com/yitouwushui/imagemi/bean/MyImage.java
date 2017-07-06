package com.yitouwushui.imagemi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ding on 2017/4/14.
 * 一天的图片
 */
public class MyImage {
    /**
     * 日期long
     */
    public Long id;
    /**
     * 标题
     */
    public String content;
    /**
     * 格式化的日期
     */
    public String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
