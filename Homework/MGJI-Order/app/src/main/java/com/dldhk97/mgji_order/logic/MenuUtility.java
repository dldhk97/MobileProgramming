package com.dldhk97.mgji_order.logic;

import android.content.Context;

import com.dldhk97.mgji_order.MyException;
import com.dldhk97.mgji_order.R;
import com.dldhk97.mgji_order.enums.ExceptionType;
import com.dldhk97.mgji_order.enums.MenuType;

import java.text.DecimalFormat;

public class MenuUtility {
    private static MenuUtility _Instance;
    private static Context context;

    public MenuUtility(Context context){
        this.context = context;
        _Instance = this;
    }

    public static MenuUtility getInstance(){
        return _Instance;
    }

    public int getPrice(MenuType menuType) throws Exception{
        switch(menuType){
            case MENU1:
                return context.getResources().getInteger(R.integer.menu1_price);
            case MENU2:
                return context.getResources().getInteger(R.integer.menu2_price);
            case MENU3:
                return context.getResources().getInteger(R.integer.menu3_price);
            default:
                throw new MyException(ExceptionType.UNKNOWN_MENU_TYPE, "");
        }
    }

    public String getName(MenuType menuType) throws Exception{
        switch(menuType){
            case MENU1:
                return context.getResources().getString(R.string.menu1_text);
            case MENU2:
                return context.getResources().getString(R.string.menu2_text);
            case MENU3:
                return context.getResources().getString(R.string.menu3_text);
            default:
                throw new MyException(ExceptionType.UNKNOWN_MENU_TYPE, "");
        }
    }

    public String formatPrice(int price){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(price);
    }
}
