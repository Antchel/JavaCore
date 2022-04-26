package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class SubCommand implements Command {
    private final ALU calculator;

    public SubCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.sub();
    }
}
