package com.dldhk97.mgji_recy.enums;

import androidx.annotation.NonNull;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.R;

public enum MealTimeType {
    BREAKFAST, LUNCH, DINNER, ONECOURSE, UNKNOWN;

    public static MealTimeType strToValue(String str){
        switch(str){
            case "조식":
                return BREAKFAST;
            case "중식":
                return LUNCH;
            case "석식":
                return DINNER;
            case "일품요리":
                return ONECOURSE;
            default:
                return UNKNOWN;
        }
    }

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
            case ONECOURSE:
                return MainActivity.getInstance().getResources().getStringArray(R.array.mealTimeType)[3];
            case UNKNOWN:
                return "알수없음";
            default:
                return super.toString();
        }
    }
}
