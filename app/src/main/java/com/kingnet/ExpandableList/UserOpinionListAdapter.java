package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;
import com.kingnet.UIpattern.TextLiner;

import java.util.List;

/**
 * Created by clery on 2016/11/10.
 */

public class UserOpinionListAdapter extends BaseAdapter {

    private List<String> titlecontent;
    private List<String> replytime;
    private LayoutInflater infalInflater;

    public UserOpinionListAdapter(Context context,
                                  List<String> titlecontent,
                                  List<String> replytime){

        this.titlecontent=titlecontent;
        this.replytime=replytime;
        this.infalInflater=(LayoutInflater)context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return titlecontent.size();
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

        String title=titlecontent.get(position);
        String time=replytime.get(position);

        ViewHolder holder;

        if (convertView == null) {
            convertView=infalInflater.inflate(R.layout.useropinionlist_item,null);
            holder=new ViewHolder();
            holder.relativeLayout=(RelativeLayout)convertView.findViewById(R.id.useropinionrelat);
            holder.textLiner=(TextLiner)convertView.findViewById(R.id.txtLiner);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        ListView.LayoutParams opin_rel_lay=new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.relativeLayout.setLayoutParams(opin_rel_lay);

        holder.textLiner.setTxtTitleText(title);
        holder.textLiner.setTxtTimeText(time);


        return convertView;
    }
    static class ViewHolder{
        RelativeLayout relativeLayout;
        TextLiner textLiner;
    }
}
