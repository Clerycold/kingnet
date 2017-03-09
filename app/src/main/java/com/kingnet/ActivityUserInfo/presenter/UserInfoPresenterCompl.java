package com.kingnet.ActivityUserInfo.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.kingnet.ActivityUserInfo.model.UserInfoModel;
import com.kingnet.ActivityUserInfo.view.UserInfoView;

/**
 * Created by clery on 2016/12/19.
 */

public class UserInfoPresenterCompl implements UserInfoPresenter{

    private UserInfoView userInfoView;
    UserInfoModel userInfoModel;

    Handler handler;

    public UserInfoPresenterCompl(Context context,UserInfoView userInfoView){
        this.userInfoView=userInfoView;
        userInfoModel=new UserInfoModel(context);
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void ChangeUserName(final String UserName) {
        userInfoModel.saveUserName(UserName);
        handler.post(new Runnable() {
            @Override
            public void run() {
                userInfoView.showUserName(UserName);
            }
        });
    }

    @Override
    public String CheckUserName() {
        return userInfoModel.readUserName();
    }

    @Override
    public void PrivatePack() {
        final Boolean isPrivate;
        if(!CheckPrivatePack()){
            isPrivate=true;
        }else{
            isPrivate=false;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                userInfoView.showUserPrivatePack(isPrivate);
                userInfoModel.savePrivatePack(isPrivate);
            }
        });
    }

    @Override
    public Boolean CheckPrivatePack(){
        return userInfoModel.isPrivatePack();
    }

    @Override
    public Boolean CheckUserInto(String UserInto) {
        return userInfoModel.CheckUserInto(UserInto);
    }

}
