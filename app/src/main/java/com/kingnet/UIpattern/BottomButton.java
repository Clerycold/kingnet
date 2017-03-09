package com.kingnet.UIpattern;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.kingnet.ActivityUserInfo.ActivityUserInfo;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.ScreenWH;
import com.kingnet.ActivityLifeConvenience.LifeConvenience;
import com.kingnet.ActivityHome.MainActivity;
import com.kingnet.MoreInfoCommon;
import com.kingnet.PayManagement;
import com.kingnet.PublicUtilitiesHome;
import com.kingnet.R;
import com.kingnet.PackageFragment.UserHome_package;
import com.kingnet.UserOpinion;
import com.kingnet.ActivityCodeView.ViewBarcode;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class BottomButton extends RelativeLayout {

    private Context context;

    private int Width;
    private int Height;

    public static int ICONTYPE;

    private ArrayList<ButtonImgText> BImgTVArray = new ArrayList<>();
    private int[] Img = new int[]{R.drawable.iconhome,R.drawable.iconpackage, R.drawable.iconbarcode, R.drawable.iconhome, R.drawable.iconmore};
    private int[] txt = new int[]{R.string.bottomhome,R.string.bottompackage,R.string.bottomcode,R.string.bottominfo,R.string.bottommore};

    public BottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(getContext());

        xmlArrange(5);

    }
    private void xmlArrange(int Count){

        RelativeLayout.LayoutParams lay;
        for(int i=0;i<Count;i++){

            ButtonImgText BImgTV = new ButtonImgText(context);
            BImgTV.setId(View.generateViewId());
            BImgTV.setTag(i);
            BImgTV.setOnClickListener(ButtonImgTextListener);
            BImgTV.setBackgroundColor(getResources().getColor(R.color.colorwhite));
            if(i==3){
                setButtonIcon(BImgTV,ICONTYPE);
            }else{
                BImgTV.setImageView(Img[i],(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                BImgTV.setTextView(getResources().getString(txt[i]));
            }
            lay =new RelativeLayout.LayoutParams(
                    (int)(double)Width/5,
                    (int)(double)(Height/10));
//            lay.setMargins(0, );
            lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            if(i==0){
                lay.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }else{
                lay.addRule(RelativeLayout.RIGHT_OF,BImgTVArray.get(i-1).getId());
            }
            BImgTV.setLayoutParams(lay);

            BImgTVArray.add(BImgTV);

            this.addView(BImgTVArray.get(i));
        }

    }

    View.OnClickListener ButtonImgTextListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int Tag = (int)v.getTag();

            switch (Tag)
            {
                case 0:
                    IntentActivity.IntentActivity(getContext(),MainActivity.class);
                    break;
                case 1:
                    IntentActivity.IntentActivity(getContext(),UserHome_package.class);
                    break;
                case 2:
                    IntentActivity.IntentActivity(getContext(),ViewBarcode.class);
                    break;
                case 3:
                    if(ICONTYPE==1|ICONTYPE==5|ICONTYPE==8){
                        IntentActivity.IntentActivity(getContext(),ActivityUserInfo.class);
                    }else if(ICONTYPE==3){
                        IntentActivity.IntentActivity(getContext(),UserOpinion.class);
                    }else if(ICONTYPE==4){
                        IntentActivity.IntentActivity(getContext(),PayManagement.class);
                    }else if(ICONTYPE==6){
                        IntentActivity.IntentActivity(getContext(),PublicUtilitiesHome.class);
                    }else if(ICONTYPE==21){
                        IntentActivity.IntentActivity(getContext(),LifeConvenience.class);
                    }
                    break;
                case 4:
                    IntentActivity.IntentActivity(getContext(),MoreInfoCommon.class);
                    break;
            }
        }
    };

    public void setButtonIcon(ButtonImgText buttonImgText,int type){

        switch(type){
            case 1:
                buttonImgText.setImageView(R.drawable.iconmen,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottominfo));
                break;
            case 2:

                break;
            case 3:
                buttonImgText.setImageView(R.drawable.iconfeedback,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottomuseropinion));
                break;
            case 4:
                buttonImgText.setImageView(R.drawable.iconmoney,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottompaymoney));
                break;
            case 5:
                buttonImgText.setImageView(R.drawable.iconmen,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottominfo));
                break;
            case 6:
                buttonImgText.setImageView(R.drawable.iconreservation,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottompublic));
                break;
            case 7:
                break;
            case 8:
                buttonImgText.setImageView(R.drawable.iconmen,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.bottominfo));
                break;
            case 21:
                buttonImgText.setImageView(R.drawable.iconlife,(int)(double)((Width/5)*0.4),
                        (int)(double)((Height/10)*0.5));
                buttonImgText.setTextView(getResources().getString(R.string.titlelifeconvenience));
        }
    }
}
