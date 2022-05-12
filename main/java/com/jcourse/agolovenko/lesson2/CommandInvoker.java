package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;

/**
 * Invoker in Command pattern concept
 */
public class CommandInvoker {

    public void invoke(Command command) {
            command.execute();
    }
}
