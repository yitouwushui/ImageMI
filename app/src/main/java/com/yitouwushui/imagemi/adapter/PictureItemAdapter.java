package com.yitouwushui.imagemi.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yitouwushui.imagemi.R;
import com.yitouwushui.imagemi.Uitls.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ding on 2017/4/18.
 */

public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemAdapter.ViewHolder> {

    private ArrayList<Integer> mData = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    /**
     * imageSpace0 image size;
     * imageSpace1 每行显示图片的数量
     * imageSpace2 图片的边距
     */
    private int[] imageSpace = new int[3];

    public PictureItemAdapter(List<Integer> mData, Context mContext, int[] imageSpace) {
        this.mData.addAll(mData);
        this.mContext = mContext;
        this.imageSpace = imageSpace;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_picture_recycler_2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.img.getLayoutParams();

        holder.img.setLayoutParams(setLayoutParams(layoutParams, position, imageSpace[1], mData.size()));
        int id;
        if (position % 2 == 0) {
            id = R.drawable.black;
        } else {
            id = R.drawable.black;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.img.setImageDrawable(mContext.getDrawable(id));
        } else {
            holder.img.setImageDrawable(mContext.getResources().getDrawable(id));
        }
    }

    /**
     * 动态设置边距
     *
     * @param layoutParams
     * @param pos 位置
     * @param spanCount 行总数
     * @param childCount 所有的子项
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
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView img;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
