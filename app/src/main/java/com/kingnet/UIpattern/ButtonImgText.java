package com.kingnet.UIpattern;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by clery on 2016/11/7.
 */

public class ButtonImgText extends RelativeLayout{

    private ImageView imageView;
    private TextView textView;

    public ButtonImgText(Context context) {
        super(context);

        initView(context);
    }

    public ButtonImgText(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);

    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.img_text_bt, this,true);

        this.imageView=(ImageView)findViewById(R.id.imgview);
        this.textView=(TextView)findViewById(R.id.textview);

        this.setClickable(true);
        this.setFocusable(true);
    }

    public void setImageView(int resourceID,int width,int height){

        Drawable drawable;
        drawable=BitmapUtils.decodeSampledDrawableFromResource(getContext().getResources(),
                resourceID, width,
                height);

        this.imageView.setImageDrawable(drawable);
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

    }
    public void setTextView(String text){
        this.textView.setText(text);

    }
}
