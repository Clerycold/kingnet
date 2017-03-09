package com.kingnet.Control;

import android.os.Handler;
import android.util.Log;

import com.kingnet.JsonUtils.NetUtils;


/**
 * Created by clery on 2016/12/21.
 */

public class NetRunnable implements Runnable{

    int type;
    String internetSite;
    String postcontent;
    NetUtils Net = new NetUtils();
    public NetRunnable(Handler NetHandler,int path){
        setInternetSite(path);
        Net.SetHandler(NetHandler);
        this.type=0;
    }

    public NetRunnable(Handler NetHandler,int path,String postcontent){
        setInternetSite(path);
        this.postcontent=postcontent;
        Net.SetHandler(NetHandler);
        this.type=1;
    }

    @Override
    public void run() {
        switch (type){
            case 0:
                Net.get(internetSite);
                break;
            case 1:
                Net.post(internetSite,postcontent);
                break;
        }
    }
    private void setInternetSite(int path){
        switch (path){
            case 0:
                internetSite="http://download.post.gov.tw/post/download/Xml_10510.xml";
                break;
            //validDevice
            case 1:
                internetSite="http://172.16.4.249/ajax/app_ws.asmx/validDevice";
                break;
            //getSTNStation
            case 2:
                internetSite="http://172.16.4.249/ajax/app_ws.asmx/getSTNStation";
                break;
        }
    }
}
