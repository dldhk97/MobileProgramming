package com.dldhk97.mgji_order.listener;

import android.view.View;
import android.widget.TextView;

import com.dldhk97.mgji_order.enums.MenuType;
import com.dldhk97.mgji_order.logic.MenuUtility;
import com.dldhk97.mgji_order.UIHandler;
import com.dldhk97.mgji_order.enums.Sign;

public class OnControlButtonClickListener implements View.OnClickListener{
    private final static int MINIMUM_COUNT = 0;
    private final static int MAXIMUM_COUNT = 5;

    private Sign sign;
    private MenuType menuType;

    private TextView textView_menu_count;
    private TextView textView_menu_priceView;

    public OnControlButtonClickListener(Sign sign, MenuType menuType, TextView textView_menu_count, TextView textView_menu_priceView){
        this.sign = sign;
        this.menuType = menuType;
        this.textView_menu_count = textView_menu_count;
        this.textView_menu_priceView = textView_menu_priceView;
    }

    @Override
    public void onClick(View view) {
        try {
            changeCount();
            changePrice();

        } catch (Exception e) {
            UIHandler.getInstance().showAlert(e.getMessage());
        }
    }

    private void changeCount() throws Exception{
        int currentCount = Integer.parseInt(textView_menu_count.getText().toString());
        int add = sign == Sign.PLUS ? 1 : -1;

        if(currentCount + add < MINIMUM_COUNT || currentCount + add > MAXIMUM_COUNT){
            return;
        }

        textView_menu_count.setText(String.valueOf(currentCount + add));
    }

    private void changePrice() throws Exception{
        int menuPrice = MenuUtility.getInstance().getPrice(menuType);
        int count = Integer.parseInt(textView_menu_count.getText().toString());

        String resultPrice =  MenuUtility.getInstance().formatPrice(count * menuPrice);

        textView_menu_priceView.setText(resultPrice);
    }

}
