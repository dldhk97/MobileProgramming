package com.dldhk97.mgji_recy.listener;

import android.view.View;
import android.widget.AdapterView;

import com.dldhk97.mgji_recy.UIHandler;

public class OnCafeteriaTypeSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                UIHandler.getInstance().showToast("0번 선택됨");
                break;
            case 1:
                UIHandler.getInstance().showToast("1번 선택됨");
                break;
            case 2:
                UIHandler.getInstance().showToast("2번 선택됨");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
