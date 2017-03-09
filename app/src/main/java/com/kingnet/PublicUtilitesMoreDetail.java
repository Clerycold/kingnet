package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by clery on 2016/11/18.
 */

public class PublicUtilitesMoreDetail extends Activity{

    private DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    private float Width = Metrics.widthPixels;
    private float Height = Metrics.heightPixels;

    private ImageView publicicon;
    private TextView publiccostcount;
    private Button publicneed;
    private TextView publicopentime;
    private TextView publiclimtpeople;
    private TextView publicknow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.publicmoredetail);

        Resources resources = PublicUtilitesMoreDetail.this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);

        Height -=height;

        initView();
        xmlArrange();

    }
    private void initView(){

        publicicon =(ImageView)findViewById(R.id.publicicon);
        publiccostcount=(TextView)findViewById(R.id.publiccostcount);
        publicneed=(Button)findViewById(R.id.publicneed);
        publicopentime=(TextView)findViewById(R.id.publicopentime);
        publiclimtpeople=(TextView)findViewById(R.id.publiclimtpeople);
        publicknow=(TextView)findViewById(R.id.publicknow);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_publicicon_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)*3));
        r_publicicon_lay.setMargins(0,(int) (double) ((Height/10)),0,0);
        r_publicicon_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        publicicon.setLayoutParams(r_publicicon_lay);

        RelativeLayout.LayoutParams r_publiccostcount_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)));
        r_publiccostcount_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_publiccostcount_lay.addRule(RelativeLayout.BELOW,publicicon.getId());
        publiccostcount.setLayoutParams(r_publiccostcount_lay);

        RelativeLayout.LayoutParams r_publicneed_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)));
        r_publicneed_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_publicneed_lay.addRule(RelativeLayout.BELOW,publiccostcount.getId());
        publicneed.setLayoutParams(r_publicneed_lay);

        RelativeLayout.LayoutParams r_publicopentime_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)*1.5));
        r_publicopentime_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_publicopentime_lay.addRule(RelativeLayout.BELOW,publicneed.getId());
        publicopentime.setLayoutParams(r_publicopentime_lay);

        RelativeLayout.LayoutParams r_publiclimtpeople_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)));
        r_publiclimtpeople_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_publiclimtpeople_lay.addRule(RelativeLayout.BELOW,publicopentime.getId());
        publiclimtpeople.setLayoutParams(r_publiclimtpeople_lay);

        RelativeLayout.LayoutParams r_publicknow_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*9), (int) (double) ((Height/10)));
        r_publicknow_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_publicknow_lay.addRule(RelativeLayout.BELOW,publiclimtpeople.getId());
        publicknow.setLayoutParams(r_publicknow_lay);

    }
}
