package com.kingnet.Animation;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by clery on 2016/11/23.
 */

public class createDropAnim {

    private static ValueAnimator createDropAnim(final View view, int start, int end) {
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return  va;
    }
    public static void animOpen(final  View view,int ViewMeasuredHeight){
        view.setVisibility(View.VISIBLE);
        ValueAnimator va = createDropAnim(view,0,ViewMeasuredHeight);
        va.start();
    }

    public static void animClose(final  View view){
        int origHeight = view.getHeight();
        ValueAnimator va = createDropAnim(view,origHeight,0);

        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value =(int)animation.getAnimatedValue();
                if(value==0){
                    view.setVisibility(View.GONE);
                }
            }
        });
        va.start();
    }
}
