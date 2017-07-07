package com.yitouwushui.imagemi.mvp.picture;

import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.mvp.base.BasePresenter;
import com.yitouwushui.imagemi.mvp.base.BaseView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yitouwushui on 2017/4/22.
 */
public interface PictureContract {
    interface IView extends BaseView<Presenter> {
        /**
         * 添加图片
         *
         * @param myImageList
         */
        void addPicture(List<MyImage> myImageList);

        /**
         * 删除图片
         *
         * @param myImage
         */
        void delPicture(MyImage myImage);

        /**
         * 查询图片
         *
         * @param myImage
         */
        void queryPicture(MyImage myImage);

        /**
         * 查询图片
         *
         * @param myImageList
         */
        void queryPicture(List<MyImage> myImageList);

        /**
         * 发生错误
         */
        void error();
    }

    interface Presenter extends BasePresenter {
        /**
         * 添加图片
         *
         * @param
         */
        void addPicture();

        /**
         * 删除图片List
         *
         * @param myImageList
         */
        void delPicture(List<MyImage> myImageList);

        /**
         * 删除图片
         *
         * @param imageId
         */
        void delPicture(int[] imageId);

        /**
         * 查询图片
         */
        void queryPicture();

        /**
         * 查询坐标
         * @param myImages
         */
        void queryLocation(ArrayList<MyImage> myImages);

        /**
         * 查询某一天的图片
         *
         * @param date
         */
        void queryPicture(Date date);

    }
}
