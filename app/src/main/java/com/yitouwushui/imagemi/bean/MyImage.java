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
    /**
     * Latitude and longitude
     * 先纬度和后经度，逗号分隔
     * 例：20.000,30.112
     */
    public List<String> location = new ArrayList<>();
    /**
     * 图片基类list
     */
    public List<ImageBean> imageBeanList = new ArrayList<>();

    public MyImage(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public MyImage() {
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

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public List<ImageBean> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<ImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }
}
