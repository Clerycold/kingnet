package com.kingnet.GoogleMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Data.GetCPCData;
import com.kingnet.R;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/12/6.
 */

public class GoogleMap_activity extends FragmentActivity implements OnMapReadyCallback{

    private Bundle bundle;

    private Titledesign titledesign;
    private TextView maptxtstation;
    private TextView maptxtaddress;

    private String station_name;
    private String address;
    private String latitude;
    private String longitude;
    private StringBuffer details_title;
    private StringBuffer details_content;

    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap_activity);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        bundle =this.getIntent().getExtras();
        if(bundle!=null){
            station_name=bundle.getString("station_name")+"站";
            address=bundle.getString("address");
            String type = bundle.getString("type");
            latitude=bundle.getString("latitude");
            longitude=bundle.getString("longitude");
            String km = bundle.getString("km");
            StringBuffer open_start = new StringBuffer(bundle.getString("open_start")).delete(5, 8);
            StringBuffer open_due = new StringBuffer(bundle.getString("open_due")).delete(5, 8);
            if(open_due.length()>5){
                int i = open_due.length();
                open_due.delete(0,i);
                open_due.append("24:00");
            }

            details_title=new StringBuffer();
            details_title.append(type);
            details_title.append(" ");
            details_title.append("中國石油-");
            details_title.append(station_name);
            details_content=new StringBuffer();
            details_content.append(km);
            details_content.append("公里 ");
            details_content.append("營業時間：");
            details_content.append(open_start);
            details_content.append("~");
            details_content.append(open_due);
        }


        titledesign.setTextView(station_name);
        maptxtstation.setText(station_name);
        maptxtaddress.setText(address);

        if (savedInstanceState == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }

    private void initView(){
        titledesign = (Titledesign)findViewById(R.id.titledesign);
        maptxtstation=(TextView)findViewById(R.id.maptxtstation);
        maptxtaddress=(TextView)findViewById(R.id.maptxtaddress);
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams maptxtaddress_lay=new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)Height/15);
        maptxtaddress_lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        maptxtaddress.setLayoutParams(maptxtaddress_lay);
        maptxtaddress.setPadding((int)(double)Width/20,0,0,0);

        RelativeLayout.LayoutParams maptxtstation_lay=new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)Height/15);
        maptxtstation_lay.addRule(RelativeLayout.ABOVE,maptxtaddress.getId());
        maptxtstation.setLayoutParams(maptxtstation_lay);
        maptxtstation.setPadding((int)(double)Width/20,0,0,0);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng sydney = new LatLng(GetCPCData.getLatitude(),GetCPCData.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(sydney).title("現在位置"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,13));
//        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
//        googleMap.setInfoWindowAdapter(this);

        //標示目標位置
        MarkerOptions markerOpt2 = new MarkerOptions();
        markerOpt2.position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
        markerOpt2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        markerOpt2.title(details_title.toString());
        markerOpt2.snippet(details_content.toString());
        googleMap.addMarker(markerOpt2).showInfoWindow();

    }
}


