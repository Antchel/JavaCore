package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;

public class CommandInvoker {

    public void invoke(Command command) {
            command.execute();
    }
}
