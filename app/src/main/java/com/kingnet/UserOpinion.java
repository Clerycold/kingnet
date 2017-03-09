package com.kingnet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.ExpandableList.UserOpinionListAdapter;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clery on 2016/11/10.
 */

public class UserOpinion extends Activity{

    private Titledesign titledesign;

    private ImageButton imageButton;

    private ScrollView scrollView;
    private RelativeLayout relativeLayout;

    private ListView listView;
    private List<String> titlecontent;
    private List<String> replytime;
    UserOpinionListAdapter opinionListAdapter;

    private int Width;
    private int Height;

    private Boolean isRelply=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.useropinion);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();
        prepareListData();

        titledesign.setTextView(getResources().getString(R.string.titleuseropinion));

        opinionListAdapter = new UserOpinionListAdapter(this,titlecontent,replytime);
        listView.setAdapter(opinionListAdapter);

        setListViewHeightBasedOnChildren(listView);
        setlistlistener();

    }

    private void initView(){

        titledesign=(Titledesign)findViewById(R.id.titledesign);
        imageButton=(ImageButton)findViewById(R.id.useropinionbtn);
        scrollView=(ScrollView)findViewById(R.id.infoscroll);
        relativeLayout=(RelativeLayout)findViewById(R.id.infoscrollrelat);
        listView = (ListView)findViewById(R.id.useropinionlistview);
    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_btnpackage_lay =new RelativeLayout.LayoutParams((int)(double)Width/5,(int)(double)Height/10);
        r_btnpackage_lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        r_btnpackage_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageButton.setLayoutParams(r_btnpackage_lay);

        RelativeLayout.LayoutParams r_scroll_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)(Height/10)*8);
        r_scroll_lay.setMargins(0,(int) (double)(Height/10),0,0);
        scrollView.setLayoutParams(r_scroll_lay);

        ScrollView.LayoutParams r_scrollrelat_lay = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)(Height/10)*8);
        relativeLayout.setLayoutParams(r_scrollrelat_lay);

    }

    private void prepareListData(){
        titlecontent=new ArrayList<String>();
        replytime=new ArrayList<String>();

        titlecontent.add("????");
        titlecontent.add("hey what's up");

        replytime.add("最新回覆時間");
        replytime.add("最新回覆時間");
    }

    private void setlistlistener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String usercontent=titlecontent.get(position);

                if(position==0){
                    isRelply=true;
                }else{
                    isRelply=false;
                }

                Context context = getApplicationContext();
                Intent i = new Intent(context, UserBackOpinion.class);

                Bundle bundle = new Bundle();
                bundle.putBoolean("isReply",isRelply);
                bundle.putString("usercontent",usercontent);//傳遞String

                //將Bundle物件傳給intent
                i.putExtras(bundle);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentActivity.IntentActivity(getApplicationContext(),UserWriteOpinion.class);
            }
        });
    }

    //List隨著高度改變
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
