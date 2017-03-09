package com.kingnet.Data;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.kingnet.JsonUtils.GetUserNearCPC;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by clery on 2016/12/5.
 */

public class GetCPCData implements LocationListener {

    private static Double LONGTIUDE;
    private static Double LATITUDE;
    private Context context;
    private boolean getService = false;
    private LocationManager lms;
    private String bestProvider = LocationManager.GPS_PROVIDER;

    Handler CleryHandler = null;
    private static List<CPCListData> cpcListDatas;

    public GetCPCData(Context context) {
        this.context = context;
        //取得系統定位服務
        LocationManager status = (LocationManager) (context.getApplicationContext().getSystemService(LOCATION_SERVICE));
        if (status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //如果GPS或網路定位開啟，呼叫locationServiceInitial()更新位置
            locationServiceInitial();
        } else {
            Toast.makeText(context.getApplicationContext(), "請開啟定位服務", Toast.LENGTH_LONG).show();
            getService = true; //確認開啟定位服務
            context.getApplicationContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)); //開啟設定頁面
        }
    }

    private void locationServiceInitial() {

        lms = (LocationManager) context.getApplicationContext().getSystemService(LOCATION_SERVICE); //取得系統定位服務
//        Location location = lms.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Criteria criteria = new Criteria();  //資訊提供者選取標準
        // 获得最好的定位效果
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        // 使用省电模式
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        bestProvider = lms.getBestProvider(criteria, true);    //選擇精準度最高的提供者
        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = lms.getLastKnownLocation(bestProvider);
        int i=0;
        while (location == null &&i<20) {
            location = lms.getLastKnownLocation(bestProvider);
            i++;
        }
        getLocation(location);
    }

    private void getLocation(Location location) { //將定位資訊顯示在畫面中
        if (location != null) {
            LONGTIUDE = location.getLongitude();   //取得經度
            LATITUDE = location.getLatitude();     //取得緯度

            CleryHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what)
                    {
                        case 0:
                            Bundle bundle = msg.getData();
                            String response = bundle.getString("response");
                            parseDiffJson(response);

                            try{
                                if (Looper.myLooper() != null){
                                    CleryHandler.getLooper().quit();
                                }
                            }catch (IllegalStateException e){
                                e.printStackTrace();
                            }
                            break;
                        case 404:
                            break;
                    }
                }
            };

            new GetUserNearCPC(context.getApplicationContext(),CleryHandler).execute("http://172.16.4.249/ajax/app_ws.asmx/getSTNStation");

        } else {
            Toast.makeText(context.getApplicationContext(), "無法定位座標", Toast.LENGTH_LONG).show();
        }
    }

    private void parseDiffJson(String json) {

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

    public static Double getLongitude() {
        return LONGTIUDE;
    }

    public static Double getLatitude() {
        return LATITUDE;
    }

    @Override
    public void onLocationChanged(Location location) {  //當地點改變時
        // TODO 自動產生的方法 Stub
        getLocation(location);
    }

    @Override
    public void onProviderDisabled(String arg0) {//當GPS或網路定位功能關閉時
        // TODO 自動產生的方法 Stub
        Toast.makeText(context.getApplicationContext(), "請開啟gps或3G網路", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String arg0) { //當GPS或網路定位功能開啟
        // TODO 自動產生的方法 Stub
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) { //定位狀態改變
        // TODO 自動產生的方法 Stub
    }

    public LocationManager getLms() {


        if(getService) {
            if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;
            }
            lms.requestLocationUpdates(bestProvider, 1000, 1, this);
        }
        return lms;
    }

    public void setStopLocationManager() {

        if(getService) {
            if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            lms.removeUpdates(this);   //離開頁面時停止更新
        }
    }
}