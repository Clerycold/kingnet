package com.kingnet.PackageFragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by Administrator on 2016/11/1.
 */
public class UserHome_package extends FragmentActivity{

    private Titledesign titledesign;

    private FrameLayout frameLayout;

    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.userhomepackage);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        initTabFragment(savedInstanceState);
        titledesign.setTextView(getResources().getString(R.string.titlepackage));


    }

    private void initTabFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            PackTabFragment packTabFragment = new PackTabFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.packcontent_fragment, packTabFragment)
                    .commit();
        }
    }

    private void initView(){
        titledesign=(Titledesign)findViewById(R.id.titledesign);
        frameLayout=(FrameLayout)findViewById(R.id.packcontent_fragment);
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams r_packframe_lay =new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)(Height/10)*8);
        r_packframe_lay.addRule(RelativeLayout.BELOW,titledesign.getId());
        frameLayout.setLayoutParams(r_packframe_lay);
    }

}
