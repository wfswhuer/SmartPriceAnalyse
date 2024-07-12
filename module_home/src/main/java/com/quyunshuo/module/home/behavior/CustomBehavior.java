package com.quyunshuo.module.home.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.reflect.Field;

public class CustomBehavior extends AppBarLayout.Behavior {
    private OverScroller mScroller;

    public CustomBehavior() {
    }

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        getParentScroller(context);
    }

    /**
     * 反射获得滑动属性。
     *
     * @param context
     */
    private void getParentScroller(Context context) {
        if (mScroller != null) {
            return;
        }
        mScroller = new OverScroller(context);
        try {
            Class<?> reflexClass = getClass().getSuperclass().getSuperclass(); // 父类AppBarLayout.Behavior的父类HeaderBehavior
            Field fieldScroller = reflexClass.getDeclaredField("mScroller");
            fieldScroller.setAccessible(true);
            fieldScroller.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常
        }
    }

    // fling上滑appbar然后迅速fling下滑recycler时, HeaderBehavior的mScroller并未停止, 会导致上下来回晃动
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        if (mScroller != null) { // 当recyclerView做好滑动准备的时候直接终止AppBar的滑动
            if (mScroller.computeScrollOffset()) {
                mScroller.abortAnimation();
            }
        }
        if (type == ViewCompat.TYPE_NON_TOUCH && getTopAndBottomOffset() == 0) { // recyclerview惯性比较大会顶在头部一会儿，到头直接终止它的滑动
            ViewCompat.stopNestedScroll(target, type);
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent e) {
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            // 添加必要的操作
        }
        return super.onTouchEvent(parent, child, e);
    }
}
