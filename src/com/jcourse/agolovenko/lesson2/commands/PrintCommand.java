package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class PrintCommand implements Command {
    private final ALU calculator;

    public PrintCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.print();
    }
}
