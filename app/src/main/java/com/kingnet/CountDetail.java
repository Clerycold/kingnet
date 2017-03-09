package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

import java.lang.reflect.Array;

/**
 * Created by clery on 2016/11/16.
 */

public class CountDetail extends Activity{

    private Titledesign titledesign;

    ScrollView scrollView;
    RelativeLayout relativeLayout;

    private TextView countdetail_title;
    private TextView countdetail_deal;


    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.countdetail);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        titledesign.setTextView(getResources().getString(R.string.titlecountdetail));
        countdetail_title.setText("我的點數"+"300"+"\n"+"今網企業大樓(2H1)220號2F-1");
        countdetail_deal.setText("最近三個月交易紀錄");
    }
    private void initView() {

        titledesign = (Titledesign) findViewById(R.id.titledesign);

        scrollView=(ScrollView)findViewById(R.id.countdetail_scroll);
        relativeLayout=(RelativeLayout)findViewById(R.id.countdetail_scroll_ral);

        countdetail_title=(TextView)findViewById(R.id.countdetail_title);
        countdetail_deal=(TextView)findViewById(R.id.countdetail_deal);
    }

    private void xmlArrange() {

        RelativeLayout.LayoutParams r_scroll_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) (Height / 10) * 8);
        r_scroll_lay.setMargins(0, (int) (double) (Height / 10), 0, 0);
        scrollView.setLayoutParams(r_scroll_lay);

        ScrollView.LayoutParams r_scrollrelat_lay = new ScrollView.LayoutParams((int) (double) Width, (int) (double) (Height / 10) * 8);
        relativeLayout.setLayoutParams(r_scrollrelat_lay);

        RelativeLayout.LayoutParams r_countdetail_title_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) (Height / 10) * 2);
        countdetail_title.setLayoutParams(r_countdetail_title_lay);

        RelativeLayout.LayoutParams r_countdetail_deal_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) (Height / 20) );
        r_countdetail_deal_lay.addRule(RelativeLayout.BELOW,countdetail_title.getId());
        r_countdetail_deal_lay.setMargins(25,25,25,25);
        countdetail_deal.setLayoutParams(r_countdetail_deal_lay);
    }
}
