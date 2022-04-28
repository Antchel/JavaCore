package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class Factory {
    static final HashMap<String, Constructor<? extends Command>> commandMap = new HashMap<>();

    static {
        try {
            commandMap.put("push", PushCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("pop", PopCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("+", AddCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("-", SubCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("/", DivCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("*", MultCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("sqrt", SqrtCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("define", DefineCommand.class.getConstructor(ICalculator.class, String[].class));
            commandMap.put("print", PrintCommand.class.getConstructor(ICalculator.class, String[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Constructor<? extends Command> getCommandByName(String name) throws NoSuchMethodException{
        if (commandMap.containsKey(name.toLowerCase()))
            return commandMap.get(name.toLowerCase());
        else {
            throw new NoSuchMethodException("Couldn't find such command in command pool.");
        }
    }

    /* For new calculator commands */
    public void register(String commandName, Class<? extends Command> clazz) throws NoSuchMethodException {
        commandMap.put(commandName, clazz.getConstructor(ICalculator.class, String[].class));
    }

}
