package com.dldhk97.mgji_recy.listeners;

import android.view.View;
import android.widget.AdapterView;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;

public class OnCafeteriaTypeSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            String toastMsg = "";
            switch(i){
                case 0:
                    toastMsg = CafeteriaType.STUDENT.toString();
                    break;
                case 1:
                    toastMsg = CafeteriaType.STAFF.toString();
                    break;
                case 2:
                    toastMsg = CafeteriaType.SNACKBAR.toString();
                    break;
            }
            UIHandler.getInstance().showToast(toastMsg + " 선택됨");
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert(e.getMessage());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
