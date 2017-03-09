package com.kingnet.ActivityZoo.model;

/**
 * Created by clery on 2016/12/16.
 */

public class ZooDataModel implements ZooDate{

    private String zooTitle;
    private String zooImgUrl;

    @Override
    public String getZooImgUrl() {
        return zooImgUrl;
    }

    @Override
    public void setZooImgUrl(String ZooImgUrl) {
        this.zooImgUrl=ZooImgUrl;

    }

    @Override
    public String getZooTitle() {
        return zooTitle;
    }

    @Override
    public void setZooTitle(String ZooTitle) {

        this.zooTitle=ZooTitle;
    }


}
