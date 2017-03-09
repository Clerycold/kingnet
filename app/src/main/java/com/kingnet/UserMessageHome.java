package com.kingnet;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ScrollingView;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kingnet.Control.ScreenWH;
import com.kingnet.MyMessageFragment.MessageTabFragment;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/11/8.
 */

public class UserMessageHome extends FragmentActivity {

    private Titledesign titledesign;
    BottomButton bottomButton;

    private FrameLayout frameLayout;

    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.userhomemessage);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        initTabFragment(savedInstanceState);

        titledesign.setTextView(getResources().getString(R.string.titlemymessage));
    }

    private void initView(){
        titledesign=(Titledesign)findViewById(R.id.titledesign);
        bottomButton=(BottomButton)findViewById(R.id.buttombutton);
        frameLayout=(FrameLayout)findViewById(R.id.messagecontent_fragment);
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams r_packframe_lay =new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)(Height/10)*8);
        r_packframe_lay.addRule(RelativeLayout.BELOW,titledesign.getId());
        frameLayout.setLayoutParams(r_packframe_lay);
    }

    private void initTabFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            MessageTabFragment messageTabFragment = new MessageTabFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.messagecontent_fragment, messageTabFragment)
                    .commit();
        }
    }
}
