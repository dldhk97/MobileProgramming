package com.dldhk97.mgji_recy.utilities;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.MealTimeType;
import com.dldhk97.mgji_recy.models.Menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class OfflineDataCreator {
    // 해당 날짜로부터 days 날 전까지의 메뉴를 랜덤생성함. 하루에 메뉴는 2개임. (중식 석식)
    public static ArrayList<Menu> getRndMenuList(Calendar startDate, int days, CafeteriaType cafeteriaType) throws Exception{
        Calendar date = Calendar.getInstance();
        date.setTime(startDate.getTime());

        ArrayList<Menu> menuArr = new ArrayList<>();

        // 해당 날짜로부터 n일 전까지 중식과 석식 랜덤생성해서 배열에 삽입
        for(int i = 0 ; i < days ; i++){
            date.add(Calendar.DATE, -1);
            String x = DateUtility.DateToString(date, '-');
            menuArr.add(getRndMenu((Calendar) date.clone(), cafeteriaType, MealTimeType.LUNCH));
            menuArr.add(getRndMenu((Calendar) date.clone(), cafeteriaType, MealTimeType.DINNER));
        }

        return menuArr;
    }

    private static final int MIN_FOOD_CNT = 2;
    private static final int ADDITIONAL_FOOD_CNT = 9;
    private static Menu getRndMenu(Calendar date, CafeteriaType cafeteriaType, MealTimeType mealTimeType) throws Exception{
        // 이번 메뉴의 음식의 수를 MIN_FOOD_CNT ~ MIN_FOOD_CNT + ADDITIONAL_FOOD_CNT 범위에서 랜덤으로 정함.
        int offset = new Random().nextInt(ADDITIONAL_FOOD_CNT);
        int foodCnt = MIN_FOOD_CNT + offset;
        Menu menu = new Menu(date, cafeteriaType, mealTimeType);

        menu.addFood("[오프라인으로 랜덤 생성된 메뉴입니다]");
        menu.addFood("[인터넷에 연결 후 다시 시도해주세요]");

        // 음식 배열 가져와서 랜덤하게 가져와서 배열에 넣음.
        String[] rndMenuStr = MainActivity.getInstance().getResources().getStringArray(R.array.rndMenuList);
        for(int i = 0 ; i < foodCnt ; i++){
            int rndMenuIdx = new Random().nextInt(rndMenuStr.length - 1);
            if(!menu.getFoods().contains(rndMenuStr[rndMenuIdx]))
                menu.addFood(rndMenuStr[rndMenuIdx]);
        }

        // 사진 대충 설정
        int imageOffset = new Random().nextInt(6);
        int imgId = R.drawable.food0 + imageOffset;
        menu.setImageId(imgId);

        return menu;
    }
}
