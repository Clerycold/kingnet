package com.kingnet.MyMessageFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kingnet.PackageFragment.PackageMainFragment;
import com.kingnet.R;
import com.kingnet.UIpattern.ButtonImgText;

/**
 * Created by clery on 2016/11/8.
 */

public class MessageSystemFrag extends MessageBaseFragment{
    View rootView;


    ButtonImgText packimagebg;


    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;


    public static MessageSystemFrag newInstance(String title, int indicatorColor, int dividerColor) {
        MessageSystemFrag f = new MessageSystemFrag();
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
}