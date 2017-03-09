package com.kingnet.MyViewpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.kingnet.Animation.CreateScaleAnim;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.MoreInfoCommon;
import com.kingnet.R;
import com.kingnet.ActivityLifeConvenience.LifeConvenience;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.ActivityZoo.ZooActivity;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HomePageTwoView extends RelativeLayout {

    private View view;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;

    private RelativeLayout relativeLayout;

    private int[] icon;

    private int Width;
    private int Height;
    private int spacing;

    private ImageButton[] ibtn;

    public HomePageTwoView(Context context) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);
        spacing=ScreenWH.getUISpacingY();

        initView();
        initData();
        xmlArrange();
        buttonListener();

        Bitmap mBitmap;
        for(int i=0;i<3;i++){
            if(i==3){
                mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getContext().getResources(),icon[i],
                        (int) (double) Math.round(((Width) /6.3)*4+spacing),
                        (int) (double) Math.round(((Height) /10)*1.6)*2);
                ibtn[i].setImageBitmap(mBitmap);
            }else{
                mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getContext().getResources(),
                        icon[i], (int) (double) Math.round(((Width) /6.3)*2),
                        (int) (double) Math.round(((Height) /10)*1.6));
                ibtn[i].setImageBitmap(mBitmap);
                ibtn[i].setScaleType(ImageButton.ScaleType.FIT_CENTER);
            }
        }

        addView(view);
    }
    private void initView() {

        view = LayoutInflater.from(getContext()).inflate(R.layout.homepagetwo, null);

        relativeLayout=(RelativeLayout)view.findViewById(R.id.hometworelat);

        imageButton1=(ImageButton)view.findViewById(R.id.hometwobutton1);
        imageButton2=(ImageButton)view.findViewById(R.id.hometwobutton2);
        imageButton3=(ImageButton)view.findViewById(R.id.hometwobutton3);
    }

    private void initData(){
        ibtn=new ImageButton[]{imageButton1,imageButton2,
                imageButton3};

        icon=new int[]{R.drawable.btn_07,R.drawable.btn_08,
                R.drawable.ad_1};
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams r_relatbg_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(Width),
                (int) (double) Math.round(((Height) /10)*5));
        relativeLayout.setLayoutParams(r_relatbg_lay);

        RelativeLayout.LayoutParams r_imagebutton1_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(((Width) /6.3)*2),
                (int) (double) Math.round(((Height) /10)*1.6));
        imageButton1.setLayoutParams(r_imagebutton1_lay);

        RelativeLayout.LayoutParams r_imagebutton2_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(((Width) /6.3)*2),
                (int) (double) Math.round(((Height) /10)*1.6));
        r_imagebutton2_lay.addRule(RelativeLayout.RIGHT_OF, imageButton1.getId());
        r_imagebutton2_lay.setMargins(spacing,0,0,0);
        imageButton2.setLayoutParams(r_imagebutton2_lay);

        RelativeLayout.LayoutParams r_imagebutton3_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(((Width) /6.3)*4+spacing),
                (int) (double) Math.round(((Height) /10)*1.6)*2);
        r_imagebutton3_lay.addRule(RelativeLayout.BELOW, imageButton1.getId());
        r_imagebutton3_lay.setMargins(0, spacing, 0, 0);
        imageButton3.setLayoutParams(r_imagebutton3_lay);
    }

    private void buttonListener(){
        final float[] y = new float[1];
        final float[] x = new float[1];
        final float[] dx = new float[1];
        final float[] dy = new float[1];

        View.OnTouchListener onTouchListener= new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        CreateScaleAnim.smallScale(v);
                        x[0] = event.getX();
                        y[0] = event.getY();
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        dx[0] = event.getX() - x[0];
                        dy[0] = event.getY() - y[0];

                        if(Math.abs(dx[0])>Width/40|Math.abs(dy[0])>Height/8){
                            CreateScaleAnim.prototypeScale(v);
                            return true;
                        }
                        return false;

                    case MotionEvent.ACTION_UP:
                        CreateScaleAnim.prototypeScale(v);
                        switch (v.getId()){
                            case R.id.hometwobutton1:
                                if(Math.abs(dy[0])<Height/8) {
                                    IntentActivity.IntentActivity(getContext(), LifeConvenience.class);
                                    BottomButton.ICONTYPE = 21;
                                }
                                break;
                            case R.id.hometwobutton2:
                                if(Math.abs(dy[0])<Height/8) {
                                    IntentActivity.IntentActivity(getContext(), MoreInfoCommon.class);
                                    BottomButton.ICONTYPE = 1;
                                }
                                break;
                            case R.id.hometwobutton3:
                                if(Math.abs(dy[0])<Height/8) {
                                    IntentActivity.IntentActivity(getContext(), ZooActivity.class);
                                }
                                break;
                        }
                }
                CreateScaleAnim.prototypeScale(v);
                return false;
            }
        };
        imageButton1.setOnTouchListener(onTouchListener);
        imageButton2.setOnTouchListener(onTouchListener);
        imageButton3.setOnTouchListener(onTouchListener);

    }
}
