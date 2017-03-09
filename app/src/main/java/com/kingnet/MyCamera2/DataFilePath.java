package com.kingnet.MyCamera2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clery on 2016/11/21.
 */

public class DataFilePath {


    public static String TEMPPATH;
    public static  File mediaStorageDir;

    public static File getDataFilePath(Context context){
        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Kingnet_picture");

        if (!mediaStorageDir.exists()) {
            Toast toast = Toast.makeText(context,
                    "建立照片資料夾Kingnet_picture", Toast.LENGTH_LONG);
            toast.show();
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        String TIMESTAMP= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + TIMESTAMP + ".jpg");

        TEMPPATH=mediaStorageDir.getPath() + File.separator +
                "IMG_" + TIMESTAMP + ".jpg";
        return mediaFile;
    }
}
