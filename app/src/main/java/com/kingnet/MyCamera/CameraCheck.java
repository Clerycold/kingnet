package com.kingnet.MyCamera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.widget.Toast;

/**
 * Created by clery on 2016/11/23.
 */

class CameraCheck {

    //判斷有無照相機功能

    static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            Toast toast = Toast.makeText(context,
                    "無相機功能", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    //得到相機編號
    static int getDefaultCameraId(Context context) {
        int defaultId = -1;
        // Find the total number of cameras available
        int mNumberOfCameras = Camera.getNumberOfCameras();
        // Find the ID of the default camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                defaultId = i;
            }
        }
        if (-1 == defaultId) {
            if (mNumberOfCameras > 0) {
                // 如果没有后向摄像头
                defaultId = 0;
            } else {
                // 没有摄像头
                Toast.makeText(context, "沒有鏡頭",
                        Toast.LENGTH_LONG).show();
            }
        }
        return defaultId;
    }

    //判斷是否可寫入
    static Boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
