package com.kingnet.MyViewpage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/25.
 */
public class PageOneView extends RelativeLayout{

    private View view;
    private  ImageView imageView;
    private  TextView textView;
    private TextView textView2;

    private int Width ;
    private int Height;

    public PageOneView(Context context,int ImgresId,int MidTxtredId,int BottomTxtresId) {
        super(context);

        Width=ScreenWH.getScreenWidth();
        Height=ScreenWH.getScreenHidth();

        initView();
        xmlArrange();

        setBgImgView(ImgresId);
        setMidTxt(MidTxtredId);
        setBottomTxt(BottomTxtresId);

        addView(view);
    }
    private void initView(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.page_content, null);
        imageView=(ImageView) view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.text);
        textView2=(TextView) view.findViewById(R.id.text2);
    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_top_img = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) Height);
        r_top_img.addRule(ALIGN_PARENT_TOP);
        imageView.setLayoutParams(r_top_img);

        RelativeLayout.LayoutParams r_bottom_text =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) ((Height/10)*0.5)+ScreenWH.getUISpacingY());
        r_bottom_text.setMargins(0,(int) (double) ((Height/10)*8.2),0,0);
        textView.setLayoutParams(r_bottom_text);

        RelativeLayout.LayoutParams r_bottom2_text =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) ((Height/10)*1.5));
        r_bottom2_text.addRule(RelativeLayout.BELOW,textView.getId());
        textView2.setLayoutParams(r_bottom2_text);
    }

    public void setBgImgView(int resId){
        Drawable drawable = BitmapUtils.decodeSampledDrawableFromResource(getContext().getResources(), resId, Width, Height);
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageButton.ScaleType.CENTER_INSIDE);
    }
    public void setMidTxt(int resid){
        textView.setText(resid);
    }
    public void setBottomTxt(int resid){
        textView2.setText(resid);
    }
}
