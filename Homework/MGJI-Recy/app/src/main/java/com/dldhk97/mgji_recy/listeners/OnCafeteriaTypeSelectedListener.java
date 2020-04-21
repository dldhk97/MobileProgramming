package com.dldhk97.mgji_recy.listeners;

import android.view.View;
import android.widget.AdapterView;

import com.dldhk97.mgji_recy.DataController;
import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;

public class OnCafeteriaTypeSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            CafeteriaType cafeteriaType = CafeteriaType.UNKNOWN;
            switch(i){
                case 0:
                    cafeteriaType = CafeteriaType.STUDENT;
                    break;
                case 1:
                    cafeteriaType = CafeteriaType.STAFF;
                    break;
                case 2:
                    cafeteriaType = CafeteriaType.SNACKBAR;
                    break;
            }
            DataController.getInstance().currentCafeteriaType = cafeteriaType;
            DataController.getInstance().updateData(cafeteriaType);
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[OnCafeteriaTypeSelectedListener.onItemSelected]\n" + e.getMessage());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
