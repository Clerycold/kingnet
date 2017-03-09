package com.kingnet.MyViewpage;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Mytransformer implements ViewPager.PageTransformer {
    private static final float min_scale = 0.85f;
    private static final float min_alpha = 0.5f;

    /**
     *當前選中的viewpage 當前position永遠為0 前一個為-1 後一個為1
     */

    @Override
    public void transformPage(View page, float position) {
        /**
         * Math.abs求絕對值
         * Math.max 两个中返回大的值
         */
        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
        float alphaFactor = Math.max(min_alpha, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {

//            Log.d("--<-1",String.format("%.2f", scaleFactor));
        }
        /**
         * 移動到前一個設定
         */
        else if (position <= 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(alphaFactor);
            page.setRotationY(rotate);
            /**
             * 移動到後一個的設定
             */
        } else if (position > 0 && position <= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(alphaFactor);
            page.setRotationY(-rotate);
            /**
             * 消失
             */
        } else if (position > 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(alphaFactor);
            page.setRotationY(-rotate);
        }
    }
}