package com.kingnet.ActivityUserInfo.model;

import android.content.Context;

import com.kingnet.GetShareMemory.SaveData;

/**
 * Created by clery on 2016/12/19.
 */

public class UserInfoModel implements UserInfoData{

    private SaveData saveData;

    public UserInfoModel(Context context){
        saveData=new SaveData(context);
    }

    @Override
    public String readUserName() {
        return saveData.getStringData("UserName","user_name");
    }

    @Override
    public void saveUserName(String UserName) {
        saveData.saveStringData("UserName","user_name",UserName);
    }

    @Override
    public Boolean isPrivatePack() {
        return saveData.readDataBoolean("userPrivate","isOnly");
    }

    @Override
    public void savePrivatePack(Boolean UserPrivate) {
        saveData.saveBooleanData("userPrivate","isOnly",UserPrivate);
    }

    @Override
    public Boolean CheckUserInto(String UserInto) {
        return UserInto.equals("");
    }
}
