package com.kingnet.MyMessageFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kingnet.R;
import com.kingnet.UIpattern.ButtonImgText;

/**
 * Created by clery on 2016/11/8.
 */

public class MessageGoodFrag extends MessageBaseFragment{
    View rootView;


    ButtonImgText packimagebg;


    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;


    public static MessageGoodFrag newInstance(String title, int indicatorColor, int dividerColor) {
        MessageGoodFrag f = new MessageGoodFrag();
        f.setTitle(title);
        f.setIndicatorColor(indicatorColor);
        f.setDividerColor(dividerColor);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * 先用同一個 如果後來版面不同在做修改
         */
        rootView =inflater.inflate(R.layout.pack_frag_common, container, false);

        initView();
        xmlArrange();


        return rootView;
    }

    private void initView(){

        packimagebg = (ButtonImgText)rootView.findViewById(R.id.packimagebg);

    }

    private void xmlArrange(){

        RelativeLayout.LayoutParams pack_imagebg_lay=new RelativeLayout.LayoutParams((int) (double) (Width/5),(int) (double) (Width/5));
        pack_imagebg_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        pack_imagebg_lay.addRule(RelativeLayout.CENTER_VERTICAL);
        packimagebg.setLayoutParams(pack_imagebg_lay);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}