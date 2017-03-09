package com.kingnet.ExpandableList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.TextUtils;
import com.kingnet.Data.CPCListData;
import com.kingnet.R;

import java.util.List;

/**
 * Created by clery on 2016/12/5.
 */

public class CPCListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater infalInflater;

    private List<CPCListData> cpcListDatas;

    int Width;
    int Height;
    int padding;

    public CPCListAdapter(Context context,List<CPCListData> cpcListDatas){
        this.context=context;
        this.cpcListDatas=cpcListDatas;
        infalInflater=(LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getScreenHidth();
        padding=(int) (double) ((Height/10)*0.2);
    }

    @Override
    public int getCount() {
        return cpcListDatas.size();
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

        final ViewHolder holder;
        String station_name =cpcListDatas.get(position).getStation_name();
        String type =cpcListDatas.get(position).getType();
        String address=cpcListDatas.get(position).getAddress();
        StringBuffer km =new StringBuffer("距離 ");
        km.append(cpcListDatas.get(position).getKm());
        km.append("km");
        if(convertView==null){
            convertView=infalInflater.inflate(R.layout.cpclistadapter_item,null);
            holder=new ViewHolder();
            holder.txtstation=(TextView)convertView.findViewById(R.id.txtstation);
            holder.txttype=(TextView)convertView.findViewById(R.id.txttype);
            holder.txtaddress=(TextView)convertView.findViewById(R.id.txtadddress);
            holder.txtdistance=(TextView)convertView.findViewById(R.id.txtdistance);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        RelativeLayout.LayoutParams txtstation_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        txtstation_lay.setMargins(padding,padding,0,0);
        holder.txtstation.setLayoutParams(txtstation_lay);

        RelativeLayout.LayoutParams txttype_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        txttype_lay.addRule(RelativeLayout.RIGHT_OF,holder.txtstation.getId());
        txttype_lay.setMargins(padding/2,padding,0,0);
        holder.txttype.setLayoutParams(txttype_lay);
        if(type.equals("加盟站")){
            holder.txttype.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.colorBluewater));
        }else {
            holder.txttype.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.colorHometext));
        }

        RelativeLayout.LayoutParams txtaddress_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        txtaddress_lay.addRule(RelativeLayout.BELOW,holder.txtstation.getId());
        txtaddress_lay.setMargins(padding,0,0,padding);
        holder.txtaddress.setLayoutParams(txtaddress_lay);

        RelativeLayout.LayoutParams txtdistance_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        txtdistance_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        txtdistance_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        txtdistance_lay.setMargins(0,padding,padding,padding);
        holder.txtdistance.setLayoutParams(txtdistance_lay);

        holder.txtstation.setText(station_name);
        holder.txttype.setText(type);
        holder.txtaddress.setText(address);
        holder.txtdistance.setText(km);


        return convertView;
    }

    static class ViewHolder{
        TextView txtstation;
        TextView txttype;
        TextView txtaddress;
        TextView txtdistance;
    }
}
