package com.kingnet.ActivityHome.presenter;

import android.content.Context;

import com.kingnet.ActivityHome.model.IsUserFirst;
import com.kingnet.ActivityHome.model.IsUserFirstModel;
import com.kingnet.ActivityHome.view.IShowTourView;

/**
 * Created by clery on 2016/12/16.
 */

public class IsUserFirstPresenterCompl implements IsUserFirstPresenter{

    IShowTourView iShowTourView;
    IsUserFirst isUserFirst;

    public IsUserFirstPresenterCompl(Context context,IShowTourView iShowTourView){
        this.iShowTourView=iShowTourView;
        isUserFirst=new IsUserFirstModel(context);
    }

    @Override
    public void IsFirstCheck(){
        iShowTourView.onShowTourView(isUserFirst.getBoolean());
    }
}
