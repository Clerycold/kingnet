package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

import java.util.List;

/**
 * Created by clery on 2016/11/4.
 */

public class LifeComvenienceListAdapter extends BaseAdapter {

    private Context context;
    private int[] listIv;
    private List<String> listtitle;
    private List<String> listcontent;
    private LayoutInflater infalInflater;

    public LifeComvenienceListAdapter(Context context,int[] listIv,
                                      List<String> listtitle,
                                      List<String> listcontent){
        this.context=context;
        this.listIv=listIv;
        this.listtitle=listtitle;
        this.listcontent=listcontent;
        this.infalInflater=(LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return listtitle.size();
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

        ViewHolder holder;
//        int icon=listIv[position];
        String title=listtitle.get(position);
        String content=listcontent.get(position);

        if (convertView == null) {
            convertView=infalInflater.inflate(R.layout.lifeconveniencelistview,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(R.id.lifelisticon);
            holder.title=(TextView)convertView.findViewById(R.id.lifelisttitle);
            holder.content=(TextView)convertView.findViewById(R.id.lifelistcontent);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        int padding=(int) (double) ((Height/10)*0.2);

        RelativeLayout.LayoutParams life_icon_lay=new RelativeLayout.LayoutParams((int) (double) (Width/10)*2,(int) (double) (Width/10)*2);
        life_icon_lay.setMargins(padding,padding,0,padding);
        holder.imageView.setLayoutParams(life_icon_lay);

        RelativeLayout.LayoutParams life_title_lay=new RelativeLayout.LayoutParams((int) (double) (Width/10)*8,(int) (double) ((Height/10)*0.4));
        life_title_lay.addRule(RelativeLayout.RIGHT_OF,holder.imageView.getId());
        life_title_lay.setMargins(padding,padding,padding,padding/2);
        holder.title.setLayoutParams(life_title_lay);

        RelativeLayout.LayoutParams life_content_lay=new RelativeLayout.LayoutParams((int) (double) (Width/10)*8, ViewGroup.LayoutParams.WRAP_CONTENT);
        life_content_lay.addRule(RelativeLayout.RIGHT_OF,holder.imageView.getId());
        life_content_lay.addRule(RelativeLayout.BELOW,holder.title.getId());
        life_content_lay.setMargins(padding,0,padding,padding);
        holder.content.setLayoutParams(life_content_lay);

        holder.title.setText(title);
        holder.title.setTypeface(null, Typeface.BOLD);
        holder.content.setText(content);

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView content;
    }
}
