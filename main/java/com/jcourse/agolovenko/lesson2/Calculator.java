package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.datamanagers.IPrintDevice;
import com.jcourse.agolovenko.lesson3.In;
import com.jcourse.agolovenko.lesson3.InjectionType;

import java.util.*;

/**
 * Receiver in command pattern concept
 */
public class Calculator implements ICalculator {
    @In(InjectionType.STACK)
    private final Stack<Double> stack = new Stack<>();
    private final HashMap<String, Double> definedVariables = new HashMap<>();
    private final IPrintDevice dataManager;

    public Calculator(IPrintDevice dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void pop() {
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Cannot pop element from stack. It is empty!");
            e.printStackTrace();
        }
    }

    @Override
    public void push(String value) {
        if (Character.isAlphabetic(value.charAt(0))) {
            if (definedVariables.containsKey(value)) {
                stack.push(definedVariables.get(value));
            } else {
                System.out.println("Variable " + value + " is not defined");
            }
        } else {
            stack.push(Double.parseDouble(value));
        }
    }

    @Override
    public void print() {
        try {
            dataManager.accept(stack.peek());
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void define(String variable, String value) {
        try {
            if (Character.isAlphabetic(variable.charAt(0))) {
                definedVariables.put(variable, getNumber(value));
            } else {
                System.out.println("Variable shouldn't be a numeric!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Value for the defined variable must be a number.");
        }
    }

    @Override
    public void add() {
        if (stack.size() == 0) {
            System.out.println("Nothing to add. Stack is empty.");
        } else if (stack.size() > 1) {
            stack.push(stack.pop() + stack.pop());
        }
    }

    @Override
    public void div() {
        if (stack.size() < 2) {
            System.out.println("Don't enough variables for division. Stack consist of" + stack.size() + "variables");
        } else {
            Double divisor = stack.pop();
            Double divident = stack.pop();
            if (divisor == 0) {
                throw new ArithmeticException("Zero division error");
            }
            stack.push(divident / divisor);
        }

    }

    @Override
    public void sqrt() {
        if (stack.size() == 0) {
            System.out.println("Nothing to add. Stack is empty.");
        } else {
            stack.push(Math.sqrt(stack.pop()));
        }
    }

    @Override
    public void sub() {
        if (stack.size() == 0) {
            System.out.println("Nothing to sub. Stack is empty.");
        } else if (stack.size() == 1) {
            stack.push(-stack.pop());
        } else {
            stack.push(-stack.pop() + stack.pop());
        }
    }

    @Override
    public void mult() {
        if (stack.size() < 2) {
            System.out.println("Don't enough variables for multiplication. Stack consist of " + stack.size() + " variables");
        } else {
            stack.push(stack.pop() * stack.pop());
        }
    }

    @Override
    public void exp(String[] params) {
        int argc = (int) Arrays.stream(params).filter(Objects::nonNull).count();
        if (argc == 0) {
            if (stack.size() == 0)
                throw new IllegalArgumentException("Stack is empty. Too few arguments for \"exp\"");
            else{
                stack.push(Math.exp(stack.pop()));
            }
        } else {
            stack.push(Math.exp(Double.parseDouble(params[0])));
        }
    }

    @Override
    public void log(String[] params) {
        int argc = (int) Arrays.stream(params).filter(Objects::nonNull).count();
        if (argc == 0) {
            if (stack.size() == 0)
                throw new IllegalArgumentException("Stack is empty. Too few arguments for \"exp\"");
            else{
                stack.push(Math.log(stack.pop()));
            }
        } else {
            stack.push(Math.log(Double.parseDouble(params[0])));
        }

    }

    private Double getNumber(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("String " + value + " can not be parsed to Double.");
        }
    }
}
