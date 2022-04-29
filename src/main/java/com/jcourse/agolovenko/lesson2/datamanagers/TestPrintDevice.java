package com.jcourse.agolovenko.lesson2.datamanagers;

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
