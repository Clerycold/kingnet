package com.kingnet.UIpattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.TextUtils;
import com.kingnet.R;

/**
 * Created by clery on 2016/12/21.
 */

public class PostDitalTxt extends RelativeLayout{

    TextView year;
    ImageView postTraceimg;
    TextView adress;
    TextView type;
    TextView posttypestuff;
    TextView txtexplanation;

    int Width;
    int Height;

    private static float TXTBIGSIZE =17.0f;
    private static float TXTSMALLSIZE =15.0f;

    Context context;

    public PostDitalTxt(Context context) {
        super(context);

        Width = ScreenWH.getScreenWidth();
        Height = ScreenWH.getNoStatus_bar_Height(context);

        this.context=context;

        initView();

    }

    public PostDitalTxt(Context context, AttributeSet attrs){
        super(context, attrs);

        Width = ScreenWH.getScreenWidth();
        Height = ScreenWH.getNoStatus_bar_Height(context);

        this.context=context;

        initView();
    }

    private void initView() {

        year = new TextView(context);
        adress = new TextView(context);
        type = new TextView(context);

        year.setId(generateViewId());
        adress.setId(generateViewId());
        type.setId(generateViewId());

        this.setGravity(Gravity.CENTER_VERTICAL);

        RelativeLayout.LayoutParams post_yeardata_lay= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        post_yeardata_lay.setMargins(0,0,0,ScreenWH.getUISpacingY());
        year.setLayoutParams(post_yeardata_lay);
        year.setTextSize(TXTBIGSIZE);
        year.setTextColor(getResources().getColor(R.color.colorBluewater));
        this.addView(year);

        RelativeLayout.LayoutParams post_adress_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        post_adress_lay.addRule(RelativeLayout.BELOW,year.getId());
        adress.setLayoutParams(post_adress_lay);
        setTxtType(adress);
        this.addView(adress);

        RelativeLayout.LayoutParams post_type_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        post_type_lay.addRule(RelativeLayout.BELOW,adress.getId());
        type.setLayoutParams(post_type_lay);
        setTxtType(type);
        this.addView(type);

    }

    private void setTxtType(TextView textView){
        textView.setTextSize(TXTSMALLSIZE);
        textView.setTextColor(getResources().getColor(R.color.colorblack));
    }

    public void setYearTxt(CharSequence text){
        year.setText(text);
    }

    public void setPostTraceImg(int ImgResId,int type){

        postTraceimg = new ImageView(context);

        RelativeLayout.LayoutParams posttrace_img_lay = new RelativeLayout.LayoutParams((int)(double)Width/5,(int)(double) Width/18);
        posttrace_img_lay.addRule(RelativeLayout.RIGHT_OF,year.getId());
        posttrace_img_lay.addRule(RelativeLayout.ALIGN_TOP,year.getId());
        postTraceimg.setLayoutParams(posttrace_img_lay);
        this.addView(postTraceimg);

        switch (type){
            case 0:
                postTraceimg.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(context.getResources(),ImgResId,(int)(double) Width/20,(int)(double) Width/18));
                break;
            case 1:
                postTraceimg.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(context.getResources(),ImgResId,(int)(double)Width/5,(int)(double) Width/18));
                break;
        }

    }

    public void setAdressTxt(CharSequence text){
        adress.setText(text);
    }

    public void setTypeTxt(CharSequence text){
        type.setText(text);
    }

    public void setPosttypestuffTxt(String stuffStrimg){

        posttypestuff = new TextView(context);
        posttypestuff.setId(generateViewId());

        RelativeLayout.LayoutParams post_typestuff_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        post_typestuff_lay.addRule(RelativeLayout.BELOW,adress.getId());
        post_typestuff_lay.addRule(RelativeLayout.RIGHT_OF,type.getId());
        post_typestuff_lay.setMargins(ScreenWH.getUISpacingY(),0,0,0);
        posttypestuff.setLayoutParams(post_typestuff_lay);
        posttypestuff.setPadding(10,0,10,0);
        posttypestuff.setTextSize(TXTSMALLSIZE);
        posttypestuff.setTextColor(getResources().getColor(R.color.colorwhite));
        this.addView(posttypestuff);

        switch (stuffStrimg){
            case "冷藏":
                posttypestuff.setBackgroundColor(context.getResources().getColor(R.color.colorMainlight));
                posttypestuff.setText(stuffStrimg);
                break;
            case "冷凍":
                posttypestuff.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
                posttypestuff.setText(stuffStrimg);
                break;
        }
    }

    public void setExplanationTxt(CharSequence text){

        txtexplanation = new TextView(context);

        RelativeLayout.LayoutParams post_txtExplanation_lay=new RelativeLayout.LayoutParams((int)(double)((Width/6.3)*3.2),(int) (double) Height/20);
        post_txtExplanation_lay.addRule(RelativeLayout.BELOW,posttypestuff.getId());
        post_txtExplanation_lay.setMargins(0,ScreenWH.getUISpacingY(),0,0);
        txtexplanation.setLayoutParams(post_txtExplanation_lay);
        txtexplanation.setBackgroundColor(getResources().getColor(R.color.colorgray));
        txtexplanation.setGravity(Gravity.CENTER);
        setTxtType(txtexplanation);
        this.addView(txtexplanation);

        txtexplanation.setText(text);
    }
}
