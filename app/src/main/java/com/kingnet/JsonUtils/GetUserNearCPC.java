package com.kingnet.JsonUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.kingnet.Control.NetRunnable;
import com.kingnet.Data.CPCListData;
import com.kingnet.Data.GetCPCData;
import com.kingnet.GetShareMemory.SaveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/12/5.
 */

public class GetUserNearCPC extends AsyncTask<String,Integer,String> {
    private static List<CPCListData> cpcListDatas;
    private Context context;

    Handler CleryHandler = null;

    public GetUserNearCPC(Context context,Handler handler) {
        CleryHandler=handler;
        this.context = context.getApplicationContext();
    }

    @Override
    protected String doInBackground(String... params) {

        getLocationInfo();

        return null;
    }

    private void getLocationInfo() {

        String stringBuffer = null;
        try {
            stringBuffer = URLEncoder.encode("val", "UTF-8") +
                    "=" +"[{"
                    + "city" + ":" + ","
                    + "area" + ":" + ","
                    + "lat" + ":" + Double.toString(GetCPCData.getLatitude()) + ","
                    + "lon" + ":" + Double.toString(GetCPCData.getLongitude()) + ","
                    + "km" + ":" + "5" + "}]";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("-----", stringBuffer);

//        AsynNetUtils.post(string, stringBuffer, new AsynNetUtils.Callback() {
//            @Override
//            public void onResponse(String response) {
//                parseDiffJson(response);
//            }
//        });
        if (Looper.myLooper() == null)
        {
            Looper.prepare();
        }
        new Thread(new NetRunnable(CleryHandler,2,stringBuffer)).start();

    }

    private void parseDiffJson(String json) {

        Log.d("----CPC",json);

        CPCListData cpcListData;
        try{
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject;

            if(jsonArray.length()!=0) {
                cpcListDatas=new ArrayList<CPCListData>();
                for(int i = 0,j=jsonArray.length();i <= j;i++){
                    jsonObject = (JSONObject) jsonArray.get(i);
                    Log.d("----",jsonObject.toString());
                    cpcListData=new CPCListData();
                    cpcListData.setType(jsonObject.getString("type"));
                    cpcListData.setStation_name(jsonObject.getString("station_name"));
                    cpcListData.setAddress(jsonObject.getString("address"));
                    cpcListData.setKm(jsonObject.getString("km"));
                    cpcListData.setLatitude(jsonObject.getString("latitude"));
                    cpcListData.setLongitude(jsonObject.getString("longitude"));
                    cpcListData.setOpen_start(jsonObject.getString("open_start"));
                    cpcListData.setOpen_due(jsonObject.getString("open_due"));
                    cpcListDatas.add(cpcListData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<CPCListData> getListCPCListData(){
        return cpcListDatas;
    }
}