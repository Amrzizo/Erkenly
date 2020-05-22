package com.amit.erkenly.customview;

import android.content.Context;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean isHorizontalScrollEnabled;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isHorizontalScrollEnabled)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isHorizontalScrollEnabled)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    public boolean isHorizontalScrollEnabled() {
        return isHorizontalScrollEnabled;
    }

    public void setHorizontalScrollEnabled(boolean horizontalScrollEnabled) {
        isHorizontalScrollEnabled = horizontalScrollEnabled;
    }
}
