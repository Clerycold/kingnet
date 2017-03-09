package com.kingnet.ActivityOpenUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kingnet.ActivityHome.MainActivity;
import com.kingnet.ActivityOpenUser.presenter.UserOpenPresenter;
import com.kingnet.ActivityOpenUser.presenter.UserOpenPresenterCompl;
import com.kingnet.ActivityOpenUser.view.UserOpenView;
import com.kingnet.BestBitmap.BitmapUtils;
import com.kingnet.CommonProblem;
import com.kingnet.Control.IntentActivity;
import com.kingnet.Control.RemindUI;
import com.kingnet.Control.ScreenWH;
import com.kingnet.R;

/**
 * Created by Administrator on 2016/10/27.
 */
public class OpenCustomer extends Activity implements UserOpenView,View.OnClickListener{

    private ImageView imageView;
    private EditText editText;
    private Button button_open;
    private Button button_help;
    private TextView txt_Explanation;

    private int Width;
    private int Height;

    UserOpenPresenter userOpenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 無標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 直式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.opencustomer);

        Width= ScreenWH.getScreenWidth();
        Height=ScreenWH.getNoStatus_bar_Height(this);

        initView();
        xmlArrange();

        userOpenPresenter=new UserOpenPresenterCompl(this);

        Bitmap bitmap= BitmapUtils.decodeSampledBitmapFromResource(getResources(),R.drawable.imageviewlogo,(int) (double) Width,(int) (double) ((Height/10)*2.5));

        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageButton.ScaleType.FIT_CENTER);

        txt_Explanation.setText(getResources().getString(R.string.openexplanation));
        txt_Explanation.setAlpha(0.5f);

        button_open.setOnClickListener(this);
        button_help.setOnClickListener(this);

    }
    private void initView(){

        imageView=(ImageView)findViewById(R.id.opencustomer_img);
        editText=(EditText)findViewById(R.id.opencustomer_edit);
        button_open=(Button)findViewById(R.id.opencustomer_open);
        button_help=(Button)findViewById(R.id.opencustomer_help);
        txt_Explanation=(TextView)findViewById(R.id.openCustomer_Explanation);

    }
    private void xmlArrange(){

        RelativeLayout.LayoutParams open_img_lay=new RelativeLayout.LayoutParams((int) (double) Width,(int) (double) ((Height/10)*2.5));
        open_img_lay.setMargins(0, (int) (double) (Height / 10) * 2, 0, ScreenWH.getUISpacingY()*2);
        open_img_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        imageView.setLayoutParams(open_img_lay);

        RelativeLayout.LayoutParams opne_edit_lay=new RelativeLayout.LayoutParams((int) (double) ((Width/10)*8),(int) (double) Math.round((Height / 15)));
        opne_edit_lay.addRule(RelativeLayout.BELOW,imageView.getId());
        opne_edit_lay.setMargins((int) (double)(Width/10),0,0,0);
        editText.setLayoutParams(opne_edit_lay);

        RelativeLayout.LayoutParams open_openbt_lay=new RelativeLayout.LayoutParams((int) (double) ((Width/10)*8),(int) (double) Math.round((Height / 15)));
        open_openbt_lay.addRule(RelativeLayout.BELOW,editText.getId());
        open_openbt_lay.setMargins((int) (double) (Width / 10), ScreenWH.getUISpacingY()*2, 0,0);
        button_open.setLayoutParams(open_openbt_lay);

        RelativeLayout.LayoutParams open_txt_lay=new RelativeLayout.LayoutParams((int) (double) ((Width/10)*8),(int) (double) Math.round((Height / 15)));
        open_txt_lay.addRule(RelativeLayout.BELOW,button_open.getId());
        open_txt_lay.setMargins((int) (double) (Width / 10), ScreenWH.getUISpacingY()*2, 0,0);
        txt_Explanation.setLayoutParams(open_txt_lay);

        RelativeLayout.LayoutParams open_helpbt_lay=new RelativeLayout.LayoutParams((int) (double) ((Width/4)),(int) (double) Math.round((Height / 10)));
        open_helpbt_lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        open_helpbt_lay.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button_help.setLayoutParams(open_helpbt_lay);
    }

    @Override
    public void onClear() {
        editText.setText("");
    }

    @Override
    public void onOpenResult(int result) {
        switch (result){
            case -1:
                RemindUI.setAlertDialog(OpenCustomer.this,getApplicationContext(),
                        R.string.openadtitle,
                        R.string.openadnonumber,
                        R.string.openmakesure,
                        getResources().getString(R.string.openagain));
                break;
            case 0:
                new AlertDialog.Builder(OpenCustomer.this)
                        .setTitle(R.string.openadtitle)
                        .setMessage("開通正確 如有問題請洽 管理室")
                        .setPositiveButton(R.string.openmakesure,null)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                IntentActivity.IntentActivity(getApplicationContext(), MainActivity.class);
                            }
                        })
                        .show();
                    break;
            case 1:

                RemindUI.setAlertDialog(OpenCustomer.this,getApplicationContext(),
                        R.string.openadtitle,
                        R.string.openmislength,
                        R.string.openmakesure,
                        getResources().getString(R.string.openagainnumber));
                break;
        }
        userOpenPresenter.Clear();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.opencustomer_help:
                IntentActivity.IntentActivity(getApplicationContext(),CommonProblem.class);
                break;
            case R.id.opencustomer_open:
                userOpenPresenter.CheckUserOpenId(editText.getText().toString());
        }
    }
}
