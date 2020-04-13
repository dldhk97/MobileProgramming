package com.dldhk97.mgji_order.logic;

import com.dldhk97.mgji_order.UIHandler;
import com.dldhk97.mgji_order.enums.MenuType;

import java.util.HashMap;

public class Logger {
    //메뉴타입 - 개수
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    int totalPrice = 0;

    //메뉴타입에 대해 개수 올림
    public void update(MenuType menuType, int count) throws Exception{
        int menuPrice = MenuUtility.getInstance().getPrice(menuType);
        if(counts.containsKey(menuType.toString())){
            int currentCount = counts.get(menuType.toString());
            counts.put(menuType.toString(), count + currentCount);
        }
        else{
            counts.put(menuType.toString(), count);
        }

        totalPrice += count * menuPrice;
    }

    //시작 시 로그 초기화용
    public void initializeLikeQuest(){
        try{
            this.update(MenuType.MENU1, 10);
            this.update(MenuType.MENU2, 20);
            this.update(MenuType.MENU3, 20);
            totalPrice = 250000;
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("로그 초기화에 실패하였습니다.");
        }
    }

    public String getLog() throws Exception{
        String result = "";

        for(MenuType menuType : MenuType.values()){
            if(counts.containsKey(menuType.toString())){
                String menuName = MenuUtility.getInstance().getName(menuType);
                int currentCount = counts.get(menuType.toString());
                result += menuName + " " + currentCount + "개\n";
            }
        }

        String totalPriceStr = MenuUtility.getInstance().formatPrice(totalPrice);
        result += "총계 " + totalPriceStr + "원";

        return result;
    }
}
