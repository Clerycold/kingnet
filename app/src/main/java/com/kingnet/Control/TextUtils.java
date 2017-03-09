package com.kingnet.Control;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.JsonUtils.AsynNetUtils;
import com.kingnet.JsonUtils.NetUtils;
import com.kingnet.R;

/**
 * Created by clery on 2016/11/25.
 */

public class TextUtils {


    public interface Callback{
        void onResponse(String response);
    }
    public interface CallbackDrawable{
        void onResponse(Drawable response);
    }

    public static void setText(final String string, final TextUtils.Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回傳string給onResponse
                        callback.onResponse(string);
                    }
                });
            }
        }).start();
    }

    public static void setBitmap(final Resources res, final int resId,final int reqWidth,final int reqHeight, final TextUtils.CallbackDrawable callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Drawable response = BitmapUtils.decodeSampledDrawableFromResource(res, resId, reqWidth, reqHeight);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回傳string給onResponse
                        callback.onResponse(response);
                    }
                });
            }
        }).start();

    }
}
