package com.kingnet.UIpattern;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by clery on 2016/12/19.
 */

public class PageDot extends LinearLayout{

    private Context context;

    private int Width;
    private int Height;

    public PageDot(Context context,int dotWidth,int count,int resId) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(getContext());

        this.context=context;

        initView(dotWidth,count,resId);
    }

    public PageDot(Context context, AttributeSet attrs) {
        super(context, attrs);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(getContext());

        this.context=context;
    }

    private void initView(int dotWidth,int Count,int redId){
        /**
         * 循環最佳化
         * i<homePageAdapter.getCount()盡量避免一直循環產生 帶來效能消耗
         *
         */
        View dot;
        for (int i = 0 ,p=Count; i < p; i++) {
            dot = new View(context);
            dot.setBackgroundResource(redId);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotWidth, dotWidth);
            params.leftMargin = (int) (double) Width / 50;
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            this.addView(dot);
        }
        this.getChildAt(0).setEnabled(true);
    }
}
