package com.dldhk97.mgji_recy.enums;

import androidx.annotation.NonNull;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.R;

public enum MealTimeType {
    BREAKFAST, LUNCH, DINNER, SNACKBAR, UNKNOWN;

    @NonNull
    @Override
    public String toString() {
        switch (this){
            case BREAKFAST:
                return MainActivity.getInstance().getResources().getStringArray(R.array.mealTimeType)[0];
            case LUNCH:
                return MainActivity.getInstance().getResources().getStringArray(R.array.mealTimeType)[1];
            case DINNER:
                return MainActivity.getInstance().getResources().getStringArray(R.array.mealTimeType)[2];
            case SNACKBAR:
                return MainActivity.getInstance().getResources().getStringArray(R.array.mealTimeType)[3];
            default:
                return super.toString();
        }
    }
}
