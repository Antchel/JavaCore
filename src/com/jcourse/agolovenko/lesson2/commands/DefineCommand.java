package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public record DefineCommand(ALU calculator,
                            String[] params) implements Command {

    @Override
    public void execute() {
        calculator.define(params[0], params[1]);
    }
}
