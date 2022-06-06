package com.jcourse.agolovenko.lesson5.Exception;

public class ExceptionService {
    public static void main(String[] args) {
        IExceptionGenerator generator = new StandardExceptionGenerator();

        try {
            generator.generateClassCastException();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        try {
            generator.generateNullPointerException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            generator.generateNumberFormatException();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            generator.generateOutOfMemoryError();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        try {
            generator.generateStackOverflowError();
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
        try {
            generator.generateMyException("Error");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
