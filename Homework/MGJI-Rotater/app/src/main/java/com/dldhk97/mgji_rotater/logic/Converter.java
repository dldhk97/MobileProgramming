package com.dldhk97.mgji_rotater.logic;

import com.dldhk97.mgji_rotater.MyException;
import com.dldhk97.mgji_rotater.enums.ExceptionType;

public class Converter {

    public String toBinary(int decimal) throws Exception{
        try{
            String binaryStr = Integer.toBinaryString(decimal);
            int binaryInt = Integer.parseInt(binaryStr);
            binaryStr = String.format("%08d", binaryInt);
            return binaryStr;
        }
        catch(Exception e){
            throw new MyException(ExceptionType.CONVERSION_FAILED, "2진수로 변환 중 에러 발생!");
        }
    }
}
