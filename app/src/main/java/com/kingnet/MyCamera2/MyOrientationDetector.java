package com.kingnet.MyCamera2;

import android.content.Context;
import android.util.Log;
import android.view.OrientationEventListener;

/**
 * Created by clery on 2016/11/21.
 */

public class MyOrientationDetector extends OrientationEventListener {

    public static int ORIENTATION;

    public MyOrientationDetector(Context context) {
        super(context);
    }

    @Override
    public void onOrientationChanged(int orientation) {

        if(orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
            return;  //手机平放时，检测不到有效的角度
        }
        //只检测是否有四个角度的改变
        if( orientation > 350 || orientation< 10 ) { //0度
            ORIENTATION = 0;
        }
        else if( orientation > 80 &&orientation < 100 ) { //90度
            ORIENTATION= 90;
        }
        else if( orientation > 170 &&orientation < 190 ) { //180度
            ORIENTATION= 180;
        }
        else if( orientation > 260 &&orientation < 280  ) { //270度
            ORIENTATION= 270;
        }
        else {
            return;
        }
    }
//    public int getORIENTATION(){
//
//        return this.ORIENTATION;
//    }
}