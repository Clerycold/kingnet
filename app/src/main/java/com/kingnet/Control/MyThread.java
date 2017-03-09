package com.kingnet.Control;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by clery on 2016/11/23.
 */

public class MyThread extends Thread{

    public interface setmHandlerMessage{}

    private Handler mHandler = null;
    private int mMessage;

    private long millis;
    public MyThread(Handler handler){
        mHandler=handler;
    }
    /**
     參數名稱    型別         用途
     what       int         標記識別符號
     obj        Object      物件, 必須Parcelable
     data       Bundle      Bundle物件
     callback   Runnable    實作Runnable的callback
     */
    @Override
    public void run() {
        Looper.prepare();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Looper.loop();
    }

    public void setmHandlerMessage(int msg){
        mMessage=msg;
        mHandler.sendEmptyMessage(msg);
    }
    public void setMillis(long millis){
     this.millis=millis;
    }
}
