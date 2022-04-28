package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public class PrintCommand implements Command {
    private final ICalculator calculator;

    public PrintCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.print();
    }
}
