package com.kingnet.ActivityLifeConvenience;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.kingnet.ActivityLifeConvenience.presenter.LifeConveniencePreCompl;
import com.kingnet.ActivityLifeConvenience.view.LifeConvenienceView;
import com.kingnet.ActivityCPCListMap.CPCListMapActivity;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.SetList;
import com.kingnet.ExpandableList.LifeComvenienceListAdapter;
import com.kingnet.Data.GetCPCData;
import com.kingnet.R;
import com.kingnet.UIpattern.Titledesign;

import java.util.List;

/**
 * Created by clery on 2016/11/4.
 */

public class LifeConvenience extends Activity implements LifeConvenienceView,AdapterView.OnItemClickListener{

    private Titledesign titledesign;

    private ScrollView scrollView;

    private ListView listView;
    private int[] listIv;
    private List<String> listtitle;
    private List<String> listcontent;
    private LifeComvenienceListAdapter listAdapter;

    private int Width;
    private int Height;

    private GetCPCData getCPCData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.lifeconvenience);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(getApplicationContext());

        initView();
        xmlArrange();

        LifeConveniencePreCompl lifeConveniencePreCompl=new LifeConveniencePreCompl(getApplicationContext(),this);
        lifeConveniencePreCompl.CheckLifeItem();

        titledesign.setTextView(getResources().getString(R.string.titlelifeconvenience));

        listView.setOnItemClickListener(this);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    200);
            return;
        }
        if(getCPCData==null){
            getCPCData= new GetCPCData(this);
        }
    }

    private void initView(){

        titledesign=(Titledesign)findViewById(R.id.titledesign);
        scrollView=(ScrollView)findViewById(R.id.infoscroll);
        listView = (ListView)findViewById(R.id.list_view);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_scroll_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)(Height/10)*8);
        r_scroll_lay.setMargins(0,(int) (double)(Height/10),0,0);
        scrollView.setLayoutParams(r_scroll_lay);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //資料重載需要再去更新一次附近加油站 或者做個比較
        if(getCPCData!=null){
            getCPCData.getLms();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(getCPCData!=null){
            getCPCData.setStopLocationManager();
        }
    }

    @Override
    public void ShowLifeList(List<String> listtitle,List<String> listcontent) {

        listAdapter = new LifeComvenienceListAdapter(this,listIv,listtitle,listcontent);
        listView.setAdapter(listAdapter);
        SetList.setListViewHeightBasedOnChildren(listView);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                IntentActivity.IntentActivity(getApplicationContext(), CPCListMapActivity.class);
                break;
            case 1:
                String sParam = "http://www.taiwantaxi.com.tw/taiwantaxi/call_3.asp";
                Intent intent;
                try
                {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sParam));
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException anfe)
                {

                }
                break;
        }
    }
}

