package com.dldhk97.mgji_recy.listeners;

import android.view.View;

import com.dldhk97.mgji_recy.UIHandler;

public class OnRefreshListener implements View.OnClickListener{

    public OnRefreshListener(){

    }

    @Override
    public void onClick(View view) {
        try{
            UIHandler.getInstance().showToast("메뉴 새로고침됨");
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert(e.getMessage());
        }
    }
}
