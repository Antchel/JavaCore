package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.*;
import com.jcourse.agolovenko.lesson3.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jcourse.agolovenko.lesson3.ExecuteMode.DEBUG;
import static com.jcourse.agolovenko.lesson3.ExecuteMode.RELEASE;

/**
 * CommandFactory class loads commands from property file and
 */
public class CommandFactory {
    private final ICalculator calculator;
    private final ExecuteMode mode;

    public CommandFactory(ICalculator calculator, ExecuteMode mode) {

        this.calculator = calculator;
        this.mode = mode;
    }

    private static final Properties COMMANDS = new Properties();

    static {
        InputStream commands = CommandFactory.class.getResourceAsStream("/commands.properties");
        try {
            COMMANDS.load(commands);
            Objects.requireNonNull(commands).close();
        } catch (IOException e) {
            throw new RuntimeException("Could not find command properties file");
        }
    }

    public Command getCommandByName(String name, String[] params) throws Throwable {
        String className = (String) COMMANDS.get(name.toLowerCase());
        Command command = (Command) Class.forName(className).
                getConstructor().newInstance();
        Field CONTEXT_FIELD = Arrays.stream(command.getClass().getDeclaredFields()).filter(field -> {
            In annotaion = field.getAnnotation(In.class);
            return annotaion != null && annotaion.value().equals(InjectionType.CONTEXT);
        }).findFirst().orElseThrow(
                (Supplier<Throwable>) () ->
                        new RuntimeException("There are no fields with Context annotation"));

        Field STACK_FIELD = Arrays.stream(command.getClass().getDeclaredFields()).filter(field -> {
            In annotaion = field.getAnnotation(In.class);
            return annotaion != null && annotaion.value().equals(InjectionType.STACK);
        }).findFirst().orElseThrow(
                (Supplier<? extends Throwable>) () ->
                        new RuntimeException("There are no fields with Stack annotation"));

        CONTEXT_FIELD.setAccessible(true);
        CONTEXT_FIELD.set(command, params);
        STACK_FIELD.setAccessible(true);
        STACK_FIELD.set(command, calculator);

        if (RELEASE == mode) {
            return command;
        } else if (DEBUG == mode) {
            return (Command) Proxy.newProxyInstance(CommandFactory.class.getClassLoader(),
                    new Class[]{Command.class}, new CommandProxy(command, calculator));
        } else {
            return null;
        }
    }

    /* For new calculator commands */
    public void register(String commandName, String clazz) throws NoSuchMethodException, ClassNotFoundException {
        COMMANDS.put(commandName, Class.forName(clazz).
                getConstructor(ICalculator.class, String[].class));
    }

}
