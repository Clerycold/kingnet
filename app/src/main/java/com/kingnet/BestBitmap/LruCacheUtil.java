package com.kingnet.BestBitmap;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.ExpandableList.ZooListAdapter;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by clery on 2016/12/8.
 */

public class LruCacheUtil {//LRU缓存
    private LruCache<String, Bitmap> mCache;

    private ListView mListView;
    private Set<NewsAsyncTask> mTaskSet;

    public LruCacheUtil(){
        mTaskSet = new HashSet<>();
        //返回Java虚拟机将尝试使用的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //指定缓存大小
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //Bitmap的实际大小 注意单位与maxMemory一致
                return value.getByteCount();

                //也可以这样返回 结果是一样的
                //return value.getRowBytes()*value.getHeight();
            }
        };
    }


    public LruCacheUtil(ListView listView) {

        mListView=listView;
        mTaskSet = new HashSet<>();
        //返回Java虚拟机将尝试使用的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //指定缓存大小
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //Bitmap的实际大小 注意单位与maxMemory一致
                return value.getByteCount();

                //也可以这样返回 结果是一样的
                //return value.getRowBytes()*value.getHeight();
            }
        };
    }

    /**
     * 通过异步任务的方式加载数据
     *
     * @param iv  图片的控件
     * @param url 图片的URL
     */
    public void showImageByAsyncTask(ImageView iv, final String url) {
        //从缓存中取出图片
        Bitmap bitmap = getBitmapFromCache(url);
        //如果缓存中没有，则需要从网络中下载
        if (bitmap == null) {
            bitmap = getBitmapFromURL(url);
            iv.setImageBitmap(bitmap);
        } else {
            //如果缓存中有 直接设置
            iv.setImageBitmap(bitmap);
        }
    }

    /**
     * 将一个URL转换成bitmap对象
     *
     * @param urlStr 图片的URL
     * @return
     */
    public Bitmap getBitmapFromURL(String urlStr) {
        Bitmap bitmap;
        InputStream is = null;

        try {

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new DataInputStream(connection.getInputStream());

            bitmap=BitmapUtils.decodeSampledBitmapData(readBytes(is), ScreenWH.getScreenWidth()/5,ScreenWH.getScreenWidth()/5);
            connection.disconnect();
            return bitmap;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * inputstream轉byte[]
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    /*--------------------------------LruCaChe的实现-----------------------------------------*/

    /**
     * 将Bitmap存入缓存
     *
     * @param url    Bitmap对象的key
     * @param bitmap 对象的key
     */
    public void addBitmapToCache(String url, Bitmap bitmap) {
        //如果缓存中没有
        if (getBitmapFromCache(url) == null) {
            //保存到缓存中
            mCache.put(url, bitmap);
        }
    }

    /**
     * 加载从start到end的所有的Image
     *
     * @param start
     * @param end
     */
    public void loadImages(int start, int end) {
        String url;
        Bitmap bitmap;
        if(!ZooListAdapter.mBusy){
            for (int i = start; i < end; i++) {
                url = ZooListAdapter.urls[i];

                //从缓存中取出图片
                bitmap = getBitmapFromCache(url);
                //如果缓存中没有，则需要从网络中下载
                if (bitmap == null) {
                    NewsAsyncTask task = new NewsAsyncTask(url);
                    task.execute(url);
                    mTaskSet.add(task);
                } else {
                    //如果缓存中有 直接设置
                    ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                    if (imageView != null ) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }
    /**
     * 停止所有当前正在运行的任务
     */
    public void cancelAllTask() {
        if (mTaskSet != null) {
            for (NewsAsyncTask task : mTaskSet) {
                task.cancel(false);
            }
        }
    }

    /**
     * 从缓存中获取Bitmap对象
     *
     * @param url Bitmap对象的key
     * @return 缓存中Bitmap对象
     */
    public Bitmap getBitmapFromCache(String url) {
        return mCache.get(url);
    }

    /*--------------------------------LruCaChe的实现-----------------------------------------*/

    /**
     * 异步任务类
     */
    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private String url;

        public NewsAsyncTask(String url) {
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = getBitmapFromURL(params[0]);
            //保存到缓存中
            if (bitmap != null) {
                addBitmapToCache(params[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //只有当前的ImageView所对应的UR的图片是一致的,才会设置图片
            ImageView imageView = (ImageView) mListView.findViewWithTag(url);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            //移除所有Task
            mTaskSet.remove(this);
        }
    }
}