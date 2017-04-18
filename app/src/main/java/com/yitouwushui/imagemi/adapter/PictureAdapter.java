package com.yitouwushui.imagemi.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yitouwushui.imagemi.R;
import com.yitouwushui.imagemi.Uitls.ScreenUtils;
import com.yitouwushui.imagemi.bean.MyImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ding on 2017/4/18.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<MyImage> mValues = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    /**
     * imageSpace0 image size;
     * imageSpace1 每行显示图片的数量
     */
    private int[] imageSpace = new int[2];

    public PictureAdapter(List<MyImage> mValues, Context mContext) {
        this.mValues.addAll(mValues);
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        measureImageSpace();
    }

    /**
     *
     */
    public void measureImageSpace() {
        int w = ScreenUtils.getScreenWidth(mContext);
        int h = ScreenUtils.getScreenHeight(mContext);
        if (w > h) {
            imageSpace[0] = h / 4;
            imageSpace[1] = w * 4 / h;
        } else {
            imageSpace[0] = w / 4;
            imageSpace[1] = 4;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_picture_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyImage myImage = mValues.get(position);
        if (TextUtils.isEmpty(myImage.content)) {
            holder.line.setVisibility(View.GONE);
            holder.tvLocation.setVisibility(View.GONE);
        } else {
            holder.tvLocation.setText(myImage.content);
        }
        holder.tvDate.setText("4月" + myImage.id + "日");
        holder.itemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, imageSpace[1], GridLayoutManager.VERTICAL, false));
        holder.itemRecyclerView.setAdapter(new PictureItemAdapter(myImage.images, mContext, imageSpace[0]));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_recycler_view)
        RecyclerView itemRecyclerView;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.line)
        TextView line;
        @Bind(R.id.tv_location)
        TextView tvLocation;
        @Bind(R.id.tv_button)
        TextView tvButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
