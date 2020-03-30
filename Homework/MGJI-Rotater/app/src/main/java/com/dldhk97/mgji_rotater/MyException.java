package com.dldhk97.mgji_rotater;

import com.dldhk97.mgji_rotater.enums.ExceptionType;

public class MyException extends Exception{
    ExceptionType type;
    String msg;

    public MyException(ExceptionType type, String msg){
        super(msg);
        this.type = type;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "[" + type.toString() + "]\n" + super.getMessage();
    }
}