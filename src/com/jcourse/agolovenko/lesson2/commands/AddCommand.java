package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.Calculator;
import com.jcourse.agolovenko.lesson2.ICalculator;

public class AddCommand implements Command {
    private final ICalculator calculator;

    public AddCommand(ICalculator calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.add();
    }
}
