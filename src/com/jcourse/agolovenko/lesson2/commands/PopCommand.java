package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class PopCommand implements Command {
    private final ALU calculator;

    public PopCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.pop();
    }
}
