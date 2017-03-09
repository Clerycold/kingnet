package com.kingnet.ActivityUserInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kingnet.ActivityUserInfo.presenter.UserInfoPresenterCompl;
import com.kingnet.ActivityUserInfo.view.UserInfoView;
import com.kingnet.Control.RemindUI;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;
import com.kingnet.UIpattern.LinearHTwoTxt;
import com.kingnet.UIpattern.Titledesign;

/**
 * Created by clery on 2016/12/15.
 */

public class ActivityUserInfo extends Activity implements View.OnClickListener,UserInfoView
        ,DialogInterface.OnClickListener,DialogInterface.OnDismissListener{

    private Titledesign titledesign;

    private LayoutInflater mInflater;

    private LinearHTwoTxt linearHTwoTxt;
    private TextView userTxtNameEx;

    private TextView userTxtExPackOnLY;
    private TextView userTxtMessageEx;

    private TextView userTxtId,userTxtIdName,userTxtCount,userTxtPhone,userTxtMoreEx;

    private ScrollView userInfoScrll;
    UserInfoPresenterCompl userInfoPresenterCompl;

    private int Width;
    private int Height;

    private EditText UserInto;

    AlertDialog UserPrivateDialog = null;
    AlertDialog UserIntoDialog=null;

    private RelativeLayout scrollrel;
    private LinearHTwoTxt linearHTwoTxtMessage;
    private LinearHTwoTxt linearHTwobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityuserinfo);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(getApplicationContext());

        initView();
        xmlArrange();

        userInfoPresenterCompl=new UserInfoPresenterCompl(getApplicationContext(),this);
        showUserPrivatePack(userInfoPresenterCompl.CheckPrivatePack());

        mInflater = LayoutInflater.from(this);
        titledesign.setTextView(getResources().getString(R.string.titleuserinfo));

        linearHTwoTxt.setTxtLiftView("包裹收件人姓名");
        linearHTwoTxt.setTxtRightView(userInfoPresenterCompl.CheckUserName());
        userTxtNameEx.setText("一支手機僅能設定一個姓名，如家中其他成員需享有e化服務，請攜帶手機至管理室開通。");

        linearHTwobtn.setTxtLiftView("包裹僅限本人領取");
        userTxtExPackOnLY.setText("若隱私功能設定啟用，您的包裹將僅限於本人持手機領取，無法由他人代領；包裹相關等資訊也僅顯示於您本人手機。");

        linearHTwoTxtMessage.setTxtLiftView("訊息通知開啟中");
        userTxtMessageEx.setText("請確認您的通知是否開啟，若設定關閉，您的手機將無法收到通知。");

        userTxtId.setText("我的戶別");
        userTxtIdName.setText("今網企業大樓(9H)220號9F-1");
        userTxtCount.setText("點數");
        userTxtPhone.setText("已開通 手機：1/載具：0");
        userTxtMoreEx.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        userTxtMoreEx.setText("點我看APP使用說明>>");

        linearHTwoTxt.setOnClickListener(this);
        linearHTwoTxtMessage.setOnClickListener(this);
        linearHTwobtn.setBtnRightonClick(this);
    }

    private void initView() {

        titledesign = (Titledesign)findViewById(R.id.titledesign);

        userInfoScrll=(ScrollView)findViewById(R.id.userInfoScrll);
        scrollrel=(RelativeLayout) findViewById(R.id.scrollral);

        linearHTwoTxt=new LinearHTwoTxt(getApplicationContext(),(int) (double) ((Height /10)*0.8),0);
        linearHTwoTxt.setId(View.generateViewId());
        linearHTwoTxt.setTag(0);

        userTxtNameEx=(TextView)findViewById(R.id.userTxtNameEx);

        linearHTwobtn = new LinearHTwoTxt(getApplicationContext(),(int) (double) ((Height /10)*0.8),1);
        linearHTwobtn.setId(View.generateViewId());
        linearHTwobtn.setBtnRightTag(2);
        userTxtExPackOnLY=(TextView)findViewById(R.id.userTxtExPackOnLY);

        linearHTwoTxtMessage=new LinearHTwoTxt(getApplicationContext(),(int) (double) ((Height /10)*0.8),0);
        linearHTwoTxtMessage.setId(View.generateViewId());
        linearHTwoTxtMessage.setTag(1);
        userTxtMessageEx=(TextView)findViewById(R.id.userTxtMessageEx);

        userTxtId=(TextView)findViewById(R.id.userTxtId);
        userTxtIdName=(TextView)findViewById(R.id.userTxtIdName);
        userTxtCount=(TextView)findViewById(R.id.userTxtCount);
        userTxtPhone=(TextView)findViewById(R.id.userTxtPhone);
        userTxtMoreEx=(TextView)findViewById(R.id.userTxtMoreEx);
    }

    private void xmlArrange() {

        RelativeLayout.LayoutParams r_scroll_lay =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double)((Height/10)*8.1));
        r_scroll_lay.setMargins(0,(int) (double)(Height/10),0,0);
        userInfoScrll.setLayoutParams(r_scroll_lay);

        RelativeLayout.LayoutParams userTxtNameUser_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtNameUser_lay.setMargins(0,(ScreenWH.getUISpacingY()*2),0,0);
        linearHTwoTxt.setLayoutParams(userTxtNameUser_lay);
        scrollrel.addView(linearHTwoTxt);

        RelativeLayout.LayoutParams userTxtNameEx_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtNameEx_lay.addRule(RelativeLayout.BELOW,linearHTwoTxt.getId());
        userTxtNameEx.setLayoutParams(userTxtNameEx_lay);
        userTxtNameEx.setPadding((int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()/2,(int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()*4);

        RelativeLayout.LayoutParams userTxtPackOnly_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtPackOnly_lay.addRule(RelativeLayout.BELOW,userTxtNameEx.getId());
        linearHTwobtn.setLayoutParams(userTxtPackOnly_lay);
        scrollrel.addView(linearHTwobtn);

        RelativeLayout.LayoutParams userTxtExPackOnly_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtExPackOnly_lay.addRule(RelativeLayout.BELOW,linearHTwobtn.getId());
        userTxtExPackOnLY.setLayoutParams(userTxtExPackOnly_lay);
        userTxtExPackOnLY.setPadding((int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()/2,(int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()*4);

        RelativeLayout.LayoutParams userTxtMessage_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (double) ((Height /10)*0.8));
        userTxtMessage_lay.addRule(RelativeLayout.BELOW,userTxtExPackOnLY.getId());
        linearHTwoTxtMessage.setLayoutParams(userTxtMessage_lay);
        scrollrel.addView(linearHTwoTxtMessage);

        RelativeLayout.LayoutParams userTxtMessageEx_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtMessageEx_lay.addRule(RelativeLayout.BELOW,linearHTwoTxtMessage.getId());
        userTxtMessageEx.setLayoutParams(userTxtMessageEx_lay);
        userTxtMessageEx.setPadding((int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()/2,(int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()*4);

        RelativeLayout.LayoutParams userTxtId_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtId_lay.addRule(RelativeLayout.BELOW,userTxtMessageEx.getId());
        userTxtId.setLayoutParams(userTxtId_lay);
        userTxtId.setPadding((int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()/2,(int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY());

        RelativeLayout.LayoutParams userTxtIdName_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) ((Height /10)*0.7));
        userTxtIdName_lay.addRule(RelativeLayout.BELOW,userTxtId.getId());
        userTxtIdName.setLayoutParams(userTxtIdName_lay);
        userTxtIdName.setPadding((int)(ScreenWH.getUISpacingY()*1.5),0,0,0);
        userTxtIdName.setGravity(Gravity.CENTER_VERTICAL);

        RelativeLayout.LayoutParams userTxtCount_lay=new RelativeLayout.LayoutParams((int)(double)((Width/6.3)),(int) (double) ((Height /10)*0.4));
        userTxtCount_lay.addRule(RelativeLayout.ALIGN_TOP,userTxtIdName.getId());
        userTxtCount_lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        userTxtCount_lay.setMargins(0,ScreenWH.getUISpacingY(),ScreenWH.getUISpacingY()*3,ScreenWH.getUISpacingY());
        userTxtCount.setLayoutParams(userTxtCount_lay);
        userTxtCount.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams userTxtPhone_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (double) ((Height /10)*0.4));
        userTxtPhone_lay.addRule(RelativeLayout.BELOW,userTxtIdName.getId());
        userTxtPhone.setLayoutParams(userTxtPhone_lay);
        userTxtPhone.setPadding((int)(ScreenWH.getUISpacingY()*1.5),0,0,0);
        userTxtPhone.setGravity(Gravity.CENTER_VERTICAL);

        RelativeLayout.LayoutParams userTxtMoreEx_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userTxtMoreEx_lay.addRule(RelativeLayout.BELOW,userTxtPhone.getId());
        userTxtMoreEx.setLayoutParams(userTxtMoreEx_lay);
        userTxtMoreEx.setPadding((int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY(),(int)(ScreenWH.getUISpacingY()*1.5),ScreenWH.getUISpacingY()*4);
    }

    @Override
    public void onClick(View v) {
        if(v.getTag()!=null){
            int Tag = (int)v.getTag();
            switch (Tag) {
                case 0:
                    UserIntoDialog=new AlertDialog.Builder(ActivityUserInfo.this)
                            .setOnDismissListener(null)
                            .setTitle("今網行動管家訊息")
                            .setMessage("為保障您的權益，請務必填寫正確姓名，以利日後確實收到個人包裹等相關資訊")
                            .setPositiveButton("確定", null)
                            .setOnDismissListener(this)
                            .show();
                    break;
                case 1:
                    startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName())));
                    break;
                case 2:
                    String DialogTitle = null;
                    String DialogMessage = null;
                    if(!userInfoPresenterCompl.CheckPrivatePack()){
                        DialogTitle="啟用隱私設定";
                        DialogMessage="您的包裹將僅限於本人持手機個人識別條碼領取，無法由他人代領；包裹相關等資料也僅顯示於您本人手機。";
                    }
                    else{
                        DialogTitle="取消隱私設定";
                        DialogMessage="取消隱私設定後，您近三日的歷史私人包裹相關資訊將於其他已開通手機顯示。如要針對單一包裹取消隱私，請至「我的包裹」列表中取消特定包裹隱私，謝謝。";
                    }
                    UserPrivateDialog=new AlertDialog.Builder(ActivityUserInfo.this)
                            .setOnDismissListener(null)
                            .setTitle(DialogTitle)
                            .setMessage(DialogMessage)
                            .setPositiveButton("確定", this)
                            .setNegativeButton(R.string.button_cancel, null)
                            .show();
                    break;
            }
        }
    }

    @Override
    public void showUserName(String userName) {
        linearHTwoTxt.setTxtRightView(userName);
    }

    @Override
    public void showUserPrivatePack(Boolean isPrivate) {
        if(isPrivate){
            linearHTwobtn.setBtnRightBackground(getResources().getColor(R.color.colorGreen));
            linearHTwobtn.setBtnRightTxt("已啟用");
        }else{
            linearHTwobtn.setBtnRightBackground(getResources().getColor(R.color.colorGrayDark));
            linearHTwobtn.setBtnRightTxt("尚未啟用");
        }
    }

    @Override
    public String getUserInto() {
        return UserInto.getText().toString();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(dialog==UserIntoDialog){
            switch(which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (userInfoPresenterCompl.CheckUserInto(getUserInto())) {
                        RemindUI.setToast(getApplicationContext(), "請勿輸入空白");
                    } else {
                        userInfoPresenterCompl.ChangeUserName(UserInto.getText().toString());
                    }
                    break;
            }
        }else if(dialog==UserPrivateDialog){
            switch(which) {
                case DialogInterface.BUTTON_POSITIVE:
                    userInfoPresenterCompl.PrivatePack();
                    break;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(UserIntoDialog!=null){
            View root;
            root = mInflater.inflate(R.layout.alertdialog_use, null);
            UserInto=(EditText) root.findViewById(R.id.alertEdit);

            RelativeLayout.LayoutParams r_edit_lay=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (double) Height / 10);
            r_edit_lay.setMargins(0,(int) (double) Height /15,0,0);
            UserInto.setLayoutParams(r_edit_lay);

            UserIntoDialog=new AlertDialog.Builder(ActivityUserInfo.this)
                    .setTitle("登記/變更收件人姓名")
                    .setPositiveButton("確定", this)
                    .setNegativeButton(R.string.button_cancel, null)
                    .setView(root)
                    .show();
        }
    }
}
