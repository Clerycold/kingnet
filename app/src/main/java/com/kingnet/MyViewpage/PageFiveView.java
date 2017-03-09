package com.kingnet.MyViewpage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kingnet.Control.ScreenWH;
import com.kingnet.Control.TextUtils;
import com.kingnet.GetShareMemory.SaveData;
import com.kingnet.ActivityHome.MainActivity;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/25.
 */
public class PageFiveView extends RelativeLayout{

    private View view;
    private ImageView imageView;
    private ImageView button_bg;
    private TextView textView;
    private Button button;

    SaveData saveData;

    private int Width;
    private int Height;


    public PageFiveView(Context context) {
        super(context);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getScreenHidth();

        initView();
        xmlArrange();

//        drawable=BitmapUtils.decodeSampledDrawableFromResource(getContext().getResources(), R.drawable.welcome05, Width, Height);
//        imageView.setImageDrawable(drawable);
//        imageView.setScaleType(ImageButton.ScaleType.FIT_CENTER);

        TextUtils.setBitmap(getContext().getResources(), R.drawable.welcome05, Width, Height, new TextUtils.CallbackDrawable() {
            @Override
            public void onResponse(Drawable response) {
                imageView.setImageDrawable(response);
                imageView.setScaleType(ImageButton.ScaleType.FIT_CENTER);
            }
        });

        button_bg.setBackgroundResource(R.color.colorwhite);

        textView.setText(R.string.homePageView5);

        button.setText(R.string.start_Experience);
        button.setBackgroundResource(R.drawable.round_corner_bg);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData=new SaveData(getContext());
                saveData.saveBooleanData("myActivityName","isFirst",false);
                MainActivity.pageViewdiss();
            }
        });

        addView(view);
    }
    private void initView(){
        view = LayoutInflater.from(getContext()).inflate(R.layout.page_content_button, null);
        imageView=(ImageView) view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.text);
        button_bg=(ImageView)view.findViewById(R.id.button_bg);
        button=(Button)view.findViewById(R.id.isFirstButton);
    }
    private void xmlArrange(){
        RelativeLayout.LayoutParams r_top_img = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round(Height));
        r_top_img.addRule(ALIGN_PARENT_TOP);
        imageView.setLayoutParams(r_top_img);

        RelativeLayout.LayoutParams r_bottom_text =new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) ((Height/10)*0.5)+ScreenWH.getUISpacingY());
        r_bottom_text.setMargins(0,(int) (double) ((Height/10)*8.1),0,0);
        textView.setLayoutParams(r_bottom_text);

        RelativeLayout.LayoutParams r_button_bg = new RelativeLayout.LayoutParams((int) (double) Width, (int) (double) Math.round(Height/10));
        r_button_bg.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,textView.getId());
        button_bg.setLayoutParams(r_button_bg);

        RelativeLayout.LayoutParams r_button = new RelativeLayout.LayoutParams((int) (double) Width/3, (int) (double) Math.round((Height/10)*0.6));
        r_button.addRule(RelativeLayout.ALIGN_TOP,button_bg.getId());
        r_button.setMargins(0,ScreenWH.getUISpacingY(),0,0);
        r_button.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button.setPadding(0,0,0,0);
        button.setLayoutParams(r_button);

    }
}