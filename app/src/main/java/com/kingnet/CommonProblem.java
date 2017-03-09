package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.ExpandableList.ExpandableListAdapter;
import com.kingnet.UIpattern.Titledesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CommonProblem extends Activity {

    TextView textView;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    int[] listIv; //圖示
    List<String> listDataHeader; //標題
    HashMap<String, List<String>> listDataChild; //內容

    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;

    Titledesign titledesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.commonproblem);

        Resources resources = CommonProblem.this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);

        Height -=height;

        initView();
        xmlArrange();

        // 列表資料
        prepareListData();

        titledesign.setTextView(getResources().getString(R.string.titlecommon));
        textView.setText(getResources().getString(R.string.commonproblemtext));

        /* listIv-圖示, listDataHeader-標題, listDataChild-內容*/
        listAdapter = new ExpandableListAdapter(this,listIv, listDataHeader,
                listDataChild);
        // 將列表資料加入至展開列表單
        expListView.setAdapter(listAdapter);

        /**
         * 設定箭頭標籤至最右邊
         * if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
         myticketLV.setIndicatorBounds(width - 40, width);
         } else {
         myticketLV.setIndicatorBoundsRelative(width - 40, width);
         }
         //根據不同版本進行設定，API 18後使用myticketLV.setIndicatorBoundsRelative(width - 40, width);
         */
        expListView.setIndicatorBounds((int)(double)Width-(int)(double)(Width/20),(int)(double)Width);
        // 點選標題 展開 監聽事件
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            public void onGroupExpand(int groupPosition) {
                /**
                 * 只展開一項目錄
                 */
                for (int i = 0; i < listAdapter.getGroupCount(); i++) {
                    // ensure only one expanded Group exists at every
                    if (groupPosition != i) {
                        expListView.collapseGroup(i);
                    }
                }
            }
        });
    }

    /* 列表資料 */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>(); // 標題
        listDataChild = new HashMap<String, List<String>>(); // 內容

        listDataHeader.add(getResources().getString(R.string.qaquestiongroup));
        listDataHeader.add(getResources().getString(R.string.qaquestiongroup1));
        listDataHeader.add(getResources().getString(R.string.qaquestiongroup2));

        // Adding child data
        List<String> first = new ArrayList<String>();
        first.add(getResources().getString(R.string.qaanswerchild));

        List<String> second = new ArrayList<String>();
        second.add(getResources().getString(R.string.qaanswerchild1));

        List<String> end = new ArrayList<String>();
        end.add(getResources().getString(R.string.qaanswerchild2));

        listDataChild.put(listDataHeader.get(0), first); // 標題, 內容
        listDataChild.put(listDataHeader.get(1), second);
        listDataChild.put(listDataHeader.get(2), end);
    }

    private void initView(){

        titledesign=(Titledesign)findViewById(R.id.titledesign);
        textView=(TextView)findViewById(R.id.qatext);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams r_title_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 20)));
        r_title_lay.addRule(RelativeLayout.BELOW,titledesign.getId());
        r_title_lay.setMargins(25,(int) (double) Math.round((Height / 40)),0,0);
        textView.setLayoutParams(r_title_lay);

        RelativeLayout.LayoutParams r_expList_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 10)*8.5));
        r_expList_lay.addRule(RelativeLayout.BELOW,textView.getId());
        expListView.setLayoutParams(r_expList_lay);
    }
}