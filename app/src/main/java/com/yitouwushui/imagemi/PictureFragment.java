package com.yitouwushui.imagemi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yitouwushui.imagemi.adapter.MyItemRecyclerViewAdapter;
import com.yitouwushui.imagemi.bean.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PictureFragment extends Fragment {

    private Context mContext;
    private List<Image> mImageList;
    private View mView;

    public PictureFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mImageList == null) {
            mImageList = new ArrayList<>();
            mImageList.add(new Image(1, "2"));
            mImageList.add(new Image(2, "3"));
            mImageList.add(new Image(3, "4"));
            mImageList.add(new Image(4, "5555"));
            mImageList.add(new Image(6, "666"));
        }

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_picture_list, container, false);
            // Set the adapter
            if (mView instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) mView;
//                recyclerView.setLayoutManager(new LinearLayoutManager(
//                        mContext, LinearLayoutCompat.VERTICAL, true));
                recyclerView.setLayoutManager(
                        new StaggeredGridLayoutManager(
                                4,
                                StaggeredGridLayoutManager.VERTICAL
                        ));
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(mContext, mImageList));
            }
        }
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public void onDetach() {
//        mListener = null;
        mContext = null;
        super.onDetach();
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Image item);
    }
}
