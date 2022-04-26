package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Invoker {


    public void invoke(ALU calculator, String[] parameters) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        String[] params = Arrays.copyOfRange(parameters, 1, parameters.length);
        try {
            Command command = ((CommandPool.getCommandByName(parameters[0]))).getConstructor(ALU.class, String[].class).newInstance(calculator, params);
            command.execute();
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
    }

}
