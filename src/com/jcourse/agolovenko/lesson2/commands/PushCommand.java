package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ALU;

public record PushCommand(ALU calculator, String[] params) implements Command {

    @Override
    public void execute() {
        calculator.push(params[0]);
    }
}
