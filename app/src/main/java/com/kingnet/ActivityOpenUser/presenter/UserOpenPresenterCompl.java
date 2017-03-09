package com.kingnet.ActivityOpenUser.presenter;

import com.kingnet.ActivityOpenUser.model.UserOpenModel;
import com.kingnet.ActivityOpenUser.view.UserOpenView;

/**
 * Created by clery on 2016/12/16.
 */

public class UserOpenPresenterCompl implements UserOpenPresenter{

    UserOpenView userOpenView;
    UserOpenModel userOpenModel;

    public UserOpenPresenterCompl(UserOpenView userOpenView){
        this.userOpenView=userOpenView;
        userOpenModel=new UserOpenModel();
    }

    @Override
    public void Clear() {
        userOpenView.onClear();
    }

    @Override
    public void CheckUserOpenId(String userOpenId) {
        int result=userOpenModel.CheckUserIdValidity(userOpenId);

        if(result==0){
            //代表長度正確 在進入後台去做開通比對
            userOpenView.onOpenResult(result);
        }else{
            userOpenView.onOpenResult(result);
        }

    }
}
