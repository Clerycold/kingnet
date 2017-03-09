package com.kingnet;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by clery on 2016/11/16.
 */

public class ListViewAnimation extends Animation{

    private View mAnimationView = null;
    private ViewGroup.LayoutParams mViewLayoutParams = null;
    ListAdapter listAdapter =null;

    int totalHeight=0;
    int desiredWidth=0;

    int mStart=0;
    int mEnd =0;

    public ListViewAnimation(ListView ListView){
        listAdapter = ListView.getAdapter();
        animationSettings(ListView, 500);
        desiredWidth = View.MeasureSpec.makeMeasureSpec(ListView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, ListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);// //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight();////统计所有子项的总高度
        }
    }

    public ListViewAnimation(ListView ListView, int duration){
        listAdapter = ListView.getAdapter();
        animationSettings(ListView, duration);
        desiredWidth = View.MeasureSpec.makeMeasureSpec(ListView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, ListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);// //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight();////统计所有子项的总高度
        }
    }

    private void animationSettings(View view, int duration){
        setDuration(duration);
        mAnimationView = view;

        mViewLayoutParams = view.getLayoutParams();

        mStart = mViewLayoutParams.height;
        mEnd = 151;

        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        if(interpolatedTime < 1.0f){
            mViewLayoutParams.height = totalHeight + (int) (mEnd* interpolatedTime);
            mAnimationView.setLayoutParams(mViewLayoutParams);
            mAnimationView.requestLayout();
        }else{
            mViewLayoutParams.height=totalHeight+mEnd;
            mAnimationView.setLayoutParams(mViewLayoutParams);
            mAnimationView.requestLayout();
        }
    }
}
