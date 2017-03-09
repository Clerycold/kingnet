package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.PublicUtilitesMoreDetail;
import com.kingnet.R;

import java.util.List;

/**
 * Created by clery on 2016/11/18.
 */

public class PublicUtilitiesAdapter extends BaseAdapter {

    private List<Bitmap> listicon;
    private List<StringBuffer> listtitle;
    private List<Boolean> listneed;
    private Context context;
    private LayoutInflater mInflater;

    public PublicUtilitiesAdapter(Context context, List<Bitmap>listicon, List<StringBuffer>listtitle,List<Boolean> listneed){

        this.context=context;
        this.listicon=listicon;
        this.listtitle=listtitle;
        this.listneed=listneed;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listtitle.size();
    }

    @Override
    public Object getItem(int position) {
        return listtitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        int Width= ScreenWH.getScreenWidth();
        int Height=ScreenWH.getScreenHidth();
        if(convertView == null){
            convertView=mInflater.inflate(R.layout.public_listview,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.publiclistimg);
            viewHolder.textView=(TextView) convertView.findViewById(R.id.publiclisttext);
            if(listneed.get(position)){
                viewHolder.textView1=(TextView) convertView.findViewById(R.id.publiclistneed);
            }
            viewHolder.imageView2=(ImageView) convertView.findViewById(R.id.publiclistmore);
            convertView.setTag(viewHolder);

        }else{

            viewHolder=(ViewHolder)convertView.getTag();
        }

        RelativeLayout.LayoutParams r_image_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*1.5), (int) (double) ((Width/10)*1.5));
        r_image_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        r_image_lay.setMargins((int) (double) (Width/20), 0, (int) (double) (Width/20), 0);
        viewHolder.imageView.setLayoutParams(r_image_lay);

        RelativeLayout.LayoutParams r_text_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (double) ((Height/10)*1.5));
        r_text_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        r_text_lay.addRule(RelativeLayout.RIGHT_OF,viewHolder.imageView.getId());
        viewHolder.textView.setLayoutParams(r_text_lay);

        if(listneed.get(position)){
            RelativeLayout.LayoutParams r_need_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (double) ((Height/10)*0.3));
            r_need_lay.addRule(RelativeLayout.RIGHT_OF,viewHolder.textView.getId());
            r_need_lay.setMargins(0,(int) (double) (Width/20),0,0);
            viewHolder.textView1.setLayoutParams(r_need_lay);
            viewHolder.textView1.setText(R.string.needReservation);
        }

        RelativeLayout.LayoutParams r_more_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)), (int) (double) ((Width/10)));
        r_more_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        viewHolder.imageView2.setLayoutParams(r_more_lay);
        setpublicmoredetail(viewHolder.imageView2);

        viewHolder.textView.setText(listtitle.get(position).toString());

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        ImageView imageView2;
    }

    private void setpublicmoredetail(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentActivity.IntentActivity(context,PublicUtilitesMoreDetail.class);
            }
        });
    }

}
