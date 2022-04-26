package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class PushCommand implements Command {
    ALU calculator;
    String value;

    public PushCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
        this.value = params[0];
    }

    @Override
    public void execute() {
        calculator.push(value);
    }
}
