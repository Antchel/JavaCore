package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;

public record PushCommand(ICalculator calculator, String[] params) implements Command {

    @Override
    public void execute() {
        calculator.push(params[0]);
    }
}
