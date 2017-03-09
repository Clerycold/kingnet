package com.kingnet.Newfragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.ActivityOpenUser.OpenCustomer;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/27.
 */
public class BarcodeFtagment  extends BaseFragment {

    private View rootView;

    private ImageView imageView;
    private TextView txtResult;
    private TextView txtResult2;
    private Button imageButton;

    private int Width;
    private int Height;

    public static Bitmap BRbitmap;

    public static BarcodeFtagment newInstance(String title, int indicatorColor, int dividerColor) {
        BarcodeFtagment f = new BarcodeFtagment();
        f.setTitle(title);
        f.setIndicatorColor(indicatorColor);
        f.setDividerColor(dividerColor);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getScreenHidth();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.new_frag_common, container, false);

        initView();
        xmlArrange();

        buttonListener();

        txtResult.setText(R.string.barcodetextcontent);
        txtResult2.setText(R.string.barcodetextcontent2);
        imageButton.setText(R.string.othercontent);

        if(BRbitmap!=null){
            imageView.setImageBitmap(BRbitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return rootView;
    }

    private void initView(){
        imageView = (ImageView) rootView.findViewById(R.id.barcodeImage);
        txtResult = (TextView)rootView.findViewById(R.id.barcodetextview);
        txtResult2= (TextView)rootView.findViewById(R.id.barcodetextview2);
        imageButton = (Button) rootView.findViewById(R.id.otherbutton);
    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams barcode_text_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        barcode_text_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        barcode_text_lay.setMargins(0,(int) (double)((Height/10)*0.6),0,0);
        txtResult.setLayoutParams(barcode_text_lay);

        RelativeLayout.LayoutParams barcode_text2_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        barcode_text2_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        barcode_text2_lay.addRule(RelativeLayout.BELOW,txtResult.getId());
        txtResult2.setLayoutParams(barcode_text2_lay);

        RelativeLayout.LayoutParams barcode_img_lay=new RelativeLayout.LayoutParams((int) (double) ((Width/6.3)*4.6),(int) (double) Math.round((Height / 10)*1.5));
        barcode_img_lay.addRule(RelativeLayout.BELOW,txtResult2.getId());
        barcode_img_lay.setMargins(0,(int) (double)((Height/10)*0.3),0,0);
        barcode_img_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(barcode_img_lay);

        RelativeLayout.LayoutParams barcode_imgbtn_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,(int) (double) Math.round((Height / 20)));
        barcode_imgbtn_lay.addRule(RelativeLayout.BELOW, imageView.getId());
        barcode_imgbtn_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        barcode_imgbtn_lay.setMargins(0,(int) (double)((Height/10)),0,0);
        imageButton.setLayoutParams(barcode_imgbtn_lay);
    }
    /**
     * 按鈕監聽
     */
    private void buttonListener(){
        /**
         * 取消Activity轉場動化 overridePendingTransition(0, 0);
         * 但此專案 運動style將整個轉場動畫 設置為0
         */
        View.OnClickListener onClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.otherbutton:
                        IntentActivity.IntentActivity(getContext().getApplicationContext(),OpenCustomer.class);
                        break;
                }
            }
        };

        imageButton.setOnClickListener(onClickListener);
    }
}