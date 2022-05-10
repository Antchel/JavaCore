package com.jcourse.agolovenko.lesson2.datamanagers;

/**
 * Concrete implementation print Calculator command which store the
 * last value in stack of Calculator and has a method getResult
 */
public class TestPrintDevice implements IPrintDevice {
    private Double result;

    public Double getResult() {
        return result;
    }

    @Override
    public void accept(Double number) {
        result = number;
    }
}
