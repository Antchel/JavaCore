package com.jcourse.agolovenko.lesson2;

/**
 * The receiver interface
 */
public interface ICalculator {
    void pop();

    void push(String value);

    void print();

    void define(String variable, String value);

    void add();

    void div();

    void sqrt();

    void sub();

    void mult();

    void exp(String[] params);

    void log(String[] params);

}
