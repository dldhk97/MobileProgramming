package com.dldhk97.mgji_recy.listeners;

import android.view.View;

import com.dldhk97.mgji_recy.DataController;
import com.dldhk97.mgji_recy.UIHandler;

public class OnMoreListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        try{
            DataController.getInstance().more();
            UIHandler.getInstance().showToast("식단을 더 불러왔습니다.");
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[OnMoreListener.onClick]\n"+ e.getMessage());
        }
    }
}
