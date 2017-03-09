package com.kingnet.UIpattern;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by clery on 2016/12/19.
 */

public class TextLiner extends LinearLayout{

    private TextView txtTitle;
    private TextView txtTime;

    public TextLiner(Context context) {
        super(context);
        initView(context);
    }

    public TextLiner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.txt_linear, this,true);

        this.txtTitle=(TextView)findViewById(R.id.txtTitle);
        this.txtTime=(TextView)findViewById(R.id.txtTime);

        LinearLayout.LayoutParams txtTitle_lay=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtTitle_lay.setMargins(ScreenWH.getUISpacingY()*2,ScreenWH.getUISpacingY(),0,ScreenWH.getUISpacingY()/2);
        txtTitle.setLayoutParams(txtTitle_lay);

        LinearLayout.LayoutParams txtTime_lay=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtTime_lay.setMargins(ScreenWH.getUISpacingY()*2,0,0,ScreenWH.getUISpacingY()*2);
        txtTime.setLayoutParams(txtTime_lay);
    }
    public void setTxtTitleText(String txtString){
        this.txtTitle.setText(txtString);
    }

    public void setTxtTimeText(String txtString){
        this.txtTime.setText(txtString);
    }

    public void setTxtTitleTxtColor(int resId){
        this.txtTitle.setTextColor(resId);
    }
    public void setTxtTimeTxtColor(int resId){
        this.txtTime.setTextColor(resId);
    }
}
