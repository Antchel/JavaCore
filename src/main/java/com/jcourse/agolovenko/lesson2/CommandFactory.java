package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandFactory {
    static final HashMap<String, Constructor<? extends Command>> commandMap = new HashMap<>();
    private final ICalculator calculator;

    public CommandFactory(ICalculator calculator) {
        this.calculator = calculator;
    }

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

    public Command getCommandByName(String name, String[] params) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (commandMap.containsKey(name.toLowerCase()))
            return commandMap.get(name.toLowerCase()).newInstance(calculator, params);
        else {
            throw new NoSuchMethodException("Couldn't find such command in command pool.");
        }
    }

    /* For new calculator commands */
    @SuppressWarnings("unchecked")
    public void register(String commandName, String clazz) throws NoSuchMethodException, ClassNotFoundException {
        commandMap.put(commandName, (Constructor<? extends Command>) Class.forName(clazz).
                getConstructor(ICalculator.class, String[].class));
    }

}
