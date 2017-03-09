package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/11/10.
 */

public class UserBackOpinion extends Activity{

    private Titledesign titledesign;

    private RelativeLayout userrelat;
    private TextView usertitle;
    private TextView usertimer;
    private TextView userimg;

    private RelativeLayout userrelat_mid;
    private TextView usercontent_mid;
    private ImageView userimg_mid;

    private ImageView massageimg;

    private RelativeLayout userrelat_bottom;
    private TextView massagetime;
    private TextView massagecontent;
    private ImageView massageimg_content;

    private int Width;
    private int Height;

    Bundle bundle;
    String content;
    Boolean isReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.userbackopinion);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        bundle = getIntent().getExtras();
        if(bundle!=null){
            isReply = bundle.getBoolean("isReply");
            content = bundle.getString("usercontent");
        }

        initView();
        xmlArrange();

        titledesign.setTextView(getResources().getString(R.string.titleuseropinion));


    }
    private void initView(){

        titledesign=(Titledesign)findViewById(R.id.titledesign);

        userrelat=(RelativeLayout)findViewById(R.id.userrelat_top);
        usertitle=(TextView)findViewById(R.id.usertitle);
        usertimer=(TextView)findViewById(R.id.usertime);
        userimg=(TextView)findViewById(R.id.userimg);

        userrelat_mid=(RelativeLayout)findViewById(R.id.userrelat_mid);
        usercontent_mid=(TextView)findViewById(R.id.usercontent_mid);
        userimg_mid=(ImageView)findViewById(R.id.userimg_mid);

        massageimg=(ImageView)findViewById(R.id.massageimg);

        userrelat_bottom=(RelativeLayout)findViewById(R.id.userrelat_bottom);
        massagetime=(TextView)findViewById(R.id.massagetime);
        massagecontent=(TextView)findViewById(R.id.massagecontent);
        massageimg_content=(ImageView)findViewById(R.id.massageimg_content);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams user_relat_top=new RelativeLayout.LayoutParams((int) (double) Math.round(((Width/10)*9.5)),
                (int) (double) Math.round(((Height) /10)));
        user_relat_top.setMargins(25,(int) (double) Math.round(((Height) /10))+25,25,0);
        userrelat.setLayoutParams(user_relat_top);

        RelativeLayout.LayoutParams user_usertitle_top=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (int) (double) Math.round(((Height) /10)/3));
        user_usertitle_top.setMargins(ScreenWH.getUISpacingY(),ScreenWH.getUISpacingY(),0,ScreenWH.getUISpacingY()/2);
        usertitle.setLayoutParams(user_usertitle_top);
        usertitle.setText("您的建議");

        RelativeLayout.LayoutParams user_usertime_top=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (int) (double) Math.round(((Height) /10)/3));
        user_usertime_top.addRule(RelativeLayout.BELOW,usertitle.getId());
        user_usertime_top.setMargins(25,0,0,25);
        usertimer.setLayoutParams(user_usertime_top);
        usertimer.setText("提交時間2016/11/03 14:52");

        RelativeLayout.LayoutParams user_userimg_top=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                (int) (double) Math.round(((Height) /10)/3));
        user_userimg_top.addRule(RelativeLayout.BELOW,usertitle.getId());
        user_userimg_top.addRule(RelativeLayout.RIGHT_OF,usertimer.getId());
        userimg.setLayoutParams(user_userimg_top);
        userimg.setPadding(ScreenWH.getUISpacingY(),0,ScreenWH.getUISpacingY(),0);
        if(isReply){
            userimg.setText("已回覆");
            userimg.setBackgroundColor(getResources().getColor(R.color.colorGreenlight));
        }else{
            userimg.setText("尚未回覆");
            userimg.setBackgroundColor(getResources().getColor(R.color.colorblack));
        }

        RelativeLayout.LayoutParams user_relat_mid=new RelativeLayout.LayoutParams((int) (double) Math.round(((Width/10)*9.5)),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        user_relat_mid.addRule(RelativeLayout.BELOW,userrelat.getId());
        user_relat_mid.setMargins(25,0,25,0);
        userrelat_mid.setLayoutParams(user_relat_mid);
        userrelat_mid.setPadding(ScreenWH.getUISpacingY(),ScreenWH.getUISpacingY(),0,ScreenWH.getUISpacingY());

        RelativeLayout.LayoutParams user_usercontent_mid=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        user_usercontent_mid.setMargins(ScreenWH.getUISpacingY()*2,ScreenWH.getUISpacingY()*2,0,ScreenWH.getUISpacingY()*2);
        usercontent_mid.setLayoutParams(user_usercontent_mid);
        usercontent_mid.setText(content);

        RelativeLayout.LayoutParams user_userimg_mid=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        user_userimg_mid.addRule(RelativeLayout.BELOW,usercontent_mid.getId());
        user_usercontent_mid.setMargins(25,0,0,25);
        userimg_mid.setLayoutParams(user_userimg_mid);


        RelativeLayout.LayoutParams user_massageimg_bottom=new RelativeLayout.LayoutParams((int) (double) Math.round(((Width/10)*1.5)),
                (int) (double) Math.round(((Height) /10)));
        user_massageimg_bottom.addRule(RelativeLayout.BELOW,userrelat_mid.getId());
        user_massageimg_bottom.setMargins(25,25,25,0);
        massageimg.setLayoutParams(user_massageimg_bottom);
        massageimg.setBackgroundColor(getResources().getColor(R.color.colorblack));

        RelativeLayout.LayoutParams user_userrelat_bottom=new RelativeLayout.LayoutParams((int) (double) Math.round(((Width/10)*8)),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        user_userrelat_bottom.addRule(RelativeLayout.BELOW,userrelat_mid.getId());
        user_userrelat_bottom.addRule(RelativeLayout.RIGHT_OF,massageimg.getId());
        user_userrelat_bottom.setMargins(0,25,25,0);
        userrelat_bottom.setLayoutParams(user_userrelat_bottom);

        RelativeLayout.LayoutParams user_massagetime_bottom=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (int) (double) Math.round(((Height) /10)/3));
        user_massagetime_bottom.setMargins(25,25,25,0);
        massagetime.setLayoutParams(user_massagetime_bottom);
        massagetime.setText("回覆於2016/11/03 14:52");

        RelativeLayout.LayoutParams user_massagecontent_bottom=new RelativeLayout.LayoutParams((int) (double) Math.round(((Width/10)*8)),
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        user_massagecontent_bottom.addRule(RelativeLayout.BELOW,massagetime.getId());
        user_massagecontent_bottom.setMargins(25,25,0,25);
        massagecontent.setLayoutParams(user_massagecontent_bottom);
        massagecontent.setText("你好");

        RelativeLayout.LayoutParams user_massageimg_content=new RelativeLayout.LayoutParams((ViewGroup.LayoutParams.WRAP_CONTENT),
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        user_massageimg_content.addRule(RelativeLayout.BELOW,usercontent_mid.getId());
        user_massageimg_content.setMargins(25,0,0,25);
        massageimg_content.setLayoutParams(user_massageimg_content);
    }
}
