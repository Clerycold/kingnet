package com.kingnet.ActivityHome.model;

import android.content.Context;

import com.kingnet.GetShareMemory.SaveData;

/**
 * Created by clery on 2016/12/16.
 */

public class IsUserFirstModel implements IsUserFirst{

    private Boolean isFirst;
    private SaveData saveData;

    public IsUserFirstModel(Context context){
        saveData=new SaveData(context);
        isFirst=saveData.readDataBoolean("myActivityName", "isFirst");
    }

    @Override
    public Boolean getBoolean() {
        return isFirst;
    }

}
