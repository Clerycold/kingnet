package com.kingnet.MoreInfo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.kingnet.R;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/11/8.
 */

public class LifeGoodBuy extends Activity{

    Titledesign titledesign;

    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.lifegoodbuy);

        Resources resources = LifeGoodBuy.this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);

        Height -=height;

        initView();

        titledesign.setTextView(getResources().getString(R.string.titlekingnetselect));
    }

    private void initView() {
        titledesign = (Titledesign) findViewById(R.id.titledesign);

    }
}
