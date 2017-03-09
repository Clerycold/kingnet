package com.kingnet.MyViewpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.kingnet.ActivityUserInfo.ActivityUserInfo;
import com.kingnet.Animation.CreateScaleAnim;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.PayManagement;
import com.kingnet.PublicUtilitiesHome;
import com.kingnet.R;
import com.kingnet.UIpattern.BottomButton;
import com.kingnet.PackageFragment.UserHome_package;
import com.kingnet.UserOpinion;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HomePageOneView extends RelativeLayout {

    private View view;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageButton imageButton5;
    private ImageButton imageButton6;

    private RelativeLayout relativeLayout;

    private int Width;
    private int Height;
    private int spacing;

    private ImageButton[] ibtn;
    private int[] icon;

    public HomePageOneView(Context context) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);
        spacing=ScreenWH.getUISpacingY();

        initView();
        initData();
        xmlArrange();
        buttonListener();

        Bitmap mBitmap;
        for(int i=0,j=ibtn.length;i<j;i++){
            mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getContext().getResources(),
                    icon[i], (int) (double) Math.round(((Width) /6.3)*2),
                    (int) (double) Math.round(((Height) /10)*1.6));

                ibtn[i].setImageBitmap(mBitmap);
                ibtn[i].setScaleType(ImageButton.ScaleType.FIT_CENTER);
        }

        addView(view);
    }

    private void initView(){

        view = LayoutInflater.from(getContext()).inflate(R.layout.homepageone, null);

        relativeLayout=(RelativeLayout)view.findViewById(R.id.relatbg);

        imageButton1=(ImageButton)view.findViewById(R.id.imagebutton1);
        imageButton2=(ImageButton)view.findViewById(R.id.imagebutton2);
        imageButton3=(ImageButton)view.findViewById(R.id.imagebutton3);
        imageButton4=(ImageButton)view.findViewById(R.id.imagebutton4);
        imageButton5=(ImageButton)view.findViewById(R.id.imagebutton5);
        imageButton6=(ImageButton)view.findViewById(R.id.imagebutton6);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_relatbg_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(Width),
                (int) (double) Math.round(((Height) /10)*5));
        relativeLayout.setLayoutParams(r_relatbg_lay);

        RelativeLayout.LayoutParams lay;
        for(int i=0;i<6;i++){
            lay = new RelativeLayout.LayoutParams(
                    (int) (double) Math.round(((Width) /6.3)*2),
                    (int) (double) Math.round(((Height) /10)*1.6));
            if(i%2==0 && i>0){
                lay.addRule(RelativeLayout.BELOW, ibtn[i-2].getId());
                if(i>1){
                    lay.setMargins(0,spacing,0,0);
                }
            }else if(i%2==1){
                lay.addRule(RelativeLayout.RIGHT_OF, ibtn[i-1].getId());
                if(i>1){
                    lay.addRule(RelativeLayout.BELOW, ibtn[i-2].getId());
                    lay.setMargins(spacing,spacing,0,0);
                }else{
                    lay.setMargins(spacing,0,0,0);
                }

            }
            ibtn[i].setLayoutParams(lay);
        }
    }

    private void initData(){

        ibtn=new ImageButton[]{imageButton1,imageButton2,
                imageButton3,imageButton4,
                imageButton5,imageButton6};

        icon=new int[]{R.drawable.btn_01,R.drawable.btn_02,
                R.drawable.btn_03,R.drawable.btn_04,
                R.drawable.btn_05,R.drawable.btn_06};

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
                        x[0] = event.getX();
                        y[0] = event.getY();
                        CreateScaleAnim.smallScale(v);
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
                                case R.id.imagebutton1:
                                    if(Math.abs(dy[0])<Height/8){
                                        IntentActivity.IntentActivity(getContext(),UserHome_package.class);
                                        BottomButton.ICONTYPE=1;
                                    }
                                    break;
                                case R.id.imagebutton3:
                                    if(Math.abs(dy[0])<Height/8) {
                                        IntentActivity.IntentActivity(getContext(), UserOpinion.class);
                                        BottomButton.ICONTYPE = 3;
                                    }
                                    break;

                                case R.id.imagebutton4:
                                    if(Math.abs(dy[0])<Height/8) {
                                        IntentActivity.IntentActivity(getContext(), PayManagement.class);
                                        BottomButton.ICONTYPE = 4;
                                    }
                                    break;
                                case R.id.imagebutton5:
                                    if(Math.abs(dy[0])<Height/8) {
                                        IntentActivity.IntentActivity(getContext(), ActivityUserInfo.class);
                                        BottomButton.ICONTYPE = 1;
                                    }
                                    break;
                                case R.id.imagebutton6:
                                    if(Math.abs(dy[0])<Height/8) {
                                        IntentActivity.IntentActivity(getContext(), PublicUtilitiesHome.class);
                                        BottomButton.ICONTYPE = 6;
                                    }
                                    break;
                        }
                }
                CreateScaleAnim.prototypeScale(v);
                return false;
            }
        };
        imageButton1.setOnTouchListener(onTouchListener);
        imageButton3.setOnTouchListener(onTouchListener);
        imageButton4.setOnTouchListener(onTouchListener);
        imageButton5.setOnTouchListener(onTouchListener);
        imageButton6.setOnTouchListener(onTouchListener);
    }
}

