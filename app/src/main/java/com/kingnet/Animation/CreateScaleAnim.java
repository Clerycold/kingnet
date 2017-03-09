package com.kingnet.Animation;

import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * Created by clery on 2016/12/2.
 */

public class CreateScaleAnim {

    public static void smallScale(final View view){
        view.setScaleX(0.95f);
        view.setScaleY(0.95f);
    }
    public static void prototypeScale(final View view){
        view.setScaleX(1);
        view.setScaleY(1);
    }
}
