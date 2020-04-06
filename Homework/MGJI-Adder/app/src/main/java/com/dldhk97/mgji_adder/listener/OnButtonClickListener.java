package com.dldhk97.mgji_adder.listener;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dldhk97.mgji_adder.MyException;
import com.dldhk97.mgji_adder.UIHandler;
import com.dldhk97.mgji_adder.enums.Direction;
import com.dldhk97.mgji_adder.enums.ExceptionType;
import com.dldhk97.mgji_adder.logic.BinaryConverter;

public class OnButtonClickListener implements View.OnClickListener{
    private Direction direction;
    private int sequence;

    private TextView textView_decimal_upper;
    private TextView textView_decimal_lower;
    private TextView textView_decimal_result;
    private TextView textView_binary_result;
    private Button button_binary_1;
    private Button button_binary_2;
    private Button button_binary_3;

    public OnButtonClickListener(Direction direction, int sequence, TextView textView_decimal_upper, TextView textView_decimal_lower, TextView textView_decimal_result, TextView textView_binary_result, Button button_binary_1, Button button_binary_2, Button button_binary_3){
        this.direction = direction;
        this.sequence = sequence;

        this.textView_decimal_upper = textView_decimal_upper;
        this.textView_decimal_lower = textView_decimal_lower;
        this.textView_decimal_result = textView_decimal_result;
        this.textView_binary_result = textView_binary_result;

        this.button_binary_1 = button_binary_1;
        this.button_binary_2 = button_binary_2;
        this.button_binary_3 = button_binary_3;
    }
    @Override
    public void onClick(View view) {
        try{
            // 버튼 숫자 토글
            toggleButton();

            // 이벤트가 발생한 버튼으로부터 String 추출
            String binaryString = button_binary_1.getText().toString() + button_binary_2.getText().toString() + button_binary_3.getText().toString();

            // 이벤트가 발생한 버튼들의 2진수 문자열을 10진수 int로 변환
            BinaryConverter converter = new BinaryConverter();
            int decimal = converter.toDecimal(binaryString);

            // 변환된 10진수를 오른쪽에 결과 표시
            setRightDecimalTextView(decimal);

            // 반대쪽 10진수 얻어내기
            int oppositeDecimal = getOppositeDecimal();

            // 덧셈 계산
            int result = decimal + oppositeDecimal;

            // 오른쪽 결과에 10진수 표시
            textView_decimal_result.setText(String.valueOf(result));

            // 왼쪽 결과에 2진수 표시
            textView_binary_result.setText(converter.toBinary(result));
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert(e.getMessage());
        }
    }

    // 버튼 투글
    private void toggleButton() throws Exception{
        Button targetButton;
        switch(sequence){
            case 0:
                targetButton = button_binary_1;
                break;
            case 1:
                targetButton = button_binary_2;
                break;
            case 2:
                targetButton = button_binary_3;
                break;
            default:
                throw new MyException(ExceptionType.UNKNOWN_BUTTON_SEQUENCE, "Button sequence is unknown");
        }

        if(targetButton.getText().toString().equals("0")){
            targetButton.setText("1");
        }
        else{
            targetButton.setText("0");
        }
    }

    // 오른쪽 결과창에 표시
    private void setRightDecimalTextView(int decimal){
        TextView targetView;
        if(direction == Direction.UPPER){
            targetView = textView_decimal_upper;
        }
        else{
            targetView = textView_decimal_lower;
        }

        targetView.setText(String.valueOf(decimal));
    }

    // 반대쪽 10진수 얻어내기
    private int getOppositeDecimal(){
        TextView targetView;
        if(direction == Direction.UPPER){
            targetView = textView_decimal_lower;
        }
        else{
            targetView = textView_decimal_upper;
        }

        return Integer.parseInt(targetView.getText().toString());
    }

}
