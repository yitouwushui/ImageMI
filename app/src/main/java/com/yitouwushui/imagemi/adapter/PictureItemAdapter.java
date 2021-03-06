package com.yitouwushui.imagemi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yitouwushui.imagemi.R;
import com.yitouwushui.imagemi.application.MyApplication;
import com.yitouwushui.imagemi.bean.ImageBean;


import java.util.ArrayList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by ding on 2017/4/18.
 */

public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemAdapter.ViewHolder> {

    private ArrayList<ImageBean> mImageBeanList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    /**
     * 当前适配器在父适配器的位置
     */
    private int adapterPosition;
    private PictureItemOnClick pictureItemOnClick;
    /**
     * imageSpace0 image size;
     * imageSpace1 每行显示图片的数量
     * imageSpace2 图片的边距
     */
    private int[] imageSpace = new int[3];

    public PictureItemAdapter(List<ImageBean> mImageBeanList, int adapterPosition, Context mContext,
                              int[] imageSpace, PictureItemOnClick pictureItemOnClick) {
        this.mImageBeanList.addAll(mImageBeanList);
        this.mContext = mContext;
        this.adapterPosition = adapterPosition;
        this.imageSpace = imageSpace;
        this.pictureItemOnClick = pictureItemOnClick;
        inflater = LayoutInflater.from(mContext);
    }

    public void setImageBean(List<ImageBean> imageBeanList) {
        mImageBeanList.clear();
        mImageBeanList.addAll(imageBeanList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_picture_recycler_2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imgPicture.getLayoutParams();
        holder.imgPicture.setLayoutParams(setLayoutParams(layoutParams, position, imageSpace[1], mImageBeanList.size()));
        holder.position = position;
        ImageBean imageBean = mImageBeanList.get(position);
        if (MyApplication.isSelectionMode) {
            holder.imgChecked.setVisibility(View.VISIBLE);
            if (pictureItemOnClick.isSelectionItem(adapterPosition, position)) {
                holder.imgChecked.setImageResource(R.drawable.selected);
            } else {
                holder.imgChecked.setImageResource(R.drawable.select_no);
            }
        } else {
            holder.imgChecked.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(imageBean.getImagePath())
                .fitCenter()
                .crossFade()
                .into(holder.imgPicture);
    }

    /**
     * 动态设置边距
     *
     * @param layoutParams
     * @param pos          位置
     * @param spanCount    行总数
     * @param childCount   所有的子项
     * @return
     */
    private RelativeLayout.LayoutParams setLayoutParams(
            RelativeLayout.LayoutParams layoutParams,
            int pos, int spanCount, int childCount) {
        layoutParams.height = imageSpace[0];
        layoutParams.width = imageSpace[0];
        int rightMargin = imageSpace[2];
        int bottomMargin = imageSpace[2];
        if (isLastRaw(pos, spanCount, childCount)) {
            // 是最后一行
            bottomMargin = 0;
        }
        if (isLastColumn(pos, spanCount)) {
            // 是最后一列
            rightMargin = 0;
        }
        layoutParams.setMargins(0, 0, rightMargin, bottomMargin);
        return layoutParams;
    }

    private boolean isLastColumn(int pos, int spanCount) {
        if ((pos + 1) % spanCount == 0) {
            // 如果是最后一列，则不需要绘制右边
            return true;
        }
        return false;
    }

    private boolean isLastRaw(int pos, int spanCount, int childCount) {
        if (pos >= ((childCount - 1) / spanCount) * spanCount) {
            // 如果是最后一行，则不需要绘制底部
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mImageBeanList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_picture)
        ImageView imgPicture;
        @BindView(R.id.img_selected)
        ImageView imgChecked;

        private int position;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick((R.id.item_recycler_item))
        public void onClick(View v) {
            if (pictureItemOnClick != null) {
                if (MyApplication.isSelectionMode) {
                    // 选择模式
                    ImageBean imageBean = mImageBeanList.get(position);
                    pictureItemOnClick.onItemClick(v, imageBean, adapterPosition, position);
                    PictureItemAdapter.this.notifyItemChanged(position);
                } else {
                    // 正常模式
                }
            }
        }

        @OnLongClick(R.id.item_recycler_item)
        public boolean onLongClick(View v) {
            if (pictureItemOnClick != null) {
                ImageBean imageBean = mImageBeanList.get(position);
                pictureItemOnClick.onLongClick(v, imageBean, adapterPosition, position);
                return true;
            }
            return false;
        }

    }

    interface PictureItemOnClick {
        /**
         * 子RecycleView item
         *
         * @param view            当前item
         * @param imageBean       图片对象
         * @param adapterPosition 适配器位置
         * @param position        item 位置
         */
        void onItemClick(View view, ImageBean imageBean, int adapterPosition, int position);

        /**
         * 子RecycleView item
         *
         * @param view            当前item
         * @param imageBean       图片对象
         * @param adapterPosition 适配器位置
         * @param position        item 位置
         */
        void onLongClick(View view, ImageBean imageBean, int adapterPosition, int position);

        /**
         * 是否被选中
         *
         * @param adapterPosition
         * @param itemPosition
         * @return
         */
        boolean isSelectionItem(int adapterPosition, int itemPosition);
    }
}
