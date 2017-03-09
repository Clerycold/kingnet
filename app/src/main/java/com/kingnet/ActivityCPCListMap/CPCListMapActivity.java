package com.kingnet.ActivityCPCListMap;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.SetList;
import com.kingnet.Data.CPCListData;
import com.kingnet.Data.GetCPCData;
import com.kingnet.ExpandableList.CPCListAdapter;
import com.kingnet.GoogleMap.GoogleMap_activity;
import com.kingnet.R;
import com.kingnet.UIpattern.Titledesign;

import java.util.List;

/**
 * Created by clery on 2016/12/5.
 */

public class CPCListMapActivity extends Activity{

    private int Width;
    private int Height;

    private Titledesign titledesign;
    private ListView cpclistview;
    private CPCListAdapter cpcListAdapter;
    private List<CPCListData> cpcListDataList;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.cpclistmap_activity);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        titledesign.setTextView(getResources().getString(R.string.titlenearcpc));

        cpcListDataList= GetCPCData.getListCPCListData();
        if(cpcListDataList!=null){
            cpcListAdapter=new CPCListAdapter(getApplicationContext(),cpcListDataList);
            cpclistview.setAdapter(cpcListAdapter);
        }else{
            TextView txtbg=(TextView)findViewById(R.id.txtbg);
            cpclistview.setEmptyView(txtbg);

            RelativeLayout.LayoutParams txtbg_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            txtbg_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
            txtbg_lay.addRule(RelativeLayout.CENTER_VERTICAL);
            txtbg.setLayoutParams(txtbg_lay);
            txtbg.setBackgroundColor(getResources().getColor(R.color.colorwhite));
            txtbg.setText("定位失敗,稍後將重新幫您定位");
        }

        SetList.setListViewHeightBasedOnChildren(cpclistview);
        setlistlistener(cpclistview);
    }

    private void initView(){

        titledesign=(Titledesign)findViewById(R.id.titledesign);
        scrollView=(ScrollView)findViewById(R.id.infoscroll);
        cpclistview=(ListView)findViewById(R.id.cpclistview);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_scroll_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)(Height/10)*9);
        r_scroll_lay.setMargins(0,(int) (double)(Height/10),0,0);
        scrollView.setLayoutParams(r_scroll_lay);

    }

    private void setlistlistener(ListView listView){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(CPCListMapActivity.this, GoogleMap_activity.class);

                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle = new Bundle();
                bundle.putString("station_name",cpcListDataList.get(position).getStation_name());
                bundle.putString("type",cpcListDataList.get(position).getType());
                bundle.putString("address",cpcListDataList.get(position).getAddress());
                bundle.putString("km",cpcListDataList.get(position).getKm());
                bundle.putString("longitude",cpcListDataList.get(position).getLongitude());
                bundle.putString("latitude",cpcListDataList.get(position).getLatitude());
                bundle.putString("open_start",cpcListDataList.get(position).getOpen_start());
                bundle.putString("open_due",cpcListDataList.get(position).getOpen_due());

                //將Bundle物件assign給intent
                intent.putExtras(bundle);
                //切換Activity
                startActivity(intent);
            }
        });
    }
}
