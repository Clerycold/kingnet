package com.kingnet.JsonUtils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by clery on 2016/11/25.
 */

public class AsynNetUtils {
    public interface Callback{
        void onResponse(String response);
    }

    public static void get(final String url, final Callback callback){
        if (Looper.myLooper() == null)
        {
            Looper.prepare();
        }
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //final String response = NetUtils.get(url);
                final String response = "";
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回傳string給onResponse
                        callback.onResponse(response);
                        try{
                            if (Looper.myLooper() != null){
                                handler.getLooper().quit();
                            }
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
        Looper.loop();
    }

    public static void post(final String url, final String content, final Callback callback){

        //Looper.myLooper取得当前线程的Looper对象
        if (Looper.myLooper() == null)
        {
            /**
             * 通过调用Looper.prepare()为该线程建立一个MessageQueue，再调用Looper.loop()进行消息循环
             * 一个线程中调用Looper.prepare()，那么系统就会自动的为该线程建立一个消息队列，然后调用 Looper.loop();之后就进入了消息循环
             */
            Looper.prepare();
        }
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //final String response = NetUtils.post(url,content);
                final String response = "";
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        callback.onResponse(response);
                        try{
                            if (Looper.myLooper() != null){
                                handler.getLooper().quit();
                            }
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
        Looper.loop();

    }
}

