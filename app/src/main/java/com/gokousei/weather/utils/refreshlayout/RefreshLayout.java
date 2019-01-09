package com.gokousei.weather.utils.refreshlayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gokousei.weather.R;

public class RefreshLayout extends ViewGroup {

    private View mHeader;
    private TextView mHeaderText;
    private ImageView mHeaderArrow;
    private ProgressBar mHeaderProgressBar;
    private View mFooter;
    private TextView mFooterText;
    private ProgressBar mFooterProgressBar;
    private ImageView mFooterArrow;

    private int mLayoutContentHeight;
    private int mEffectiveHeaderHeight;
    private int mEffectiveFooterHeight;
    private int mLastMoveY;
    private float mLastRotation;
    private int mLastYIntercept;
    private int lastChildIndex;
    private int lastXIntercept, lastYIntercept;

    ObjectAnimator mAnimator;

    private static OnRefreshListener mRefreshListener;

    private Status mStatus = Status.NORMAL;

    private void updateStatus(Status status) {
        mStatus = status;
    }

    private enum Status {
        NORMAL, TRY_REFRESH, REFRESHING, TRY_LOADMORE, LOADING
    }

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    // 当view的所有childView从xml中被初始化后调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        lastChildIndex = getChildCount() - 1;
        addHeader();
        addFooter();
    }

    private void addHeader() {
        mHeader = LayoutInflater.from(getContext())
                .inflate(R.layout.refresh_layout_header, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mHeader, params);
        mHeaderText = findViewById(R.id.refresh_layout_header_text);
        mHeaderProgressBar = findViewById(R.id.refresh_layout_header_progressbar);
        mHeaderArrow = findViewById(R.id.refresh_layout_header_arrow);
    }

    private void addFooter() {
        mFooter = LayoutInflater.from(getContext())
                .inflate(R.layout.refresh_layout_footer, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mFooter, params);
        mFooterText = findViewById(R.id.refresh_layout_footer_text);
        mFooterProgressBar = findViewById(R.id.refresh_layout_footer_progressbar);
        mFooterArrow = findViewById(R.id.refresh_layout_footer_arrow);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLayoutContentHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == mHeader) {
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
                mEffectiveHeaderHeight = child.getMeasuredHeight();
            } else if (child == mFooter) {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(),
                        mLayoutContentHeight + child.getMeasuredHeight());
                mEffectiveFooterHeight = child.getMeasuredHeight();
            } else {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(),
                        mLayoutContentHeight + child.getMeasuredHeight());
                if (i < getChildCount()) {
                    if (child instanceof ScrollView) {
                        mLayoutContentHeight += getMeasuredHeight();
                        continue;
                    }
                    mLayoutContentHeight += child.getMeasuredHeight();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        if (mStatus == Status.REFRESHING || mStatus == Status.LOADING) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastMoveY - y;
                if (getScaleY() <= 0 && dy <= 0) {
                    if (mStatus == Status.TRY_LOADMORE) {
                        scrollBy(0, dy / 30);
                    } else {
                        scrollBy(0, dy / 3);
                    }
                } else if (getScrollY() >= 0 && dy >= 0) {
                    if (mStatus == Status.TRY_REFRESH) {
                        scrollBy(0, dy / 30);
                    } else {
                        scrollBy(0, dy / 3);
                    }
                } else {
                    scrollBy(0, dy / 3);
                }
                beforeRefreshing(dy);
                beforeLoadMore(dy);
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() <= -mEffectiveHeaderHeight) {
                    releaseWithStatusRefresh();
                    if (mRefreshListener != null) {
                        mRefreshListener.doRefresh();
                        refreshFinished();
                    }
                } else if (getScrollY() >= mEffectiveFooterHeight) {
                    releaseWithStatusLoadMore();
                    if (mRefreshListener != null) {
                        mRefreshListener.doLoadMore();
                        loadMoreFinished();
                    }
                } else {
                    releaseWithStatusTryRefresh();
                    releaseWithStatusTryLoadMore();
                }
                break;
        }
        mLastMoveY = y;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        if (mStatus == Status.REFRESHING || mStatus == Status.LOADING) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = y;
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastXIntercept;
                int deltaY = y - lastYIntercept;
                if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    if (y > mLastYIntercept) {
                        View child = getChildAt(0);
                        intercept = getRefreshIntercept(child);
                        if (intercept) {
                            updateStatus(mStatus.TRY_REFRESH);
                        }
                    } else if (y < mLastYIntercept) {
                        View child = getChildAt(lastChildIndex);
                        intercept = getLoadMoreIntercept(child);

                        if (intercept) {
                            updateStatus(mStatus.TRY_LOADMORE);
                        }
                    } else {
                        intercept = false;
                    }
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        mLastYIntercept = y;
        lastYIntercept = y;
        lastXIntercept = x;
        return intercept;
    }

    private boolean getRefreshIntercept(View child) {
        boolean intercept = false;

        if (child instanceof AdapterView) {
            intercept = adapterViewRefreshIntercept(child);
        } else if (child instanceof ScrollView) {
            intercept = scrollViewRefreshIntercept(child);
        } else if (child instanceof RecyclerView) {
            intercept = recyclerViewRefreshIntercept(child);
        }
        return intercept;
    }

    private boolean getLoadMoreIntercept(View child) {
        boolean intercept = false;

        if (child instanceof AdapterView) {
            intercept = adapterViewLoadMoreIntercept(child);
        } else if (child instanceof ScrollView) {
            intercept = scrollViewLoadMoreIntercept(child);
        } else if (child instanceof RecyclerView) {
            intercept = recyclerViewLoadMoreIntercept(child);
        }
        return intercept;
    }

    /*具体判断各种View是否应该拦截*/
    // 判断AdapterView下拉刷新是否拦截
    private boolean adapterViewRefreshIntercept(View child) {
        boolean intercept = true;
        AdapterView adapterChild = (AdapterView) child;
        if (adapterChild.getFirstVisiblePosition() != 0
                || adapterChild.getChildAt(0).getTop() != 0) {
            intercept = false;
        }
        return intercept;
    }

    // 判断AdapterView加载更多是否拦截
    private boolean adapterViewLoadMoreIntercept(View child) {
        boolean intercept = false;
        AdapterView adapterChild = (AdapterView) child;
        if (adapterChild.getLastVisiblePosition() == adapterChild.getCount() - 1 &&
                (adapterChild.getChildAt(adapterChild.getChildCount() - 1).getBottom() >= getMeasuredHeight())) {
            intercept = true;
        }
        return intercept;
    }

    // 判断ScrollView刷新是否拦截
    private boolean scrollViewRefreshIntercept(View child) {
        boolean intercept = false;
        if (child.getScrollY() <= 0) {
            intercept = true;
        }
        return intercept;
    }

    // 判断ScrollView加载更多是否拦截
    private boolean scrollViewLoadMoreIntercept(View child) {
        boolean intercept = false;
        ScrollView scrollView = (ScrollView) child;
        View scrollChild = scrollView.getChildAt(0);

        if (scrollView.getScrollY() >= (scrollChild.getHeight() - scrollView.getHeight())) {
            intercept = true;
        }
        return intercept;
    }

    // 判断RecyclerView刷新是否拦截
    private boolean recyclerViewRefreshIntercept(View child) {
        boolean intercept = false;

        RecyclerView recyclerView = (RecyclerView) child;
        if (recyclerView.computeVerticalScrollOffset() <= 0) {
            intercept = true;
        }
        return intercept;
    }

    // 判断RecyclerView加载更多是否拦截
    private boolean recyclerViewLoadMoreIntercept(View child) {
        boolean intercept = false;

        RecyclerView recyclerView = (RecyclerView) child;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange()) {
            intercept = true;
        }

        return intercept;
    }

    /*修改header和footer的状态*/
    public void beforeRefreshing(float dy) {
        //计算旋转角度
        int scrollY = Math.abs(getScrollY());
        scrollY = scrollY > mEffectiveHeaderHeight ? mEffectiveHeaderHeight : scrollY;
        float angle = (float) (scrollY * 1.0 / mEffectiveHeaderHeight * 180);
        mHeaderArrow.setRotation(angle);


        if (getScrollY() <= -mEffectiveHeaderHeight) {
            mHeaderText.setText("松开刷新");
        } else {
            mHeaderText.setText("下拉刷新");
        }
    }

    public void beforeLoadMore(float dy) {
        //计算旋转角度
        int scrollY = Math.abs(getScrollY());
        scrollY = scrollY > mEffectiveFooterHeight ? mEffectiveFooterHeight : scrollY;
        float angle = (float) (scrollY * 1.0 / mEffectiveFooterHeight * 180);
        mFooterArrow.setRotation(angle);


        if (getScrollY() >= mEffectiveHeaderHeight) {
            mFooterText.setText("松开加载更多");
        } else {
            mFooterText.setText("上拉加载更多");
        }
    }

    public void refreshFinished() {
        scrollTo(0, 0);
        mHeaderText.setText("下拉刷新");
        mHeaderProgressBar.setVisibility(GONE);
        mHeaderArrow.setVisibility(VISIBLE);
        updateStatus(Status.NORMAL);
    }

    public void loadMoreFinished() {
        mFooterText.setText("上拉加载");
        mFooterProgressBar.setVisibility(GONE);
        scrollTo(0, 0);
        updateStatus(Status.NORMAL);
    }

    private void releaseWithStatusTryRefresh() {
        scrollBy(0, -getScrollY());
        mHeaderText.setText("下拉刷新");
        updateStatus(Status.NORMAL);
    }

    private void releaseWithStatusTryLoadMore() {
        scrollBy(0, -getScrollY());
        mFooterText.setText("上拉加载更多");
        updateStatus(Status.NORMAL);
    }

    private void releaseWithStatusRefresh() {
        scrollTo(0, -mEffectiveHeaderHeight);
        mHeaderProgressBar.setVisibility(VISIBLE);
        mHeaderArrow.setVisibility(GONE);
        mHeaderText.setText("正在刷新");
        updateStatus(Status.REFRESHING);
    }

    private void releaseWithStatusLoadMore() {
        scrollTo(0, mEffectiveFooterHeight);
        mFooterText.setText("正在加载");
        mFooterProgressBar.setVisibility(VISIBLE);
        updateStatus(Status.LOADING);
    }
    /*修改header和footer的状态*/

}
