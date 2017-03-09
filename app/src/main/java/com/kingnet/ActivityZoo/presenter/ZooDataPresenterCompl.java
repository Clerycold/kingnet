package com.kingnet.ActivityZoo.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.kingnet.ActivityZoo.model.ZooDataModel;
import com.kingnet.ActivityZoo.view.ZooDataView;
import com.kingnet.Control.NetRunnable;
import com.kingnet.Control.RemindUI;
import com.kingnet.JsonUtils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/12/16.
 */

public class ZooDataPresenterCompl implements ZooDataPresenter{

    ZooDataView zooDataView;
    Context context;

    Handler CleryHandler = null;
    private List<ZooDataModel> zooDataList;

    public ZooDataPresenterCompl(Context context,ZooDataView zooDataView,ArrayList<ZooDataModel> zooDataList){
        this.context=context;
        this.zooDataView=zooDataView;
        this.zooDataList=zooDataList;
        doDownload();
    }

    @Override
    public Boolean ZooDataUpdata() {
        if(zooDataList!=null){
            zooDataView.ZooDataShow();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void doDownload() {
        if(isConnected(context)){
            CleryHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what)
                    {
                        case 1:
                            Bundle bundle = msg.getData();
                            String response = bundle.getString("response");
                            parseDiffJson(response);
                            Log.d("----response",response);
                            break;
                        case 404:
                            break;
                    }
                }
            };
            new Thread(new NetRunnable(CleryHandler,0)).start();

        }else{
            RemindUI.setToast(context,"請開啟網路");
        }
    }

    private void parseDiffJson(String json) {

        if(zooDataList!=null){
            zooDataList.clear();
        }
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonObject1=new JSONObject(jsonObject.getString("result"));
            JSONArray jsonArray=new JSONArray(jsonObject1.getString("results"));

            JSONObject jsonObject2;
            ZooDataModel zooData;
            for(int i=0,j=jsonArray.length();i<j;i++){
                jsonObject2 = (JSONObject) jsonArray.get(i);
                zooData=new ZooDataModel();
                zooData.setZooTitle(jsonObject2.getString("A_Pic01_ALT"));
                zooData.setZooImgUrl(jsonObject2.getString("A_Pic01_URL"));

                Log.i("", "---------------------------" + jsonObject2.getString("A_Pic01_ALT"));
                zooDataList.add(zooData);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ZooDataUpdata();
    }
}
