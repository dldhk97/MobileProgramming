package com.dldhk97.mgji_rotater.logic;

import com.dldhk97.mgji_rotater.MyException;
import com.dldhk97.mgji_rotater.enums.ExceptionType;

public class Rotater {

    public String rotateLeft(String binaryStr) throws Exception{
        try{
            char tmp = binaryStr.charAt(0);
            String result = binaryStr.substring(1, binaryStr.length());
            result += tmp;
            return result;
        }
        catch(Exception e){
            throw new MyException(ExceptionType.ROTATION_FAILED, "LEFT 연산 중 오류 발생!");
        }
    }

    public String rotateRight(String binaryStr) throws Exception{
        try{
            char tmp = binaryStr.charAt(binaryStr.length() - 1);
            String result = binaryStr.substring(0, binaryStr.length() - 1);
            result = tmp + result;
            return result;
        }
        catch(Exception e){
            throw new MyException(ExceptionType.ROTATION_FAILED, "RIGHT 연산 중 오류 발생!");
        }
    }
}
