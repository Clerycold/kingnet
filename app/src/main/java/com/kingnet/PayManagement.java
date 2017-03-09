package com.kingnet;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/11/7.
 */

public class PayManagement extends Activity {

    private Titledesign titledesign;

    private ImageView imageView;
    private Bitmap mBitmap;

    private int Width;
    private int Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.paymanagement);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        titledesign.setTextView(getResources().getString(R.string.bottompaymoney));

        setImageView();

    }
    private void initView() {
        titledesign = (Titledesign) findViewById(R.id.titledesign);
        imageView = (ImageView) findViewById(R.id.imageview);
    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_img_lay =new RelativeLayout.LayoutParams((int) (double)Width, (int) (double)(Height/10)*2);
        r_img_lay.setMargins(0,(int) (double)(Height/10),0,0);
        imageView.setLayoutParams(r_img_lay);
    }
    private void setImageView(){

        mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(),
                R.drawable.home_money_ad, (int)(double)((Width)),
                (int)(double)((Height/10)*2));
        imageView.setImageBitmap(mBitmap);
    }
}
