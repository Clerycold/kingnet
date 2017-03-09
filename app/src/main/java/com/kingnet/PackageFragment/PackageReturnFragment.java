package com.kingnet.PackageFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingnet.R;
import com.kingnet.UIpattern.ButtonImgText;

/**
 * Created by Administrator on 2016/11/2.
 */
public class PackageReturnFragment extends PackBaseFragment {
    View rootView;


    ButtonImgText packimagebg;


    DisplayMetrics Metrics = Resources.getSystem().getDisplayMetrics();
    float Width= Metrics.widthPixels;
    float Height=Metrics.heightPixels;


    public static PackageReturnFragment newInstance(String title, int indicatorColor, int dividerColor) {
        PackageReturnFragment f = new PackageReturnFragment();
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

        buttonListener();



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

                        break;
                }
            }
        };

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