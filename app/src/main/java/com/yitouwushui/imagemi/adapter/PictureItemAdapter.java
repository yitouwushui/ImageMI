package com.yitouwushui.imagemi.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private int imageSize;

    public PictureItemAdapter(List<Integer> mData, Context mContext, int imageSize) {
        this.mData.addAll(mData);
        this.mContext = mContext;
        this.imageSize = imageSize;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_picture_recycler_2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.img.getLayoutParams();
        layoutParams.height = imageSize;
        layoutParams.width = imageSize;
        holder.img.setLayoutParams(layoutParams);
//        LogUtils.d("imageSize :" + imageSize);
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
