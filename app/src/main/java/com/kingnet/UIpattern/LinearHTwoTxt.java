package com.kingnet.UIpattern;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by clery on 2016/12/20.
 */

public class LinearHTwoTxt extends RelativeLayout{

    TextView txtLiftView;
    TextView txtRightView;
    Button btnRight;

    int Width;
    int Height;
    int layoutHeight;

    public LinearHTwoTxt(Context context,int LayoutHeight,int type) {
        super(context);

        Width=ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);

        setLayoutHeight(LayoutHeight);
        initView(context,type);
    }

    public LinearHTwoTxt(Context context, AttributeSet attrs) {
        super(context, attrs);

        Height=ScreenWH.getNoStatus_bar_Height(context);
        setLayoutHeight(layoutHeight);
    }

    private void initView(Context context,int type){

        RelativeLayout.LayoutParams relative_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,layoutHeight);
        this.setLayoutParams(relative_lay);
        this.setBackgroundColor(getResources().getColor(R.color.colorwhite));

        txtLiftView=new TextView(context);
        txtLiftView.setId(generateViewId());

        RelativeLayout.LayoutParams txtLiftView_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,layoutHeight);
        txtLiftView_lay.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        txtLiftView.setLayoutParams(txtLiftView_lay);
        txtLiftView.setPadding((int)(ScreenWH.getUISpacingY()*1.5),0,0,0);

        setTexttype(txtLiftView);
        this.addView(txtLiftView);

        if(type==0){
            txtRightView = new TextView(context);
            txtRightView.setId(generateViewId());

            RelativeLayout.LayoutParams txtRightView_lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,layoutHeight);
            txtRightView_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            txtRightView.setLayoutParams(txtRightView_lay);
            txtRightView.setPadding(0,0,ScreenWH.getUISpacingY(),0);

            setTexttype(txtRightView);
            this.addView(txtRightView);

        }else if(type==1){
            btnRight = new Button(context);
            btnRight.setId(generateViewId());

            RelativeLayout.LayoutParams btnRight_lay=new RelativeLayout.LayoutParams((int)(double)((Width/6.3)*1.5),(int) (double) ((Height /10)*0.5));
            btnRight_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            btnRight_lay.setMargins(0,ScreenWH.getUISpacingY(),ScreenWH.getUISpacingY()*3,ScreenWH.getUISpacingY());
            btnRight.setLayoutParams(btnRight_lay);
            btnRight.setPadding(0,0,0,0);

            this.addView(btnRight);
        }
    }

    public void setTxtLiftView(String stringtxt){
        txtLiftView.setText(stringtxt);
    }

    public void setTxtRightView(String stringtxt){
        txtRightView.setText(stringtxt);
    }

    public void setLayoutHeight(int layoutHeight){
        this.layoutHeight=layoutHeight;
    }

    public void setTexttype(TextView textView){
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(15.0f);
        textView.setTextColor(getResources().getColor(R.color.colorblack));
    }

    public void setBtnRightBackground(int color){
        btnRight.setBackgroundColor(color);
    }
    public void setBtnRightonClick(OnClickListener l){
        btnRight.setOnClickListener(l);
    }
    public void setBtnRightTag(int id){
        btnRight.setTag(id);
    }
    public void setBtnRightTxt(CharSequence text){
        btnRight.setText(text);
    }
}
