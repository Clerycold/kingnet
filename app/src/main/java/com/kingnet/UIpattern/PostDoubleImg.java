package com.kingnet.UIpattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.TextUtils;
import com.kingnet.R;

/**
 * Created by clery on 2016/12/21.
 */

public class PostDoubleImg extends RelativeLayout{

    ImageView imageViewdown;
    ImageView imageViewup;

    int Width;
    int Height;

    Context context;

    public PostDoubleImg(Context context) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);

        this.context=context;

        initView();
    }

    public PostDoubleImg(Context context, AttributeSet attrs){
        super(context, attrs);
        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);

        this.context=context;

        initView();
    }

    private void initView() {

        imageViewdown = new ImageView(context);
        imageViewdown.setId(generateViewId());

        RelativeLayout.LayoutParams imageViewdown_lay=new RelativeLayout.LayoutParams((int)(double)(Height/10), (int)(double)(Height/10));
        imageViewdown.setLayoutParams(imageViewdown_lay);
        imageViewdown.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(context.getResources(),R.drawable.post_package,(int)(double)(Height/10),(int)(double)(Height/10)));
        this.addView(imageViewdown);

    }

    public void setImageViewDownImg(int ImgResId){
        TextUtils.setBitmap(context.getApplicationContext().getResources(),ImgResId,(int)(double)(Height/10),(int)(double)(Height/10), new TextUtils.CallbackDrawable() {
            @Override
            public void onResponse(Drawable response) {
                imageViewdown.setImageDrawable(response);
            }
        });
    }
    public void setImageViewUpImg(int ImgResId,int type){

        imageViewup = new ImageView(context);

        RelativeLayout.LayoutParams imageViewup_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,(int) (double) Height/20);
        imageViewup_lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        imageViewup_lay.addRule(RelativeLayout.ALIGN_RIGHT,imageViewdown.getId());
        imageViewup.setLayoutParams(imageViewup_lay);
        this.addView(imageViewup);

        switch (type){
            case 0:
                imageViewup.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(context.getResources(),ImgResId,(int)(double) Height/20,(int)(double) Height/20));
                break;
            case 1:
                imageViewup.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResource(context.getResources(),ImgResId,(int)(double)((Height/10)*1.1),(int)(double) Height/20));
                break;
        }
    }
}
