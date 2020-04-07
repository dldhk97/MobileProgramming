package com.dldhk97.mgji_adder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.dldhk97.mgji_adder.enums.Direction;
import com.dldhk97.mgji_adder.listener.OnButtonClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uh = new UIHandler(this);
        setupUiComponents();
    }

    // UI 컴포넌트 설정
    private void setupUiComponents() {
        Button button_binary_upper_1 = findViewById(R.id.button_binary_upper_1);
        Button button_binary_upper_2 = findViewById(R.id.button_binary_upper_2);
        Button button_binary_upper_3 = findViewById(R.id.button_binary_upper_3);
        ArrayList<Button> upperButtons = new ArrayList<>(Arrays.asList(button_binary_upper_1, button_binary_upper_2, button_binary_upper_3));

        Button button_binary_lower_1 = findViewById(R.id.button_binary_lower_1);
        Button button_binary_lower_2 = findViewById(R.id.button_binary_lower_2);
        Button button_binary_lower_3 = findViewById(R.id.button_binary_lower_3);
        ArrayList<Button> lowerButtons = new ArrayList<>(Arrays.asList(button_binary_lower_1, button_binary_lower_2, button_binary_lower_3));

        TextView textView_decimal_upper = findViewById(R.id.textview_decimal_upper);
        TextView textView_decimal_lower = findViewById(R.id.textview_decimal_lower);
        TextView textView_decimal_result = findViewById(R.id.textview_demical_result);
        TextView textView_binary_result = findViewById(R.id.textview_binary_result);
        ArrayList<TextView> textViews = new ArrayList<>(Arrays.asList(textView_decimal_upper, textView_decimal_lower, textView_decimal_result, textView_binary_result));

        setButtonListener(upperButtons, Direction.UPPER, textViews);
        setButtonListener(lowerButtons, Direction.LOWER, textViews);


        initializeLikeQuest(upperButtons, lowerButtons, textViews);
    }

    // 버튼 클릭 리스너 설정
    private void setButtonListener(ArrayList<Button> buttons, Direction direction, ArrayList<TextView> textViews){
        for(int i = 0 ; i < 3 ; i++){
            OnButtonClickListener listener = new OnButtonClickListener(direction,
                    i,
                    textViews.get(0), textViews.get(1), textViews.get(2), textViews.get(3),
                    buttons.get(0), buttons.get(1), buttons.get(2)
            );
            buttons.get(i).setOnClickListener(listener);
        }
    }

    // 시작할 때 과제 예시처럼 보이게 세팅
    private void initializeLikeQuest(ArrayList<Button> upperButtons, ArrayList<Button> lowerButtons, ArrayList<TextView> textViews){
        upperButtons.get(1).setText("1");
        upperButtons.get(2).setText("1");
        lowerButtons.get(0).setText("1");
        lowerButtons.get(1).setText("1");

        textViews.get(0).setText("3");
        textViews.get(1).setText("-2");
        textViews.get(2).setText("1");
        textViews.get(3).setText("001");
    }

}
