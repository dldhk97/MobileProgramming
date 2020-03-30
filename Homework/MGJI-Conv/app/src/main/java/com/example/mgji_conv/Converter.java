package com.example.mgji_conv;

import android.util.Log;

import java.math.BigDecimal;

public class Converter {
    private final double mileToKm = 1.60934;

    public BigDecimal toKilometer(BigDecimal mile) throws Exception{
        try{
            BigDecimal kilometer = mile.multiply(new BigDecimal(mileToKm));
            return kilometer;
        }
        catch(Exception e){
            Log.d("CONVERTER_EXCEPTION",e.getMessage());
        }
        throw new MyException(ExceptionType.CONVERTER_EXCEPTION, "알 수 없는 이유로 변환에 실패하였습니다!");
    }
}
