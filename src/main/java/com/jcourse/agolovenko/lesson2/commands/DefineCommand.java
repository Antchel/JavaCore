package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public record DefineCommand(ICalculator calculator,
                            String[] params) implements Command {

    @Override
    public void execute() {
        calculator.define(params[0], params[1]);
    }
}
