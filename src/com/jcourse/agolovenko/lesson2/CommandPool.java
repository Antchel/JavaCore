package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.*;

import java.util.HashMap;

public class CommandPool {
    static final HashMap<String, Class<? extends Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("push", PushCommand.class);
        commandMap.put("pop", PopCommand.class);
        commandMap.put("+", AddCommand.class);
        commandMap.put("-", SubCommand.class);
        commandMap.put("/", DivCommand.class);
        commandMap.put("*", MultCommand.class);
        commandMap.put("sqrt", SqrtCommand.class);
        commandMap.put("define", DefineCommand.class);
        commandMap.put("print", PrintCommand.class);
    }

    public static Class<? extends Command> getCommandByName(String name) throws NoSuchMethodException{
        if (commandMap.containsKey(name.toLowerCase()))
            return commandMap.get(name.toLowerCase());
        else {
            throw new NoSuchMethodException("Couldn't find such command in command pool.");
        }
    }

    /* For new calculator commands */
    public static void register(String commandName, Class<? extends Command> clazz) {
        commandMap.put(commandName, clazz);
    }

}
