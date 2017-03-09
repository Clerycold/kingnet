package com.kingnet.ActivityUserInfo.model;

/**
 * Created by clery on 2016/12/19.
 */

public interface UserInfoData {

    String readUserName();
    void saveUserName(String UserName);

    Boolean isPrivatePack();
    void savePrivatePack(Boolean UserPrivate);

    Boolean CheckUserInto(String UserInto);
}
