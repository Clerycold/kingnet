package com.kingnet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.kingnet.ExpandableList.MoreinfoListAdapter;
import com.kingnet.MoreInfo.AboutKingnet;
import com.kingnet.MoreInfo.LifeGoodBuy;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MoreInfoCommon extends Activity{

    Titledesign titledesign;
    BottomButton bottomButton;

    ScrollView scrollView;
    RelativeLayout relativeLayout;
    ImageView imageView;

    private ListView listView;
    private List<String> list;
    private MoreinfoListAdapter listAdapter;

    ImageButton imageButton;

    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;

    Context context;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.moreinfocommon);

        Resources resources = MoreInfoCommon.this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);

        Height -=height;

        initView();
        xmlArrange();
        prepareListData();
        setlistlistener();

        titledesign.setTextView(getResources().getString(R.string.titlemoreinfo));

        listAdapter = new MoreinfoListAdapter(this,list);
        listView.setAdapter(listAdapter);


    }
    private void initView(){
        titledesign=(Titledesign)findViewById(R.id.titledesign);
        bottomButton=(BottomButton)findViewById(R.id.buttombutton);

        scrollView=(ScrollView)findViewById(R.id.infoscroll);
        relativeLayout=(RelativeLayout)findViewById(R.id.infoscrollrelat);
        imageView=(ImageView)findViewById(R.id.infoimage);

        listView = (ListView)findViewById(R.id.list_view);

        imageButton=(ImageButton)findViewById(R.id.infoadbtn);
    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_scroll_lay =new RelativeLayout.LayoutParams((int) (double)Width, (int) (double)(Height/10)*8);
        r_scroll_lay.setMargins(0,(int) (double)(Height/10),0,0);
        scrollView.setLayoutParams(r_scroll_lay);

        ScrollView.LayoutParams r_scrollrelat_lay = new ScrollView.LayoutParams((int) (double)Width, (int) (double)(Height/10)*8);
        relativeLayout.setLayoutParams(r_scrollrelat_lay);

        RelativeLayout.LayoutParams r_image_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) ((Height/10)*2.5));
        r_image_lay.setMargins((int) (double)(Height/40),(int) (double)(Height/40),(int) (double)(Height/40),0);
        imageView.setLayoutParams(r_image_lay);

        RelativeLayout.LayoutParams r_listView_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 10) * 5));
        r_listView_lay.addRule(RelativeLayout.BELOW,imageView.getId());
        r_listView_lay.setMargins((int) (double) (Height / 40), 0, (int) (double) (Height / 40),0);
        listView.setLayoutParams(r_listView_lay);

        RelativeLayout.LayoutParams r_imagebutton_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 10) * 2.5));
        r_imagebutton_lay.addRule(RelativeLayout.BELOW,listView.getId());
        imageButton.setLayoutParams(r_imagebutton_lay);
    }

    private void prepareListData() {
        list =new ArrayList<String>();
        list.add(getResources().getString(R.string.moreinfogroup));
        list.add(getResources().getString(R.string.moreinfogroup1));
        list.add(getResources().getString(R.string.moreinfogroup2));
        list.add(getResources().getString(R.string.moreinfogroup3));
        list.add(getResources().getString(R.string.moreinfogroup4));
        list.add(getResources().getString(R.string.moreinfogroup5));
    }

    private void setlistlistener(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setintent(UserMessageHome.class);
                        BottomButton.ICONTYPE=1;
                        break;
                    case 1:
                        setintent(LifeGoodBuy.class);
                        break;
                    case 3:
                        setintent(AboutKingnet.class);
                        break;
                    case 4:
                        setintent(CommonProblem.class);
                        break;
                    case 5:
                        String sParam = "chk.kingnet.app";
                        Intent intent;

                        try
                        {
                            // Open app with Google Play app
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+sParam));
                            startActivity(intent);
                        }
                        catch (android.content.ActivityNotFoundException anfe)
                        {
                            // Open Google Play website
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + sParam));
                            startActivity(intent);
                        }
                        break;
                }

            }
        });
    }
    private void setintent(Class<?> cls){
        context = getApplicationContext();
        i = new Intent(context, cls);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
