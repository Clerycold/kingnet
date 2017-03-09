package com.kingnet.GcmUtils;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.kingnet.GcmUtils.RegistrationIntentService;

/**
 * Created by clery on 2016/11/25.
 */

public class MyInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}