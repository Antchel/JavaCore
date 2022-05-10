package com.jcourse.agolovenko.lesson2.commands;

import com.jcourse.agolovenko.lesson2.ICalculator;
import com.jcourse.agolovenko.lesson3.In;
import com.jcourse.agolovenko.lesson3.InjectionType;

public final class DefineCommand implements Command {
    @In(InjectionType.STACK)
    private ICalculator calculator;
    @In(InjectionType.CONTEXT)
    private String[] params;

    @Override
    public void execute() {
        calculator.define(params[0], params[1]);
    }
}
