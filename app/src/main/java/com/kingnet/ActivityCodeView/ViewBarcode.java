package com.kingnet.ActivityCodeView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Newfragment.TabFragment;
import com.kingnet.R;
import com.kingnet.UIpattern.IOSSwitchView;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ViewBarcode extends FragmentActivity implements IOSSwitchView.OnSwitchStateChangeListener{

    private ImageView imageView;

    private IOSSwitchView mSwitchView;
    private TextView textView;

    private FrameLayout frameLayout;

    private int Width;
    private int Height;

    public static int REQUEST_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.barcode_content);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        Bitmap mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(),R.drawable.imageviewlogo,(int) (double) Width, (int) (double) Math.round((Height / 10)*2));
        imageView.setImageBitmap(mBitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        textView.setText(R.string.Adjustmentlight);

        stopAutoBrightness();

        initTabFragment(savedInstanceState);
    }

    private void initTabFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            TabFragment tabFragment = new TabFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_fragment, tabFragment)
                    .commit();
        }

        mSwitchView.setOnSwitchStateChangeListener(this);
    }

    private void initView(){

        imageView=(ImageView)findViewById(R.id.barcodeImage);
        mSwitchView = (IOSSwitchView) findViewById(R.id.switch_view);
        textView=(TextView)findViewById(R.id.barcodetextview);
        frameLayout=(FrameLayout)findViewById(R.id.content_fragment);

    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams r_bg_top = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 10)*3));
        r_bg_top.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(r_bg_top);


        RelativeLayout.LayoutParams r_switch = new RelativeLayout.LayoutParams((int) (double) ((Width/10)*1.3), (int) (double) Math.round(Height / 30));
        r_switch.addRule(RelativeLayout.BELOW, imageView.getId());
        r_switch.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        r_switch.setMargins(0, 0,ScreenWH.getUISpacingX(),ScreenWH.getUISpacingY());
        mSwitchView.setLayoutParams(r_switch);

        RelativeLayout.LayoutParams r_textview = new RelativeLayout.LayoutParams((int) (double) Width/5, (int) (double) Math.round(Height / 20));
        r_textview.addRule(RelativeLayout.LEFT_OF,mSwitchView.getId());
        r_textview.addRule(RelativeLayout.BELOW, imageView.getId());
        r_textview.setMargins(0, (int) (double) Math.round(((Height / 30)-(int)(double) Math.round((Height / 30)))),0, 0);
        textView.setLayoutParams(r_textview);

        RelativeLayout.LayoutParams r_fragment_main= new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height / 10)*7));
        r_fragment_main.addRule(RelativeLayout.BELOW,mSwitchView.getId());
        frameLayout.setLayoutParams(r_fragment_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setadjustmentlight(int brightness) {
        Settings.System.putInt(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);
    }
    /**
     * 停止自动亮度调节
     *
     */
    private void stopAutoBrightness() {
        /**
         * 6.0版本的權限設定
         */
        if (Build.VERSION.SDK_INT >= M) {
            if(!Settings.System.canWrite(this)){
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }else {
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                setadjustmentlight(255);
            }
        }else{
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            setadjustmentlight(255);
        }
    }
    @TargetApi(M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (Settings.System.canWrite(this)) {

                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                setadjustmentlight(255);
            } else {
                Toast.makeText(this, "WRITE_SETTINGS permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * 开启亮度自动调节
     *
     */
    private void startAutoBrightness() {
        /**
         * 6.0版本的權限設定
         */
        if (Build.VERSION.SDK_INT >= M) {
            if(!Settings.System.canWrite(this)){
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }else {
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            }

        }else{
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        }
    }

    @Override
    public void onStateSwitched(boolean isOn) {
        if (isOn) {
            stopAutoBrightness();
        } else {
            startAutoBrightness();
        }
    }
}
