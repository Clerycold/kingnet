package com.kingnet.MyViewpage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HomePageThreeView extends RelativeLayout {

    private View view;

    private int Width;
    private int Height;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private int spacing;

    private RelativeLayout relativeLayout;

    public HomePageThreeView(Context context) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(context);
        spacing=ScreenWH.getUISpacingY();

        initView();
        xmlArrange();

        Bitmap mBitmap = BitmapUtils.decodeSampledBitmapFromResource(getContext().getResources(), R.drawable.ad_3,
                (int) (double) Math.round(((Width) /6.3)*4+spacing),
                (int) (double) Math.round(((Height) /10)*1.6)*2);
        imageButton1.setImageBitmap(mBitmap);
        imageButton1.setScaleType(ImageButton.ScaleType.FIT_CENTER);

        Bitmap mBitmap1 = BitmapUtils.decodeSampledBitmapFromResource(getContext().getResources(), R.drawable.ad_2,
                (int) (double) Math.round(((Width) / 6.3) * 4 + spacing),
                (int) (double) Math.round(((Height) / 10) * 1.6));
        imageButton2.setImageBitmap(mBitmap1);
        imageButton2.setScaleType(ImageButton.ScaleType.FIT_CENTER);

        addView(view);
    }
    private void initView() {

        view = LayoutInflater.from(getContext()).inflate(R.layout.homepagethree, null);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.homethreerelat);
        imageButton1=(ImageButton)view.findViewById(R.id.homethreebutton1);
        imageButton2=(ImageButton)view.findViewById(R.id.homethreebutton2);
    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams r_relatbg_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(Width),
                (int) (double) Math.round(((Height) /10)*5));
        relativeLayout.setLayoutParams(r_relatbg_lay);

        RelativeLayout.LayoutParams r_imagebutton1_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(((Width) /6.3)*4+spacing),
                (int) (double) Math.round(((Height) /10)*1.6)*2);
        r_imagebutton1_lay.addRule(RelativeLayout.BELOW, imageButton1.getId());
        imageButton1.setLayoutParams(r_imagebutton1_lay);

        RelativeLayout.LayoutParams r_imagebutton2_lay = new RelativeLayout.LayoutParams(
                (int) (double) Math.round(((Width) /6.3)*4+spacing),
                (int) (double) Math.round(((Height) /10)*1.6));
        r_imagebutton2_lay.addRule(RelativeLayout.BELOW, imageButton1.getId());
        r_imagebutton2_lay.setMargins(0, spacing, 0, 0);
        imageButton2.setLayoutParams(r_imagebutton2_lay);

    }
}