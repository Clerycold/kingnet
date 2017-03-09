package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MoreinfoListAdapter extends BaseAdapter{

    private Context context;
    private List<String> list;
    private LayoutInflater infalInflater;

    public MoreinfoListAdapter(Context context,List<String> list){

        this.context=context;
        this.list=list;
        infalInflater=(LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        String headerTitle = list.get(position); // 取得標題

        if (convertView == null) {

            convertView=infalInflater.inflate(R.layout.moreinfolistview,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView)convertView.findViewById(R.id.moreinfogroup);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        int Width= ScreenWH.getScreenWidth();
        int Height=ScreenWH.getScreenHidth();

        RelativeLayout.LayoutParams open_img_lay=new RelativeLayout.LayoutParams((int) (double) Width,(int) (double) ((Height/10)*0.8));
        open_img_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        open_img_lay.setMargins(25,0,25,0);
        viewHolder.textView.setLayoutParams(open_img_lay);

        viewHolder.textView.setTypeface(null, Typeface.BOLD);
        viewHolder.textView.setText(headerTitle);

        return convertView;
    }

    static class ViewHolder{
        TextView textView;
    }
}
