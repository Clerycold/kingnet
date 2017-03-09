package com.kingnet;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.BestBitmap.BitmapRotation;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.MyThread;
import com.kingnet.Control.ScreenWH;
import com.kingnet.MyCamera.UserCamera;
import com.kingnet.MyCamera2.DataFilePath;
import com.kingnet.MyCamera2.MyOrientationDetector;
import com.kingnet.MyCamera2.UserCamera2;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/11/10.
 */

public class UserWriteOpinion extends Activity {

    private Titledesign titledesign;

    private TextView titleText;
    private EditText writecontent;
    private TextView writetextnumber;
    private ImageButton writepicture;
    private Button writebtnok;

    private static final int MAX_COUNT = 100;
    private static final int REQUEST_CAMERA_PERMISSION = 200;

    private int Width;
    private int Height;

    public static Boolean SAVEPICTURE=false;
    public static Boolean ISEXCITBITMAP=false;

    private MyThread myThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.userwriteopinion);

        Width= ScreenWH.getScreenWidth();
        Height= ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();
        titledesign.setTextView(getResources().getString(R.string.titleuseropinion));
        setImageListener();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UserWriteOpinion.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CAMERA_PERMISSION);
            }
        }

    }

    private void initView(){
        titledesign=(Titledesign)findViewById(R.id.titledesign);

        titleText=(TextView)findViewById(R.id.writetitle);
        writecontent=(EditText)findViewById(R.id.writecontent);
        writetextnumber=(TextView)findViewById(R.id.writetextnumber);
        writepicture=(ImageButton)findViewById(R.id.writepicture);
        writebtnok=(Button)findViewById(R.id.writebtnok);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_writetitle_lay =new RelativeLayout.LayoutParams((int) (double)Width, ViewGroup.LayoutParams.WRAP_CONTENT);
        r_writetitle_lay.addRule(RelativeLayout.BELOW,titledesign.getId());
        r_writetitle_lay.setMargins(25,25,25,25);
        titleText.setLayoutParams(r_writetitle_lay);
        titleText.setText("親愛的住戶  您好，"+"\n"+"社區維護不分你我，大家一起共同維護，對社區有任何建議可提供管委會參考");

        RelativeLayout.LayoutParams r_writecontent_lay =new RelativeLayout.LayoutParams((int) (double)Width, (int) (double)((Height/10)*2.5));
        r_writecontent_lay.addRule(RelativeLayout.BELOW,titleText.getId());
        r_writecontent_lay.setMargins(25,0,25,0);
        writecontent.setLayoutParams(r_writecontent_lay);
        writecontent.addTextChangedListener(mTextWatcher);

        RelativeLayout.LayoutParams r_writetextnumber_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        r_writetextnumber_lay.addRule(RelativeLayout.BELOW,writecontent.getId());
        r_writetextnumber_lay.addRule(RelativeLayout.ALIGN_RIGHT,writecontent.getId());
        writetextnumber.setLayoutParams(r_writetextnumber_lay);
        writetextnumber.setText("限100字元/100");

        RelativeLayout.LayoutParams r_writepicture_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)((Height/10)*2.5));
        r_writepicture_lay.addRule(RelativeLayout.BELOW,writetextnumber.getId());
        r_writepicture_lay.setMargins(25,25,25,0);
        writepicture.setLayoutParams(r_writepicture_lay);


        RelativeLayout.LayoutParams r_writebtnok_lay =new RelativeLayout.LayoutParams((int) (double)Width, (int) (double)((Height/10)));
        r_writebtnok_lay.addRule(RelativeLayout.BELOW,writepicture.getId());
        r_writebtnok_lay.setMargins(25,25,25,0);
        writebtnok.setLayoutParams(r_writebtnok_lay);
        writebtnok.setText("提  交  建  議");

    }
    /**
     * Edit監聽器
     */
    private TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;

        private int editEnd;

        public void afterTextChanged(Editable s) {
            editStart = writecontent.getSelectionStart();
            editEnd = writecontent.getSelectionEnd();

            // 先去掉监听器，否则会出现栈溢出
            writecontent.removeTextChangedListener(mTextWatcher);

            // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                s.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
            }
            // mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
            writecontent.setSelection(editStart);

            // 恢复监听器
            writecontent.addTextChangedListener(mTextWatcher);

            setLeftCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

    };
    /**
     * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
     *
     * @param c
     * @return
     */
    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0 ,j=c.length(); i < j; i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }
    /**
     * 刷新剩余输入字数,最大值新浪微博是140个字，人人网是200个字
     */
    private void setLeftCount() {
        writetextnumber.setText("限100字元/"+String.valueOf((MAX_COUNT - getInputCount())));
    }
    /**
     * 获取用户输入的分享内容字数
     *
     * @return
     */
    private long getInputCount() {
        return calculateLength(writecontent.getText().toString());
    }

    //如果希望捕获屏幕旋转事件，并且不希望activity 被销毁
    //AndroidManifest.xml对应的activity属性中，添加：android:configChanges="orientation|screenSize"
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserCamera.temppicture!=null){
            if(MyOrientationDetector.ORIENTATION==0|MyOrientationDetector.ORIENTATION==180){
                RelativeLayout.LayoutParams r_writepicture_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                r_writepicture_lay.addRule(RelativeLayout.BELOW,writetextnumber.getId());
                r_writepicture_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
                r_writepicture_lay.setMargins(25,25,25,0);
                writepicture.setLayoutParams(r_writepicture_lay);
                writepicture.setImageBitmap(UserCamera.temppicture);
                writepicture.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else{
                RelativeLayout.LayoutParams r_writepicture_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                r_writepicture_lay.addRule(RelativeLayout.BELOW,writetextnumber.getId());
                r_writepicture_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
                r_writepicture_lay.setMargins(25,25,25,0);
                writepicture.setLayoutParams(r_writepicture_lay);
                writepicture.setImageBitmap(UserCamera.temppicture);
                writepicture.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            setImageListener();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        DataFilePath.TEMPPATH=null;
    }
    private void setImageListener(){
        if(!ISEXCITBITMAP){
            writepicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    IntentActivity.IntentActivity(getApplicationContext(),UserCamera.class);
                    UserCamera.temppicture=null;
                }
            });
        }else {
            writepicture.setOnClickListener(null);
            setdissimgbtn();
            ISEXCITBITMAP=true;
        }
    }

    private void setdissimgbtn(){
        final Button dissimg=(Button)findViewById(R.id.dissimg);
        RelativeLayout.LayoutParams r_diss_lay =new RelativeLayout.LayoutParams((int)(double)Width/10, (int)(double)Width/10);
        r_diss_lay.addRule(RelativeLayout.BELOW,writetextnumber.getId());
        r_diss_lay.addRule(RelativeLayout.ALIGN_RIGHT,writetextnumber.getId());
        r_diss_lay.setMargins(0,25,0,0);
        dissimg.setLayoutParams(r_diss_lay);
        dissimg.setVisibility(View.VISIBLE);

        dissimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISEXCITBITMAP=false;
                dissimg.setVisibility(View.GONE);
                myThread=new MyThread(handler);
                myThread.setmHandlerMessage(0x123);
                myThread.start();
            }
        });
    }
    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123)
            {
                RelativeLayout.LayoutParams r_writepicture_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)((Height/10)*2.5));
                r_writepicture_lay.addRule(RelativeLayout.BELOW,writetextnumber.getId());
                r_writepicture_lay.setMargins(25,25,25,0);
                writepicture.setLayoutParams(r_writepicture_lay);
                writepicture.setImageBitmap(null);
                writepicture.setBackgroundColor(getResources().getColor(R.color.colorblack));
                setImageListener();
            }
        }
    };
}
