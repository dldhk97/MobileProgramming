package com.dldhk97.mgji_menu.models;

import com.dldhk97.mgji_menu.enums.ExceptionType;

public class MyException extends Exception{
    private ExceptionType type;

    public MyException(ExceptionType type, String msg){
        super(msg);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "[" + type.toString() + "]\n" + super.getMessage();
    }
}