package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

import java.util.List;

/**
 * Created by clery on 2016/11/16.
 */

public class UserInfo2ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> storyname;
    private List<Integer> count;

    private LayoutInflater mInflater;

    public UserInfo2ListViewAdapter(Context context,List<String> storyname,List<Integer> count){

        this.context=context;
        this.storyname=storyname;
        this.count=count;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return storyname.size();
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

        int Width= ScreenWH.getScreenWidth();
        int Height=ScreenWH.getScreenHidth();

        String story_content=storyname.get(position);
        int count_content=count.get(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.userinfo2_list, null);
            viewHolder=new ViewHolder();
            viewHolder.story = (TextView) convertView.findViewById(R.id.list_story);
            viewHolder.count = (TextView) convertView.findViewById(R.id.list_count);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }


        LinearLayout.LayoutParams r_story_lay = new LinearLayout.LayoutParams((int) (double) (Width/10)*8, (int) (double) Math.round((Height / 20)));
        r_story_lay.setMargins(25, 25, 0, 0);
        viewHolder.story.setLayoutParams(r_story_lay);

        LinearLayout.LayoutParams r_count_lay = new LinearLayout.LayoutParams((int) (double) (Width/10)*8, (int) (double) Math.round((Height / 20)));
        r_count_lay.setMargins(25, 0, 0, 25);
        viewHolder.count.setLayoutParams(r_count_lay);

        viewHolder.story.setText(story_content);
        viewHolder.count.setText("目前公設點數"+Integer.toString(count_content));

        return convertView;
    }

    static class ViewHolder{
        TextView story;
        TextView count;
    }
}
