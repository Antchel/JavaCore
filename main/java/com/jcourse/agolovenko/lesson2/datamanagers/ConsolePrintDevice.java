package com.jcourse.agolovenko.lesson2.datamanagers;

/**
 * Concrete implementation print calculator command witch prints data to console
 */
public class ConsolePrintDevice implements IPrintDevice {
    @Override
    public void accept(Double number) {
        System.out.println(number);
    }
}
