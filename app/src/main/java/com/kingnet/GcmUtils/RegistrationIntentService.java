package com.kingnet.GcmUtils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.kingnet.R;

import java.io.IOException;

/**
 * Created by clery on 2016/11/25.
 */

public class RegistrationIntentService extends IntentService {
    public RegistrationIntentService(){
        super("RegistrationIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(getString(R.string.gcm_sender_id),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Bundle message = new Bundle();
            message.putString("token", token);
            Intent intent1 = new Intent("FilterString");
            intent1.putExtras(message);
            sendBroadcast(intent1);

            Log.d("--token",token);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}