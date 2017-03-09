package com.kingnet.Control;

import android.content.Context;
import android.content.Intent;

/**
 * Created by clery on 2016/11/22.
 */

public class IntentActivity {

    public static void IntentActivity(Context context,Class<?> cls){
        Intent intent=new Intent();
        Context mContext=context.getApplicationContext();
        intent.setClass(mContext,cls);
        try{
            mContext.startActivity(intent);
        }catch (Exception e){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
