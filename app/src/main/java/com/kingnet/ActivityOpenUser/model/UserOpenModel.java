package com.kingnet.ActivityOpenUser.model;

import android.util.Log;

/**
 * Created by clery on 2016/12/16.
 */

public class UserOpenModel implements UserOpenId {

    @Override
    public int CheckUserIdValidity(String openId) {
        if(openId.equals("")){
            return -1;
        }else if(openId.length()!=8){
            return 1;
        }
        return 0;
    }
}
