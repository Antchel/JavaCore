package com.jcourse.agolovenko.lesson3;

import com.jcourse.agolovenko.lesson2.CommandFactory;
import com.jcourse.agolovenko.lesson2.ICalculator;
import com.jcourse.agolovenko.lesson2.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * CommandProxy class allows you to get debug information such as:
 *      Stack before, Context of operation, Arguments and stack after
 *      and store this information into the file
 */
public class CommandProxy implements InvocationHandler {

    private final Command command;
    private final ICalculator calculator;
    private final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    public CommandProxy(Command command, ICalculator calculator) {
        this.command = command;
        this.calculator = calculator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info(String.format("%0" + 50 + "d", 0).replace('0', '-'));

        Field stack = Arrays.stream(calculator.getClass().getDeclaredFields()).filter(field -> {
            In annotation = field.getAnnotation(In.class);
            return annotation != null && annotation.value().equals(InjectionType.STACK);
        }).findFirst().orElseThrow((Supplier<Throwable>) () -> new RuntimeException("There are no such annotation in class"));
        Field context = Arrays.stream(command.getClass().getDeclaredFields()).filter(field -> {
            In annotation = field.getDeclaredAnnotation(In.class);
            return annotation != null && annotation.value().equals(InjectionType.STACK);
        }).findFirst().orElseThrow((Supplier<? extends Throwable>) () ->
                new RuntimeException("There is no field with In Stack annotation"));
        Field arguments = Arrays.stream(command.getClass().getDeclaredFields()).filter(field -> {
            In annotation = field.getDeclaredAnnotation(In.class);
            return annotation != null && annotation.value().equals(InjectionType.CONTEXT);
        }).findFirst().orElseThrow((Supplier<? extends Throwable>) () ->
                new RuntimeException("There is no field with In Context annotation"));

        stack.setAccessible(true);
        context.setAccessible(true);
        arguments.setAccessible(true);

        LOGGER.debug("STACK BEFORE " + stack.get(calculator));
        LOGGER.debug("CONTEXT " + context);
        LOGGER.debug("ARGUMENTS  " + Arrays.toString((String[]) arguments.get(command)));

        command.execute();

        LOGGER.debug("STACK AFTER " + stack.get(calculator));

        return null;
    }
}
