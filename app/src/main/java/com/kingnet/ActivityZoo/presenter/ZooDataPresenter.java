package com.kingnet.ActivityZoo.presenter;

import android.content.Context;

import com.kingnet.ExpandableList.ZooListAdapter;

/**
 * Created by clery on 2016/12/16.
 */

public interface ZooDataPresenter {

    Boolean ZooDataUpdata();
    Boolean isConnected(Context context);
    void doDownload();

}
