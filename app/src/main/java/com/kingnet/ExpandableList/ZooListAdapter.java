package com.kingnet.ExpandableList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.ActivityZoo.model.ZooDataModel;
import com.kingnet.BestBitmap.LruCacheUtil;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

import java.util.List;

/**
 * Created by clery on 2016/12/12.
 */

public class ZooListAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private Context context;
    private List<ZooDataModel> zooDataList;
    private LayoutInflater infalInflater;
    private LruCacheUtil lruCacheUtil;
    private ListView listView;

    public static boolean mBusy=false; //滚动中

    private int mStart, mEnd;//滑动的起始位置
    public static String[] urls; //用来保存当前获取到的所有图片的Url地址
    private boolean mFirstIn;

    String iconUrl;


    public ZooListAdapter(Context context, List<ZooDataModel> zooDataList, ListView listView) {
        this.context = context;
        this.zooDataList = zooDataList;
        infalInflater = (LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listView = listView;
        lruCacheUtil = new LruCacheUtil(listView);

        mFirstIn = true;

        this.listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        if(zooDataList!=null){
            urls = new String[zooDataList.size()];
            for (int i = 0,j=zooDataList.size(); i < j; i++) {
                urls[i] = zooDataList.get(i).getZooImgUrl();
            }
        }
        return (zooDataList != null ? zooDataList.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return zooDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.listzoo_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.img);
            holder.textView = (TextView) convertView.findViewById(R.id.texttitle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int Width = ScreenWH.getScreenWidth();

        RelativeLayout.LayoutParams img_lay = new RelativeLayout.LayoutParams((int) (double) Width / 5, (int) (double) Width / 5);
        img_lay.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        holder.imageView.setLayoutParams(img_lay);

        iconUrl = zooDataList.get(position).getZooImgUrl();
        //当前位置的ImageView与图片的URL绑定
        holder.imageView.setTag(iconUrl);

        RelativeLayout.LayoutParams txt_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt_lay.addRule(RelativeLayout.RIGHT_OF, holder.imageView.getId());
        txt_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        holder.textView.setLayoutParams(txt_lay);

        if(!mBusy){
            lruCacheUtil.showImageByAsyncTask(holder.imageView, iconUrl);
        }else{
            holder.imageView.setImageBitmap(null);
        }
        holder.textView.setText(zooDataList.get(position).getZooTitle());


        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        //SCROLL_STATE_TOUCH_SCROLL 手指在屏幕上滑动
        // SCROLL_STATE_FLING 手指离开屏幕后，惯性滑动
        // SCROLL_STATE_IDLE 滑动后静止

        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                mBusy = false;
                lruCacheUtil.loadImages(mStart, mEnd);
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                mBusy = true;
                lruCacheUtil.cancelAllTask();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:

                mBusy = true;
                lruCacheUtil.cancelAllTask();
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        Log.d("---mstart",Integer.toString(mStart));
        Log.d("---mEnd",Integer.toString(mEnd));
        Log.d("---totalItemCount",Integer.toString(totalItemCount));
//
        //如果是第一次进入 且可见item大于0 预加载
        if (mFirstIn && visibleItemCount > 0) {
            lruCacheUtil.loadImages(mStart, mEnd);
            mFirstIn = false;
        }
    }
    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
