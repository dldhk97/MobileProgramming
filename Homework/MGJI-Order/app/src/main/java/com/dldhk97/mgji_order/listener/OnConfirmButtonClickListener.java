package com.dldhk97.mgji_order.listener;

import android.view.View;
import android.widget.TextView;

import com.dldhk97.mgji_order.logic.Logger;
import com.dldhk97.mgji_order.UIHandler;
import com.dldhk97.mgji_order.enums.MenuType;
import com.dldhk97.mgji_order.logic.MenuUtility;

public class OnConfirmButtonClickListener implements View.OnClickListener{
    private Logger logger;

    private TextView textView_menu1_count;
    private TextView textView_menu2_count;
    private TextView textView_menu3_count;
    private TextView textView_menu_log1_content;
    private TextView textView_menu_log2_content;

    public OnConfirmButtonClickListener(Logger logger, TextView textView_menu1_count, TextView textView_menu2_count, TextView textView_menu3_count, TextView textView_menu_log1_content, TextView textView_menu_log2_content){
        this.logger = logger;

        this.textView_menu1_count = textView_menu1_count;
        this.textView_menu2_count = textView_menu2_count;
        this.textView_menu3_count = textView_menu3_count;
        this.textView_menu_log1_content = textView_menu_log1_content;
        this.textView_menu_log2_content = textView_menu_log2_content;
    }

    @Override
    public void onClick(View view) {
        try{
            int menu1Count = Integer.parseInt(textView_menu1_count.getText().toString());
            int menu2Count = Integer.parseInt(textView_menu2_count.getText().toString());
            int menu3Count = Integer.parseInt(textView_menu3_count.getText().toString());

            logger.update(MenuType.MENU1, menu1Count);
            logger.update(MenuType.MENU2, menu2Count);
            logger.update(MenuType.MENU3, menu3Count);

            displayLog1(menu1Count, menu2Count, menu3Count);
            textView_menu_log2_content.setText(logger.getLog());
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert(e.getMessage());
        }
    }

    private void displayLog1(int menu1Count, int menu2Count, int menu3Count) throws Exception{
        String menu1Name = MenuUtility.getInstance().getName(MenuType.MENU1);
        String menu2Name = MenuUtility.getInstance().getName(MenuType.MENU2);
        String menu3Name = MenuUtility.getInstance().getName(MenuType.MENU3);

        int menu1Price = menu1Count * MenuUtility.getInstance().getPrice(MenuType.MENU1);
        int menu2Price = menu2Count * MenuUtility.getInstance().getPrice(MenuType.MENU2);
        int menu3Price = menu3Count * MenuUtility.getInstance().getPrice(MenuType.MENU3);


        String log1 = menu1Name + " " + menu1Count + "개 " + MenuUtility.getInstance().formatPrice(menu1Price) + "원\n" +
                menu2Name + " " + menu2Count + "개 " + MenuUtility.getInstance().formatPrice(menu2Price) + "원\n" +
                menu3Name + " " + menu3Count + "개 " + MenuUtility.getInstance().formatPrice(menu3Price) + "원\n";

        log1 += "계 " + MenuUtility.getInstance().formatPrice(menu1Price + menu2Price + menu3Price) + "원";

        textView_menu_log1_content.setText(log1);
    }
}
