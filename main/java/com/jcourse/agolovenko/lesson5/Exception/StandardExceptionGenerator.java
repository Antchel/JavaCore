package com.jcourse.agolovenko.lesson5.Exception;

import java.util.ArrayList;

public class StandardExceptionGenerator implements IExceptionGenerator{
    @Override
    @SuppressWarnings({"all"})
    public void generateNullPointerException() {
        Integer val = null;
        val.toString();
    }

    @Override
    @SuppressWarnings({"all"})
    public void generateClassCastException() {
        Object val = new ArrayList<>();
        ((String) val).toString();
    }

    @Override
    @SuppressWarnings({"all"})
    public void generateNumberFormatException() {
        String str = "abc";
        Double.parseDouble(str);
    }

    @Override
    @SuppressWarnings({"all"})
    public void generateStackOverflowError() {
        while(true){
            generateStackOverflowError();
        }
    }

    @Override
    @SuppressWarnings({"all"})
    public void generateOutOfMemoryError() {
        int []arr = new int[Integer.MAX_VALUE];
    }

    @Override
    @SuppressWarnings({"all"})
    public void generateMyException(String message) throws MyException {
        throw new MyException(message);
    }
}
