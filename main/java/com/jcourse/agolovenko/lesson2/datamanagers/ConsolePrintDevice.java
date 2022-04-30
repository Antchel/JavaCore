package com.jcourse.agolovenko.lesson2.datamanagers;

public class ConsolePrintDevice implements IPrintDevice {
    @Override
    public void accept(Double number) {
        System.out.println(number);
    }
}
