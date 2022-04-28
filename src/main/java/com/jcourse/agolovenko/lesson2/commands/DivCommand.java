package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public class DivCommand implements Command {
    private final ICalculator calculator;

    public DivCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.div();
    }
}
