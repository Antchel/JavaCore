package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public class SqrtCommand implements Command {
    private final ICalculator calculator;

    public SqrtCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.sqrt();
    }
}
