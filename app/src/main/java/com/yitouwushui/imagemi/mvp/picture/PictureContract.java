package com.yitouwushui.imagemi.mvp.picture;

import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.mvp.base.BasePresenter;
import com.yitouwushui.imagemi.mvp.base.BaseView;

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
        void add();

        /**
         * 删除图片List
         *
         * @param myImageList
         */
        void del(List<MyImage> myImageList);

        /**
         * 删除图片
         *
         * @param imageId
         */
        void del(int[] imageId);

        /**
         * 查询图片
         */
        void query();

        /**
         * 查询某一天的图片
         *
         * @param date
         */
        void query(Date date);
    }

    interface IModel {
        /**
         * 添加图片
         *
         * @param
         */
        void add();

        /**
         * 删除图片List
         *
         * @param myImageList
         */
        void del(List<MyImage> myImageList);

        /**
         * 删除图片
         *
         * @param imageId
         */
        void del(int[] imageId);

        /**
         * 查询图片
         */
        void query();

        /**
         * 查询某一天的图片
         *
         * @param date
         */
        void query(Date date);

    }
}
