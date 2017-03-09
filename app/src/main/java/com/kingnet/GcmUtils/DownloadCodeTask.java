package com.kingnet.GcmUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.kingnet.Control.DeviceUuidFactory;
import com.kingnet.Control.MyThread;
import com.kingnet.Control.NetRunnable;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.encodeAsBitmap;
import com.kingnet.GetShareMemory.SaveData;
import com.kingnet.JsonUtils.AsynNetUtils;
import com.kingnet.Newfragment.BarcodeFtagment;
import com.kingnet.Newfragment.QrcodeFragment;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static com.kingnet.ActivityHome.MainActivity.VALIDCODE;

/**
 * Created by clery on 2016/11/25.
 */

public class DownloadCodeTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private String suuid;
    private BroadcastReceiver receiver;
    private String data = null;
    private String gettoken;

    private SaveData saveData;

    Handler CleryHandler = null;


    public DownloadCodeTask(Context context){
        this.context=context.getApplicationContext();
        saveData=new SaveData(context);
    }

    @Override
    protected String doInBackground(String... params) {

        String url;
        url=params[0];

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
                        break;
                    case 404:
                        break;
                }
            }
        };

        getPhoneInfo(url);

        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);


    }

    private void getPhoneInfo(final String string){

        DeviceUuidFactory deviceUuidFactory=new DeviceUuidFactory(context);
        UUID uuid=deviceUuidFactory.getDeviceUuid();

        suuid = uuid.randomUUID().toString();

        Intent intent = new Intent(context, RegistrationIntentService.class);
        context.startService(intent);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 處理 Service 傳來的訊息。
                Bundle message = intent.getExtras();
                gettoken = message.getString("token");


                try {

                    String stringBuffer = URLEncoder.encode("uuid", "UTF-8") +
                            "=" +
                            URLEncoder.encode(suuid, "UTF-8") +
                            "&" +
                            URLEncoder.encode("device_sn", "UTF-8") +
                            "=" +
                            URLEncoder.encode(gettoken, "UTF-8") +
                            "&" +
                            URLEncoder.encode("device", "UTF-8") +
                            "=" +
                            URLEncoder.encode(Build.DEVICE, "UTF-8") +
                            "&" +
                            URLEncoder.encode("model", "UTF-8") +
                            "=" +
                            URLEncoder.encode(Build.MODEL, "UTF-8") +
                            "&" +
                            URLEncoder.encode("version", "UTF-8") +
                            "=" +
                            URLEncoder.encode(Integer.toString(Build.VERSION.SDK_INT), "UTF-8");

                    Log.d("stringBuffer",stringBuffer);

//                    AsynNetUtils.post(string, stringBuffer, new AsynNetUtils.Callback() {
//                        @Override
//                        public void onResponse(String response) {
//                            parseDiffJson(response);
//                        }
//                    });
                    new Thread(new NetRunnable(CleryHandler,1,stringBuffer)).start();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        };

        final String Action = "FilterString";
        IntentFilter filter = new IntentFilter(Action);
        context.registerReceiver(receiver, filter);
    }

    private void parseDiffJson(String json) {

        Log.d("--valid",json);
        try{
            JSONObject jsonObject=new JSONObject(json);
            VALIDCODE=jsonObject.getString("validCode");

            if(VALIDCODE!=null){
                context.unregisterReceiver(receiver);
            }

            saveData.saveStringData("Code","validCode", VALIDCODE);

            MyThread myThread=new MyThread(handler);
            myThread.setmHandlerMessage(0x123);
            myThread.start();

        }catch (Exception e){e.printStackTrace();}

    }
    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123)
            {
                try {
                    QrcodeFragment.QRbitmap= encodeAsBitmap.encodeAsBitmap(VALIDCODE, BarcodeFormat.QR_CODE,(int) (double) ScreenWH.getScreenWidth(),(int) (double) Math.round((ScreenWH.getScreenHidth() / 10)*3));
                    BarcodeFtagment.BRbitmap= encodeAsBitmap.encodeAsBitmap(VALIDCODE, BarcodeFormat.CODE_39,(int) (double) ScreenWH.getScreenWidth(),(int) (double) Math.round((ScreenWH.getScreenHidth() / 10)));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
