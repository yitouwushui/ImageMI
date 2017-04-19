package com.yitouwushui.imagemi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yitouwushui.imagemi.adapter.PictureAdapter;
import com.yitouwushui.imagemi.bean.MyImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PictureFragment extends Fragment {

    private static final String TAG = "PictureFragment";
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.topContainer)
    FrameLayout topContainer;

    private Context mContext;
    private List<MyImage> mMyImageList;
    private View mView;
    private PictureAdapter adapter;
    private int mTopHeight;
    private TopView mTopView;

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
            init(inflater, container);
        }
        return mView;
    }

    private void init(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_picture_list, container, false);
        View topView = inflater.inflate(R.layout.fragment_item_text, container, false);
        ButterKnife.bind(this, mView);
        mTopView = new TopView(topView);



        list = (RecyclerView) mView.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutCompat.VERTICAL, false));
        adapter = new PictureAdapter(mMyImageList, mContext);
        list.setAdapter(adapter);
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                FrameLayout currentUpView = (FrameLayout) recyclerView.findChildViewUnder(0, 0);
                FrameLayout currentLowView = (FrameLayout) recyclerView.findChildViewUnder(0, mTopHeight);
            }
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(MyImage item);
    }

    class TopView {
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.line)
        TextView line;
        @Bind(R.id.tv_location)
        TextView tvLocation;

        TopView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
