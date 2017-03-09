package com.kingnet.ActivityHome;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.kingnet.ActivityHome.presenter.IsUserFirstPresenter;
import com.kingnet.ActivityHome.presenter.IsUserFirstPresenterCompl;
import com.kingnet.ActivityHome.view.IShowTourView;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.MyThread;
import com.kingnet.Control.RemindUI;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.encodeAsBitmap;
import com.kingnet.GcmUtils.DownloadCodeTask;
import com.kingnet.GetShareMemory.SaveData;
import com.kingnet.JsonUtils.GetUserCommunity;
import com.kingnet.JsonUtils.GetUserPostal;
import com.kingnet.MyViewpage.AdapterPageChangeListener;
import com.kingnet.MyViewpage.FirstViewpage;
import com.kingnet.MyViewpage.HomePageAdapter;
import com.kingnet.MyViewpage.HomePageOneView;
import com.kingnet.MyViewpage.HomePageThreeView;
import com.kingnet.MyViewpage.HomePageTwoView;
import com.kingnet.MyViewpage.Mytransformer;
import com.kingnet.MyViewpage.PageFiveView;
import com.kingnet.MyViewpage.PageOneView;
import com.kingnet.MyViewpage.SamplePagerAdapter;
import com.kingnet.Newfragment.BarcodeFtagment;
import com.kingnet.Newfragment.QrcodeFragment;
import com.kingnet.R;
import com.kingnet.ActivityCodeView.ViewBarcode;
import com.kingnet.UIpattern.PageDot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IShowTourView{

    private ImageView mainLogo;
    private FirstViewpage homePageview;
    private LinearLayout homepageviewlinear;
    private RelativeLayout relativeLayout;
    private ImageView bottonbg;

    private HomePageAdapter homePageAdapter;

    private PageDot pageDot;

    /**
     * 為了讓fiveViewPage點擊隱藏 故設全域 應該有更好的方法
     */
    public static FirstViewpage mViewPager;
    public static LinearLayout linearLayout;

    private int Width;
    private int Height;

    /**
     * 判斷是否退出程式
     */
    private boolean isQuit = false;

    SaveData saveData;

    public static String VALIDCODE;
    public static String KINGAPI ="http://172.16.4.249/ajax/app_ws.asmx/";

    public static String USERID;

    IsUserFirstPresenter isUserFirstPresenter;

    private RelativeLayout relatbg;

    int[] tourImg = new int[]{R.drawable.welcome01,R.drawable.welcome02,R.drawable.welcome03,R.drawable.welcome04};
    int[] tourMidTxt = new int[]{R.string.homePageView,R.string.homePageView2,R.string.homePageView3,R.string.homePageView4};
    int[] tourBottomTxt = new int[]{R.string.homePageView1_2,R.string.homePageView2_2,R.string.homePageView3_2,R.string.homePageView4_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 無標題
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //在啟動前更改回主要的AppTheme
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        saveData=new SaveData(getApplicationContext());

        VALIDCODE=saveData.getStringData("Code","validCode");
        USERID=saveData.getStringData("usercommunity","userId");
        MyThread myThread=new MyThread(handler);

        if(VALIDCODE.equals("")){
            String validCode ="validDevice";
            new DownloadCodeTask(this).execute(KINGAPI+validCode);

        }else{
            myThread.setMillis(500);
            myThread.setmHandlerMessage(0x123);
            myThread.start();
            /**
             * 寫一個class專門判斷讀取資料
             */
            if(USERID.equals("")){
                String community="getUserCommunityList";
                new GetUserCommunity(this).execute(MainActivity.KINGAPI+community);
            }
        }

        if(!USERID.equals("")){
            String postalList="getUserPostalList";
            new GetUserPostal(this).execute(MainActivity.KINGAPI+postalList);
        }

        initView();
        xmlArrange();
        initDate();

        isUserFirstPresenter =new IsUserFirstPresenterCompl(getApplicationContext(),this);
        isUserFirstPresenter.IsFirstCheck();

        AdapterPageChangeListener homePageChangeListener = new AdapterPageChangeListener();
        homePageChangeListener.setViewGroup(pageDot);
        homePageChangeListener.setPagerAdapter(homePageAdapter);
        homePageview.setOnPageChangeListener(homePageChangeListener);
        homePageview.setAdapter(homePageAdapter);
        homePageview.setPageTransformer(true, new Mytransformer());
        /**
         * 讓ViewPager滑動順暢 setOffscreenPageLimit
         * 頁面佔存設太多會out of memory
         * if [6] 畫面會保存[0][1][2] 其餘為null
         */
        homePageview.setOffscreenPageLimit(2);

        homepageviewlinear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                        return homePageview.onTouchEvent(event);
                }
        });

        mainLogo.setImageDrawable(getResources().getDrawable(R.drawable.mainlogo));
        mainLogo.setScaleType(ImageButton.ScaleType.FIT_CENTER);

        Bitmap bitmap= BitmapUtils.decodeSampledBitmapFromResource(getResources(),R.drawable.mybarcode,(int) (double) ((Width/6.3)*2.3), (int) (double) (Height/20));
        bottonbg.setImageBitmap(bitmap);
        bottonbg.setOnClickListener(this);

    }

    private void initView() {

        relatbg=(RelativeLayout)findViewById(R.id.relatbg);
        mainLogo = (ImageView) findViewById(R.id.MainLogo);
        pageDot=new PageDot(getApplicationContext(),(int)(double)Width/40,3,R.drawable.dot_home_selector);
        pageDot.setId(View.generateViewId());
        homepageviewlinear=(LinearLayout)findViewById(R.id.homepageviewlinear);
        homePageview=(FirstViewpage)findViewById(R.id.homePageview);
        bottonbg = (ImageView) findViewById(R.id.bottombg);
        relativeLayout=(RelativeLayout)findViewById(R.id.bottom_bg);
    }
    private void xmlArrange() {

        RelativeLayout.LayoutParams r_bg_top = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round((Height) / 10) * 3);
        mainLogo.setLayoutParams(r_bg_top);

        RelativeLayout.LayoutParams r_homedot_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        r_homedot_lay.addRule(RelativeLayout.BELOW, mainLogo.getId());
        r_homedot_lay.setMargins((int) (double)(Width/6)*4, 0, 0, 0);
        pageDot.setLayoutParams(r_homedot_lay);
        relatbg.addView(pageDot);

        RelativeLayout.LayoutParams r_homepageviewlinear_lay =new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round(((Height) /10)*5));
        r_homepageviewlinear_lay.addRule(RelativeLayout.BELOW, pageDot.getId());
        r_homepageviewlinear_lay.setMargins(0,ScreenWH.getUISpacingY(),0,0);
        homepageviewlinear.setLayoutParams(r_homepageviewlinear_lay);

        ViewGroup.LayoutParams lp = homePageview.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams((int) (double) (Width), (int) (double) Math.round(((Height) /10)*5.5));
        } else {
            /**
             *homePageview顯示的寬高
             */
            lp.width = (int) (double)(Width/6)*4;
        }
        homePageview.setLayoutParams(lp);
        homePageview.setPageMargin(-(int) (double)((Width)/13.5));

        RelativeLayout.LayoutParams r_bottom_bg_lay = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round(((Height) / 10) *0.9));
        r_bottom_bg_lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayout.setLayoutParams(r_bottom_bg_lay);

        RelativeLayout.LayoutParams r_bg_bottom_lay = new RelativeLayout.LayoutParams((int) (double) ((Width/6.3)*2.3), (int) (double) (Height/20));
        r_bg_bottom_lay.addRule(RelativeLayout.ALIGN_BOTTOM,relativeLayout.getId());
        r_bg_bottom_lay.setMargins(0,0,0,(int) (double) ((Height/10)*0.45)-(int) (double) (Height/40));
        r_bg_bottom_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bottonbg.setLayoutParams(r_bg_bottom_lay);
    }

    private void initDate(){
        List<ViewGroup> homepagelist = new ArrayList<ViewGroup>();
            homepagelist.add(new HomePageOneView(getApplicationContext()));
            homepagelist.add(new HomePageTwoView(getApplicationContext()));
            homepagelist.add(new HomePageThreeView(getApplicationContext()));
            homePageAdapter=new HomePageAdapter(homepagelist);
    }

    public static void pageViewdiss() {
        mViewPager.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
    }

    private void firstSetApp(){

        linearLayout = (LinearLayout) findViewById(R.id.ll_dot);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorwhite));
        mViewPager = (FirstViewpage) findViewById(R.id.pager);

        RelativeLayout.LayoutParams r_middle_lin = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) ((Height / 10)*1.1));
        r_middle_lin.setMargins(0, (int) (double) ((Height/10)*7.5), 0, 0);
        linearLayout.setLayoutParams(r_middle_lin);

        RelativeLayout.LayoutParams r_viewpage = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Height);
        mViewPager.setLayoutParams(r_viewpage);

        SamplePagerAdapter samplePagerAdapter= new SamplePagerAdapter();
        for(int i=0,j=tourImg.length;i<j;i++){
            samplePagerAdapter.addPagelist(new PageOneView(getApplicationContext(),tourImg[i],tourMidTxt[i],tourBottomTxt[i]));

        }
        samplePagerAdapter.addPagelist(new PageFiveView(getApplicationContext()));
        mViewPager.setAdapter(samplePagerAdapter);

        PageDot pageDot1=new PageDot(getApplicationContext(),(int) (double) Width /40,5,R.drawable.dot_bg_selector);
        pageDot1.setPadding(0,(int) (double) ((Height / 20) - (Width / 60)),0,0);
        linearLayout.addView(pageDot1);

        AdapterPageChangeListener adapterPageChangeListener = new AdapterPageChangeListener();
        adapterPageChangeListener.setViewGroup(pageDot1);
        adapterPageChangeListener.setPagerAdapter(samplePagerAdapter);
        mViewPager.setOnPageChangeListener(adapterPageChangeListener);
        mViewPager.setOffscreenPageLimit(1);

        mViewPager.bringToFront();
        linearLayout.bringToFront();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            if (!isQuit) {
                Timer timer = new Timer();
                isQuit = true;
                RemindUI.setToast(getApplicationContext(),getResources().getString(R.string.isquit));
                /**
                 * 設定task兩秒後啟動
                 */
                TimerTask task;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123)
            {
                try {
                    QrcodeFragment.QRbitmap= encodeAsBitmap.encodeAsBitmap(MainActivity.VALIDCODE, BarcodeFormat.QR_CODE,(int) (double) ((Width/6.3)*3.5),(int) (double) ((Width/6.3)*3.5));
                    BarcodeFtagment.BRbitmap= encodeAsBitmap.encodeAsBitmap(MainActivity.VALIDCODE, BarcodeFormat.CODE_39,(int) (double) ((Width/6.3)*4.6),(int) (double) Math.round((Height / 10)*1.5));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        handler = null;
        System.gc();
    }

    @Override
    public void onShowTourView(Boolean isFirst) {
        if (isFirst){
            firstSetApp();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottombg:
                IntentActivity.IntentActivity(getApplicationContext(),ViewBarcode.class);
                break;
        }
    }
}
