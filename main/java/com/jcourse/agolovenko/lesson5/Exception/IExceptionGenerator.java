package com.jcourse.agolovenko.lesson5.Exception;

public interface IExceptionGenerator {
    void generateNullPointerException();

    void generateClassCastException();

    void generateNumberFormatException();

    void generateStackOverflowError();

    void generateOutOfMemoryError();

    void generateMyException(String message) throws MyException;

}
