package com.kingnet.ActivityLifeConvenience.presenter;

import android.content.Context;

import com.kingnet.ActivityLifeConvenience.model.LifeItem;
import com.kingnet.ActivityLifeConvenience.model.LifeItemModel;
import com.kingnet.ActivityLifeConvenience.view.LifeConvenienceView;

/**
 * Created by clery on 2016/12/23.
 */

public class LifeConveniencePreCompl implements LifeConveniencePresenter{

    LifeConvenienceView lifeConvenienceView;
    LifeItemModel lifeItemModel;

    public LifeConveniencePreCompl(Context context, LifeConvenienceView lifeConvenienceView){
        this.lifeConvenienceView = lifeConvenienceView;
        lifeItemModel = new LifeItemModel();
    }

    @Override
    public void CheckLifeItem() {
        lifeConvenienceView.ShowLifeList(lifeItemModel.getTitle(),lifeItemModel.getContent());
    }
}
