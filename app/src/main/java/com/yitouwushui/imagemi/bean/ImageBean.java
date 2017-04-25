package com.yitouwushui.imagemi.bean;

/**
 * Created by yitouwushui on 2017/4/23.
 */

public class ImageBean {

    /**
     * 图片id
     */
    private long id;
    /**
     * 图片名字
     */
    private String imageName;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 图片原图路径
     */
    private String Thumbnail;
    /**
     * 图片缩略图
     */
    private String miniKind;
    /**
     * 图片添加日期
     */
    private String date;
    /**
     * 长整形时间
     */
    private Long dateL;
    /**
     * 图片参数
     */
    private String param;
    /**
     * 图片拍摄地址
     */
    private String location;
    /**
     * 是否被选中
     */
    private boolean isChecked;

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getMiniKind() {
        return miniKind;
    }

    public void setMiniKind(String miniKind) {
        this.miniKind = miniKind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getDateL() {
        return dateL;
    }

    public void setDateL(Long dateL) {
        this.dateL = dateL;
    }
}
