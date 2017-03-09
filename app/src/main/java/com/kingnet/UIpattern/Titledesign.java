package com.kingnet.UIpattern;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/28.
 */
public class Titledesign extends RelativeLayout{

    private TextView textView;
    int Width;
    int Height;

    public Titledesign(Context context, AttributeSet attrs) {
        super(context, attrs);
        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context.getApplicationContext());
        LayoutInflater.from(context).inflate(R.layout.title_design, this);
        textView=(TextView)findViewById(R.id.titletext);


        RelativeLayout.LayoutParams r_title_lay =new RelativeLayout.LayoutParams((int)(double)Width,(int)(double)Height/10);
        textView.setLayoutParams(r_title_lay);

    }
    public void setTextView(String string){
        textView.setText(string);
    }
}
