package com.yitouwushui.imagemi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yitouwushui.imagemi.PictureFragment.OnListFragmentInteractionListener;
import com.yitouwushui.imagemi.R;
import com.yitouwushui.imagemi.bean.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Image} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Image> mValues;
    private Context mContext;
    private static final int TEXT_VIEW_TYPE = 1;
    private static final int IMAGE_VIEW_TYPE = 2;
    private LayoutInflater inflater;


    public MyItemRecyclerViewAdapter(Context context, List<Image> items) {
        mValues = new ArrayList<>();
        mValues.addAll(items);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        return getViewHolderByViewType(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderText) {
            Image image = mValues.get(position);
            ((ViewHolderText) holder).mIdView.setText(String.valueOf(image.id));
            ((ViewHolderText) holder).mContentView.setText(image.content);
        } else {
            ((ViewHolderImage) holder).mImageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        }
    }

    private RecyclerView.ViewHolder getViewHolderByViewType(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TEXT_VIEW_TYPE:
                View tv_ItemView = inflater.inflate(R.layout.fragment_item_text, null);
                holder = new ViewHolderText(tv_ItemView);
                break;
            case IMAGE_VIEW_TYPE:
                View iv_ItemView = inflater.inflate(R.layout.fragment_item_image2, null);
                holder = new ViewHolderImage(iv_ItemView);
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        int index = position % 2;
        switch (index) {
            case 0:
                viewType = TEXT_VIEW_TYPE;
                break;
            case 1:
                viewType = IMAGE_VIEW_TYPE;
                break;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolderImage extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolderImage(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }

    class ViewHolderText extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Image mItem;

        public ViewHolderText(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
