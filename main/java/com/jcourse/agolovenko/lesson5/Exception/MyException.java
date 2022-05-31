package com.jcourse.agolovenko.lesson5.Exception;

public class MyException extends Exception {
    MyException(String message) {
        throw new RuntimeException(message);
    }
}
