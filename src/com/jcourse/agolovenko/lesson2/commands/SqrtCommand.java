package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class SqrtCommand implements Command {
    private final ALU calculator;

    public SqrtCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.sqrt();
    }
}
