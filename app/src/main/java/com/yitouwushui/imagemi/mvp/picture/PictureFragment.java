package com.yitouwushui.imagemi.mvp.picture;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yitouwushui.imagemi.R;
import com.yitouwushui.imagemi.adapter.PictureAdapter;
import com.yitouwushui.imagemi.bean.MyImage;
import com.yitouwushui.imagemi.uitls.UIUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class PictureFragment extends Fragment implements PictureContract.IView {

    private static final String TAG = "PictureFragment";
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.topContainer)
    FrameLayout topContainer;

    private Context mContext;
    private List<MyImage> mMyImageList = new ArrayList<>();
    private View mView;
    private PictureAdapter adapter;
    private OnActionFragmentListener onActionFragmentListener;

//    private TopView mTopView;
//    private IView topView;

    public PictureFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mView == null || mMyImageList.size() == 0) {
            init(inflater, container);
        }

        return mView;

    }


    private void init(LayoutInflater inflater, final ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_picture_list, container, false);
        ButterKnife.bind(this, mView);
//        topView = inflater.inflate(R.layout.fragment_item_text, container, false);
//        mTopView = new TopView(topView);

        list = (RecyclerView) mView.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(
                mContext, LinearLayoutCompat.VERTICAL, false));
        adapter = new PictureAdapter(mMyImageList, mContext);
        PicturePresenter picturePresenter = new PicturePresenter(this, mContext);
        picturePresenter.query();
        adapter.setPictureFragmentItem(pictureFragmentItem);
        list.setAdapter(adapter);
//
//        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            private int mTopHeight = (int) mContext.getResources().getDimension(R.dimen.item_text_height);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mTopHeight);
//            FrameLayout preView = null;
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                FrameLayout currentUpView = (FrameLayout) recyclerView.findChildViewUnder(0, 0);
//                FrameLayout currentLowView = (FrameLayout) recyclerView.findChildViewUnder(0, mTopHeight);
//                //  初始化preView
//                if (dy == 0) {
//                    preView = currentUpView;
//                }
//                // 当下移临界点时，标题栏从原view中移到topContainer中
//                if (currentLowView != preView || dy > 0) {
//                    View titleView = topContainer.getChildAt(0);
//                    if (titleView != null) {
//                        topContainer.removeView(titleView);
//                        params.gravity = Gravity.BOTTOM;
//                        titleView.setLayoutParams(params);
//                        preView.addView(titleView);
//                        //    将preView替换成现有的currentLowView
//                        preView = currentLowView;
//                    }
//                }
//                // 当上移到临界点时
//                if (currentUpView != preView || dy < 0) {
//                    // 上标题栏置顶
//                    View upTv = currentUpView.findViewById(R.id.item_recycler_title);
//                    if (null != upTv) {
//                        params.gravity = Gravity.BOTTOM;
//                        upTv.setLayoutParams(params);
//                    }
//                    // 下标题栏置顶
//                    View lowTv = topContainer.getChildAt(0);
//                    if (null != lowTv) {
//                        topContainer.removeView(lowTv);
//                        params.gravity = Gravity.TOP;
//                        lowTv.setLayoutParams(params);
//                        preView.addView(lowTv);
//                    }
//                    //  将preView替换成现有的currentUpView
//                    preView = currentUpView;
//                }
//
//                //   只有一种情况会让标题栏上浮，就是两个锚点下的view相同的时候
//                if (currentUpView.equals(currentLowView)) {
//                    View tv = currentLowView.findViewById(R.id.item_recycler_title);
//                    if (null != tv) {
//                        currentUpView.removeView(tv);
//                        params.gravity = Gravity.TOP;
//                        tv.setLayoutParams(params);
//                        topContainer.addView(tv);
//                    }
//                }
//            }
//        });

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int mTopHeight = (int) mContext.getResources().getDimension(R.dimen.item_text_height);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mTopHeight);
            FrameLayout preView = null;
            FrameLayout nextView = null;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                FrameLayout currentUpView = (FrameLayout) recyclerView.findChildViewUnder(0, 0);
                FrameLayout currentLowView = (FrameLayout) recyclerView.findChildViewUnder(0, mTopHeight);
                //  初始化preView
                if (dy == 0) {
                    preView = currentUpView;
                }
                // 当下移临界点时，标题栏从原view中移到topContainer中
                if (currentLowView != preView || dy > 0) {
                    View titleView = topContainer.getChildAt(0);
                    if (titleView != null) {
                        topContainer.removeView(titleView);
                        params.gravity = Gravity.BOTTOM;
                        titleView.setLayoutParams(params);
                        preView.addView(titleView);
                        //    将preView替换成现有的currentLowView
                        preView = currentLowView;
                        nextView = currentUpView;
                    }
                }
                // 当上移到临界点时
                if (currentUpView != preView || dy < 0) {
                    // 上标题栏置顶
                    View upTv = currentUpView.findViewById(R.id.item_recycler_title);
                    if (null != upTv) {
                        params.gravity = Gravity.BOTTOM;
                        upTv.setLayoutParams(params);
                    }
                    // 下标题栏置顶
                    View lowTv = topContainer.getChildAt(0);
                    if (null != lowTv) {
                        topContainer.removeView(lowTv);
                        params.gravity = Gravity.TOP;
                        lowTv.setLayoutParams(params);
                        preView.addView(lowTv);
                    }
                    //  将preView替换成现有的currentUpView
                    preView = currentUpView;
                }

                //   只有一种情况会让标题栏上浮，就是两个锚点下的view相同的时候
                if (currentUpView.equals(currentLowView)) {
                    View tv = currentLowView.findViewById(R.id.item_recycler_title);
                    if (null != tv) {
                        currentUpView.removeView(tv);
                        params.gravity = Gravity.TOP;
                        tv.setLayoutParams(params);
                        topContainer.addView(tv);
                    }
                    if (nextView != null) {
                        View next = nextView.findViewById(R.id.item_recycler_title);
                        if (null != next) {
                            params.gravity = Gravity.TOP;
                            next.setLayoutParams(params);
                        }
                    }
                }
            }
        });
    }

    PictureAdapter.PictureFragmentItem pictureFragmentItem = new PictureAdapter.PictureFragmentItem() {
        @Override
        public void onItemClick(long[] ids) {
            UIUtils.showToast(mContext, "");
        }

        @Override
        public void onItemLongClick() {
            onActionFragmentListener.onLongClick(null);
        }
    };

    /**
     * 退出选择模式
     */
    public void exitSelectionMode() {
        if (adapter != null) {
            adapter.clearSelectedData();
            adapter.notifyDataSetChanged();
        }
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

    @Override
    public void addPicture(List<MyImage> myImageList) {

    }

    @Override
    public void delPicture(MyImage myImage) {

    }

    @Override
    public void queryPicture(MyImage myImage) {

    }

    @Override
    public void queryPicture(List<MyImage> myImageList) {
        if (adapter == null) {
            adapter = new PictureAdapter(myImageList, mContext);
        } else {
            adapter.setValues(myImageList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error() {

    }

    @Override
    public void setPresenter(PictureContract.Presenter presenter) {

    }


    public interface OnActionFragmentListener {
        // TODO: Update argument type and name
        void onLongClick(View view);
    }

    public void setOnActionFragmentListener(OnActionFragmentListener onActionFragmentListener) {
        this.onActionFragmentListener = onActionFragmentListener;
    }

    //    class TopView {
//        @Bind(R.id.tv_date)
//        TextView tvDate;
//        @Bind(R.id.line)
//        TextView line;
//        @Bind(R.id.tv_location)
//        TextView tvLocation;
//
//        TopView(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
}
