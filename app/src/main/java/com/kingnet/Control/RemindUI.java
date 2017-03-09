package com.kingnet.Control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.kingnet.R;

/**
 * Created by clery on 2016/11/23.
 */

public class RemindUI {

    public static Toast setToast(Context context,String str){

        Toast toast = Toast.makeText(context,
                str, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }
    public static AlertDialog.Builder setAlertDialog(Activity activity,final Context context, int title, int Message, int btnString, final String toastString){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(activity);
        alertDialog.setTitle(title)
                .setMessage(Message)
                .setPositiveButton(btnString,null)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        setToast(context.getApplicationContext(),toastString);
                    }
                })
                .show();
        return alertDialog;
    }
}
