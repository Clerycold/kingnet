package com.kingnet.Control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

/**
 * Created by clery on 2016/11/22.
 */

public class GalleryAddPic {
    private File f;

    /**
     * 更新手機圖片資料
     * @param context 發送時的廣播
     * @param file 圖片路徑
     */
    public GalleryAddPic(Context context, String file){
//      判断SDK版本是不是4.4或者高于4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            MediaScanner mediaScanner = new MediaScanner(context);
            mediaScanner.scanFile(new File(file), null);
        } else {
            if (f.isDirectory()) {    //// 判断e盘是否是目录
                f = new File(file);
                Uri contentUri = Uri.fromFile(f);
                Intent allmediaScanIntent = new Intent(Intent.ACTION_MEDIA_MOUNTED, contentUri);
                context.sendBroadcast(allmediaScanIntent);
            } else {
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(file)));
                //file = file+ File.separator +"IMG_" + TIMESTAMP + ".jpg"
                context.sendBroadcast(mediaScanIntent);
            }
        }
    }
}