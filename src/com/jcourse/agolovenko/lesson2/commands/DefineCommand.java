package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public class DefineCommand implements Command {
    ALU calculator;
    String[] params;

    public DefineCommand(ALU calculator, String[] params) {
        this.calculator = calculator;
        this.params = params;
    }

    @Override
    public void execute() {
        calculator.define(params[0], params[1]);
    }
}
