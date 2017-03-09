package com.kingnet.MyCamera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.view.OrientationEventListener.ORIENTATION_UNKNOWN;

/**
 * Created by clery on 2016/11/22.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Camera.Size mPreviewSize;



    public CameraPreview(Context context, Camera camera){
        super(context);

        this.mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
                if (Build.VERSION.SDK_INT >= 8 ) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        mCamera.setDisplayOrientation(90);
                    }
                }

            } catch (IOException e) {
                mCamera.release();
                mCamera = null;
                Log.d("TAG", "Error setting camera preview: " + e.getMessage());
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
        }
        try {
            Camera.Parameters myParameters = mCamera.getParameters();
            if(mPreviewSize != null) {
                myParameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
                mCamera.setParameters(myParameters);
            }
            myParameters.setPreviewFrameRate(5);
            myParameters.setPictureFormat(PixelFormat.JPEG);
            myParameters.set("jpeg-quality", 100);
            checkAutoFouse(myParameters);
            mCamera.setParameters(myParameters);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d("TAG", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mCamera!=null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();//停止預覽
            mCamera.release();//釋放相機資源
            mCamera = null;
            mHolder=null;
        }
    }
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            //Math.abs 絕對值
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        /**
         * 必须调用super.ononMeasure()或者直接调用setMeasuredDimension()方法设置该View大小，否则会报异常。
         * 设置当前view的大小 width:view的宽，单位都是像素值 heigth:view的高，单位都是像素值
         */
        setMeasuredDimension(width, height);
        if(UserCamera.CURRENTCAMERAID== Camera.CameraInfo.CAMERA_FACING_BACK ){
            List<Camera.Size> mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            if (mSupportedPreviewSizes != null) {
                mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
            }
        }
    }
    private boolean checkAutoFouse(Camera.Parameters myParameters) {
        myParameters = mCamera.getParameters();
        // 判斷能不能對焦
        if (myParameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            myParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            if (myParameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE))
                myParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            return true;

        } else {

            return false;
        }
    }
}
