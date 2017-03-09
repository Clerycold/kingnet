package com.kingnet.ActivityUserInfo.presenter;

/**
 * Created by clery on 2016/12/19.
 */

public interface UserInfoPresenter {
    void ChangeUserName(String UserName);
    String CheckUserName();

    void PrivatePack();
    Boolean CheckPrivatePack();

    Boolean CheckUserInto(String UserInto);
}
