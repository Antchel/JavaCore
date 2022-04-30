package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public class PopCommand implements Command {
    private final ICalculator calculator;

    public PopCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.pop();
    }
}
