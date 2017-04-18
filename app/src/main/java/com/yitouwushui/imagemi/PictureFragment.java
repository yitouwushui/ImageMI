package com.yitouwushui.imagemi;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yitouwushui.imagemi.Uitls.LogUtils;
import com.yitouwushui.imagemi.Uitls.ScreenUtils;
import com.yitouwushui.imagemi.adapter.PictureAdapter;
import com.yitouwushui.imagemi.bean.MyImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PictureFragment extends Fragment {

    private static final String TAG = "PictureFragment";

    private Context mContext;
    private List<MyImage> mMyImageList;
    private View mView;
    private PictureAdapter adapter;

    public PictureFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mMyImageList == null) {
            mMyImageList = new ArrayList<>();
            Random random = new Random();
            for (int i = 1; i < 50; i++) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 1, k = random.nextInt(8) + 2; j < k; j++) {
                    list.add(j);
                }
                mMyImageList.add(new MyImage(i, "2333333", list));
            }
        }

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_picture_list, container, false);
            RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(
                    mContext, LinearLayoutCompat.VERTICAL, false));
            adapter = new PictureAdapter(mMyImageList, mContext);
            recyclerView.setAdapter(adapter);
        }
        return mView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
        void onListFragmentInteraction(MyImage item);
    }
}
