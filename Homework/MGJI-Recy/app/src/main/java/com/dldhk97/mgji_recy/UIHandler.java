package com.dldhk97.mgji_recy;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class UIHandler {
    private static UIHandler _Instance;
    private MainActivity mainActivity;

    UIHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        _Instance = this;
    }

    public static UIHandler getInstance(){
        return _Instance;
    }

    public void showToast(final String msg){
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(mainActivity, msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void showAlert(final String msg){
        final AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setMessage(msg);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alert.show();
            }
        });
    }
}
