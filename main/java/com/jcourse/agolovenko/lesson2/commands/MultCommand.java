package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public class MultCommand implements Command {
    private final ICalculator calculator;

    public MultCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.mult();
    }
}
