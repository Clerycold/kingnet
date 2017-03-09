package com.kingnet;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by clery on 2016/11/14.
 */

public class ViewExpandAnimation extends Animation {

    private View mAnimationView = null;
    private RelativeLayout.LayoutParams mViewLayoutParams = null;
    private int mStart = 0;
    private int mEnd = 0;

    public ViewExpandAnimation(View view,ListView listView){
        animationSettings(view, 500,listView);
    }

    public ViewExpandAnimation(View view, int duration,ListView listView){
        animationSettings(view, duration,listView);
    }

    private void animationSettings(View view, int duration,ListView listView){
        setDuration(duration);
        mAnimationView = view;

        mViewLayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

        mStart = mViewLayoutParams.bottomMargin;
        mEnd = (mStart == 0 ? (0 - view.getHeight()) : 0);

        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        if(interpolatedTime < 1.0f){
            mViewLayoutParams.bottomMargin = mStart + (int) ((mEnd - mStart) * interpolatedTime);
            // invalidate
            mAnimationView.requestLayout();
        }else{
            mViewLayoutParams.bottomMargin = mEnd;
            mAnimationView.requestLayout();
            if(mEnd != 0){
                mAnimationView.setVisibility(View.GONE);
            }
        }
    }

}