package com.kingnet.BestBitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;


/**
 * Created by Administrator on 2016/11/3.
 */
public class BitmapUtils {

    private static LruCacheUtil lruCacheUtil;
    //    1. 使用inJustDecodeBounds，读bitmap的长和宽。
//    2. 根据bitmap的长款和目标缩略图的长和宽，计算出inSampleSize的大小。
//    3. 使用inSampleSize，载入一个大一点的缩略图A
//    4. 使用createScaseBitmap，将缩略图A，生成我们需要的缩略图B。
//    5. 回收缩略图A。

    //計算該使用的大小
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;

        int inSampleSize = 1;
        //如果圖片大小大於設定的大小時
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth,
                                            int dstHeight) {
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
    }

    // 从Resources中加载图片
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {
        if(lruCacheUtil==null){
            lruCacheUtil=new LruCacheUtil();
        }
        Bitmap bitmap = lruCacheUtil.getBitmapFromCache(Integer.toString(resId));
        if(bitmap==null){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true; //解析图片的长度和宽度，不载入图片。
            BitmapFactory.decodeResource(res, resId, options); // 读取图片长款
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight); // 计算inSampleSize

            options.inJustDecodeBounds = false;
            if(options.inSampleSize==1){
               bitmap=BitmapFactory.decodeResource(res, resId);
            }else{
                Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入一个稍大的缩略图
                bitmap =createScaleBitmap(src, reqWidth, reqHeight);
                lruCacheUtil.addBitmapToCache(Integer.toString(resId),bitmap);
            }
        }

        return bitmap;
    }

    // 从sd卡上加载图片
    public static Bitmap decodeSampledBitmapFromFd(String pathName,
                                                    int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight);
    }

    public static Bitmap decodeSampledBitmapData(byte[] data,
                                                   int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data,0,data.length, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeByteArray(data,0,data.length, options);

        return createScaleBitmap(src, reqWidth, reqHeight);
    }

    public static Drawable decodeSampledDrawableFromResource(Resources res,
                                                           int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //解析图片的长度和宽度，不载入图片。
        BitmapFactory.decodeResource(res, resId, options); // 读取图片长款
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight); // 计算inSampleSize

        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Log.d("--options.inSampleSize",Integer.toString(options.inSampleSize));
        Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入一个稍大的缩略图
        return new BitmapDrawable(res,createScaleBitmap(src, reqWidth, reqHeight));
    }
}
