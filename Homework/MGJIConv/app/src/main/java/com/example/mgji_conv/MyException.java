package com.example.mgji_conv;

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

enum ExceptionType{
    EMPTY_USER_INPUT, CONVERTER_EXCEPTION, REGEX_NOT_MATCH, UNKNOWN;
}
