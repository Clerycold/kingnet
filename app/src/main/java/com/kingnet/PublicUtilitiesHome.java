package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.ExpandableList.PublicUtilitiesAdapter;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/11/18.
 */

public class PublicUtilitiesHome extends Activity {

    private Titledesign titledesign;

    private ImageButton imageButton;
    private TextView textViewtitle;
    private ListView publicListView;
    private PublicUtilitiesAdapter publicUtilitiesAdapter;
    private List<Bitmap> listicon;
    private List<StringBuffer> listSringTitle;
    private List<Boolean> listneed;

    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //在啟動前更改回主要的AppTheme
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.publicutiltieshome);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();
        prepareListData();

        titledesign.setTextView(getResources().getString(R.string.titlepublicUtilities));
        textViewtitle.setText("請選擇要預約公設的時間");

        publicUtilitiesAdapter = new PublicUtilitiesAdapter(this, listicon, listSringTitle,listneed);
        publicListView.setAdapter(publicUtilitiesAdapter);
    }

    private void initView() {

        titledesign = (Titledesign) findViewById(R.id.titledesign);
        imageButton = (ImageButton) findViewById(R.id.publicimgbtn);
        textViewtitle = (TextView) findViewById(R.id.publictitletext);
        publicListView = (ListView) findViewById(R.id.publicListView);
    }

    private void xmlArrange() {

        RelativeLayout.LayoutParams r_btnpackage_lay = new RelativeLayout.LayoutParams((int) (double) Width / 4, (int) (double) Height / 10);
        r_btnpackage_lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        r_btnpackage_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageButton.setLayoutParams(r_btnpackage_lay);

        RelativeLayout.LayoutParams r_titletext_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Height / 10);
        r_titletext_lay.addRule(RelativeLayout.BELOW, titledesign.getId());
        textViewtitle.setLayoutParams(r_titletext_lay);

        RelativeLayout.LayoutParams r_publicListView_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) (Height / 10)*7);
        r_publicListView_lay.addRule(RelativeLayout.BELOW, textViewtitle.getId());
        publicListView.setLayoutParams(r_publicListView_lay);
    }

    private void prepareListData() {
        listSringTitle = new ArrayList<StringBuffer>();
        listicon = new ArrayList<Bitmap>();
        listneed = new ArrayList<Boolean>();

        StringBuffer s=new StringBuffer("有 氧 健 身 房"+ "\n"+"周一至周六"+"\n"+"10:00~22:00");
        listSringTitle.add(s);
        StringBuffer s1=new StringBuffer("多 功 能 館"+ "\n"+"周一至周日"+"\n"+"08:00~21:00");
        listSringTitle.add(s1);
        StringBuffer s2=new StringBuffer("獨 立 大 型"+ "\n"+"周一至周日"+"\n"+"17:00~22:00");
        listSringTitle.add(s2);
        StringBuffer s3=new StringBuffer("籃 球 場"+ "\n"+"周日、五、六"+"\n"+"18:00~22:00");
        listSringTitle.add(s3);
        StringBuffer s4=new StringBuffer("會 議 室"+ "\n"+"周日"+"\n"+"22:00~00:00");
        listSringTitle.add(s4);

        listneed.add(true);
        listneed.add(false);
        listneed.add(false);
        listneed.add(true);
        listneed.add(true);
    }
}
