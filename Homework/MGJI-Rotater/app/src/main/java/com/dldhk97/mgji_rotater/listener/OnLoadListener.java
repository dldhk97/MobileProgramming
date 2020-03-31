package com.dldhk97.mgji_rotater.listener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dldhk97.mgji_rotater.MyException;
import com.dldhk97.mgji_rotater.UIHandler;
import com.dldhk97.mgji_rotater.enums.ExceptionType;
import com.dldhk97.mgji_rotater.logic.Converter;

import java.util.regex.Pattern;

public class OnLoadListener implements View.OnClickListener{
    private EditText userInput;
    private TextView resultView;

    public OnLoadListener(EditText userInput, TextView resultView){
        this.userInput = userInput;
        this.resultView = resultView;
    }
    @Override
    public void onClick(View view) {
        try{
            String decimalStr = userInput.getText().toString();

            if(decimalStr.isEmpty()){
                UIHandler.getInstance().showToast("입력값이 비어있습니다");
                return;
            }

            if(!matchPattern(decimalStr)){
                throw new MyException(ExceptionType.REGEX_NOT_MATCH ,"유효하지 않은 입력값입니다!");
            }

            int decimal;
            try{
                decimal = Integer.parseInt(decimalStr);
            }
            catch(NumberFormatException nfe){
                throw new MyException(ExceptionType.PARSEINT_FAILED, "문자열을 숫자로 변환하는데 실패했습니다!\n입력값이 너무 깁니다!");
            }

            if(decimal > 255 || decimal < 0){
                throw new MyException(ExceptionType.OUT_OF_RANGE ,"입력값이 범위를 벗어났습니다.");
            }

            Converter converter = new Converter();
            String binary = converter.toBinary(decimal);

            resultView.setText(binary);
            UIHandler.getInstance().showToast("LOAD 완료");
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert(e.getMessage());
        }

    }

    private boolean matchPattern(String decimalStr){
        Pattern numPattern = Pattern.compile("^[0-9]+$");
        return numPattern.matcher(decimalStr).find();
    }
}
