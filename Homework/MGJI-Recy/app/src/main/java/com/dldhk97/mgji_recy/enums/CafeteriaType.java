package com.dldhk97.mgji_recy.enums;

import androidx.annotation.NonNull;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.R;

public enum CafeteriaType {
    STUDENT, STAFF, SNACKBAR, UNKNOWN;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case STUDENT:
                return MainActivity.getInstance().getResources().getStringArray(R.array.cafeteriaType)[0];
            case STAFF:
                return MainActivity.getInstance().getResources().getStringArray(R.array.cafeteriaType)[1];
            case SNACKBAR:
                return MainActivity.getInstance().getResources().getStringArray(R.array.cafeteriaType)[2];
            default:
                return super.toString();
        }
    }

    public String getURL(){
        switch(this){
            case STUDENT:
                return MainActivity.getInstance().getResources().getStringArray(R.array.urls)[0];
            case STAFF:
                return MainActivity.getInstance().getResources().getStringArray(R.array.urls)[1];
            case SNACKBAR:
                return MainActivity.getInstance().getResources().getStringArray(R.array.urls)[2];
            default:
                return "";
        }
    }

   public static String[] getStringArray(){
        return MainActivity.getInstance().getResources().getStringArray(R.array.cafeteriaType);
    }
}
