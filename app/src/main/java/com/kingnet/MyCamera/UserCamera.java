package com.kingnet.MyCamera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kingnet.BestBitmap.BitmapRotation;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.GalleryAddPic;
import com.kingnet.Control.ScreenWH;
import com.kingnet.MyCamera2.DataFilePath;
import com.kingnet.MyCamera2.MyOrientationDetector;
import com.kingnet.R;
import com.kingnet.UserWriteOpinion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.kingnet.UserWriteOpinion.SAVEPICTURE;


/**
 * Created by clery on 2016/11/22.
 */

public class UserCamera extends Activity {

    FrameLayout frameLayoutPreview;
    private Camera mCamera;
    private CameraPreview mPreview;

    private Button button_take;

    public static int CURRENTCAMERAID;
    public static Boolean ENMERGENCE = false;
    private Boolean isTakePicture=false;

    private int Width;
    private int Height;

    private MyOrientationDetector myOrientationDetector;

    public static Bitmap temppicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.user_camera);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);
        myOrientationDetector=new MyOrientationDetector(this);

        initViews();
        xmlArrange();
        setOnClickListener();

        if (CameraCheck.checkCameraHardware(this)) {
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(this, mCamera);
        }

        frameLayoutPreview.addView(mPreview);

        new MyThread().start();

        new AlertDialog.Builder(UserCamera.this)
                .setOnDismissListener(null)
                .setTitle("儲存照片")
                .setMessage("照片將儲存於手機，不儲存照片使用後將會刪除")
                .setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SAVEPICTURE=false;
                    }
                })
                .setNeutralButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SAVEPICTURE=true;
                    }
                })
                .show();
    }

    private void initViews(){
        frameLayoutPreview = (FrameLayout) findViewById(R.id.camera_preview);
        button_take=(Button)findViewById(R.id.button_take);
    }

    private void xmlArrange(){
        RelativeLayout.LayoutParams r_camera2button_lay =new RelativeLayout.LayoutParams((int) (double) ((Width/10)*2), (int) (double) ((Width/10)*2));
        r_camera2button_lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        r_camera2button_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        r_camera2button_lay.setMargins(0,0,0,(int) (double) ((Height/10)));
        button_take.setLayoutParams(r_camera2button_lay);
    }

    private void setOnClickListener(){
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.camera_preview:
                        isTakePicture=false;
                        CameraautoFocus();
                        break;
                    case R.id.button_take:
                        isTakePicture=true;
                        CameraautoFocus();

                        break;
                }
            }
        };
        frameLayoutPreview.setOnClickListener(onClickListener);
        button_take.setOnClickListener(onClickListener);
    }
    private Camera getCameraInstance() {
        Camera c = null;
        try {

            c = Camera.open();
            CURRENTCAMERAID = CameraCheck.getDefaultCameraId(this);
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }

        return c;
    }

    private void CameraautoFocus(){
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if(isTakePicture){
                    capturePicture();
                    isTakePicture=!isTakePicture;
                }
            }
        });
    }

    class MyThread extends Thread
    {
        @Override
        public void run() {
            //延迟两秒更新
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }
    }
    private Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123)
            {
                CameraautoFocus();
            }
        }
    };

    private void capturePicture() {
        /**
         ** 監聽當前螢幕旋轉開啟
         */
        myOrientationDetector.enable();
        mCamera.takePicture(
                shutterCallback,//// 指定快門回呼
                null,// 指定raw data可讀回呼
                mPicture);// 指定jpeg data可讀回呼
    }
    private final Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {
            AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mgr.playSoundEffect(AudioManager.FLAG_PLAY_SOUND);
        }
    };

    final transient private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            if (data != null) {
                temppicture = BitmapUtils.decodeSampledBitmapData(data, (int)(double)Height,(int)(double)Width);
                //picture = rotationBitmap(picture);
                temppicture=new BitmapRotation().bitmapRotation(temppicture,myOrientationDetector.ORIENTATION,CURRENTCAMERAID);
            }
            if(SAVEPICTURE){
                File pictureFile = DataFilePath.getDataFilePath(getApplicationContext());

                if (pictureFile == null) {
                    return;
                }
                if (CameraCheck.isExternalStorageWritable()) {
                    try {
                        FileOutputStream fos = new FileOutputStream(pictureFile);
                        //第二個參數0~100  100表示不壓縮
                        //Bitmap mBitmap = Bitmap.createScaledBitmap(picture, 600, 800, true);
                        //mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        temppicture.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        byte[] b = new byte[1024 * 8];
                        fos.write(b);
                        fos.flush();
                        fos.close();

                    } catch (FileNotFoundException e) {
                        Log.d("TAG", "File not found: " + e.getMessage());
                    } catch (IOException e) {
                        Log.d("TAG", "Error accessing file: " + e.getMessage());
                    }
                }
                new GalleryAddPic(getApplicationContext(),DataFilePath.mediaStorageDir.getPath());
            }
            /**
             ** 監聽當前螢幕旋轉關閉
             */
            myOrientationDetector.disable();
            UserWriteOpinion.ISEXCITBITMAP=true;
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        if(ENMERGENCE &&mCamera==null){
            mCamera = getCameraInstance();
            mPreview = new CameraPreview(this, mCamera);
            frameLayoutPreview.addView(mPreview);
            ENMERGENCE =false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        frameLayoutPreview.removeView(mPreview);
        mCamera = null;
        mPreview = null;
        ENMERGENCE =true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeMessages(0x123);
        }
    }
}
