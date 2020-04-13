package com.dldhk97.mgji_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.dldhk97.mgji_order.enums.MenuType;
import com.dldhk97.mgji_order.enums.Sign;
import com.dldhk97.mgji_order.listener.OnConfirmButtonClickListener;
import com.dldhk97.mgji_order.listener.OnControlButtonClickListener;
import com.dldhk97.mgji_order.logic.Logger;
import com.dldhk97.mgji_order.logic.MenuUtility;

public class MainActivity extends AppCompatActivity {

    private static Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uh = new UIHandler(this);
        MenuUtility mu = new MenuUtility(this);
        logger = new Logger();
        logger.initializeLikeQuest();

        setupUiComponents();
    }

    // UI 컴포넌트 설정
    private void setupUiComponents() {
        Button button_menu1_minus = findViewById(R.id.button_menu1_minus);
        Button button_menu2_minus = findViewById(R.id.button_menu2_minus);
        Button button_menu3_minus = findViewById(R.id.button_menu3_minus);

        Button button_menu1_plus = findViewById(R.id.button_menu1_plus);
        Button button_menu2_plus = findViewById(R.id.button_menu2_plus);
        Button button_menu3_plus = findViewById(R.id.button_menu3_plus);

        Button button_menu_confirm = findViewById(R.id.button_menu_confirm);

        TextView textView_menu1_count = findViewById(R.id.textView_menu1_count);
        TextView textView_menu2_count = findViewById(R.id.textView_menu2_count);
        TextView textView_menu3_count = findViewById(R.id.textView_menu3_count);

        TextView textView_menu1_priceView = findViewById(R.id.textView_menu1_priceView);
        TextView textView_menu2_priceView = findViewById(R.id.textView_menu2_priceView);
        TextView textView_menu3_priceView = findViewById(R.id.textView_menu3_priceView);

        TextView textView_menu_log1_content = findViewById(R.id.textView_menu_log1_content);
        TextView textView_menu_log2_content = findViewById(R.id.textView_menu_log2_content);

        button_menu1_minus.setOnClickListener(new OnControlButtonClickListener(Sign.MINUS, MenuType.MENU1, textView_menu1_count, textView_menu1_priceView));
        button_menu2_minus.setOnClickListener(new OnControlButtonClickListener(Sign.MINUS, MenuType.MENU2, textView_menu2_count, textView_menu2_priceView));
        button_menu3_minus.setOnClickListener(new OnControlButtonClickListener(Sign.MINUS, MenuType.MENU3, textView_menu3_count, textView_menu3_priceView));

        button_menu1_plus.setOnClickListener(new OnControlButtonClickListener(Sign.PLUS, MenuType.MENU1, textView_menu1_count, textView_menu1_priceView));
        button_menu2_plus.setOnClickListener(new OnControlButtonClickListener(Sign.PLUS, MenuType.MENU2, textView_menu2_count, textView_menu2_priceView));
        button_menu3_plus.setOnClickListener(new OnControlButtonClickListener(Sign.PLUS, MenuType.MENU3, textView_menu3_count, textView_menu3_priceView));

        button_menu_confirm.setOnClickListener(new OnConfirmButtonClickListener(logger, textView_menu1_count , textView_menu2_count, textView_menu3_count, textView_menu_log1_content, textView_menu_log2_content));
    }

}
