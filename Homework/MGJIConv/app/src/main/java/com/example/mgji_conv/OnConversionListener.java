package com.example.mgji_conv;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class OnConversionListener implements View.OnClickListener{
    private final String TAG = "OnConversionListener";
    private EditText userInput;
    private TextView resultView;

    public OnConversionListener(EditText userInput, TextView resultView){
        this.userInput = userInput;
        this.resultView = resultView;
    }


    @Override
    public void onClick(View v) {
        try{
            String mileStr = getUserInput();
            BigDecimal mile = BigDecimal.valueOf(Double.parseDouble(mileStr));
            Converter converter = new Converter();
            BigDecimal result = converter.toKilometer(mile);
            displayResult(mile, result);
            UIHandler.getInstance().showToast("변환 성공!");
            userInput.setText("");
        }
        catch(Exception e){
            Log.d(TAG, e.getMessage());
            UIHandler.getInstance().showAlert(e.getMessage());
        }

    }

    private String getUserInput() throws Exception{
        String mileStr = null;
        // try to get userInput;
        mileStr = userInput.getText().toString().trim();
        if(mileStr == null){
            throw new MyException(ExceptionType.UNKNOWN, "텍스트박스에서 텍스트 가져오기 실패");
        }
        if(mileStr.isEmpty()){
            throw new MyException(ExceptionType.EMPTY_USER_INPUT, "입력값이 비어있습니다!");
        }
        if(!matchPattern(mileStr)){
            throw new MyException(ExceptionType.REGEX_NOT_MATCH, "유효하지 않은 문자열입니다!");
        }
        return mileStr;
    }

    private boolean matchPattern(String userInput){
        Pattern numPattern = Pattern.compile("^-?[0-9]+\\.?[0-9]*$");
        if(numPattern.matcher(userInput).find()){
            return true;
        }
        return false;
    }

    private void displayResult(BigDecimal userInput, BigDecimal result) throws Exception{
        String userInputStr = String.valueOf(userInput);
        String resultStr = String.valueOf(result.setScale(5, BigDecimal.ROUND_FLOOR));
        resultView.setText(userInputStr + " mile 은\n" + resultStr + " km 입니다.");
    }
}
