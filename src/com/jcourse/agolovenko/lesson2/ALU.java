package com.jcourse.agolovenko.lesson2;

import java.util.*;

public class ALU {
    private final Stack<Double> stack = new Stack<>();
    private final HashMap<String, Double> definedVariables = new HashMap<>();

    public void pop() {
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Cannot pop element from stack. It is empty!");
            e.printStackTrace();
        }
    }

    public void push(String value) {
        if (!isNumeric(value)) {
            if (definedVariables.containsKey(value)) {
                stack.push(definedVariables.get(value));
            }
        } else {
            stack.push(Double.parseDouble(value));
        }
    }

    public void print() {
        try {
            System.out.println(stack.peek());
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    public void define(String variable, String value) {
        if (isNumeric(value) && !isNumeric(variable)) {
            definedVariables.put(variable, Double.parseDouble(value));
        } else {
            System.out.println("Value for the defined variable must be a number and variable shouldn't be a numeric!");
        }
    }

    public void add() {
        if (stack.size() == 0) {
            System.out.println("Nothing to add. Stack is empty.");
        } else if (stack.size() > 1) {
            stack.push(stack.pop() + stack.pop());
        }
    }

    public void div() {
        if (stack.size() < 2) {
            System.out.println("Don't enough variables for division. Stack consist of" + stack.size() + "variables");
        } else {
            try {
                stack.push(1. / stack.pop() * stack.pop());
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }
        }

    }

    public void sqrt() {
        if (stack.size() == 0) {
            System.out.println("Nothing to add. Stack is empty.");
        } else {
            stack.push(Math.sqrt(stack.pop()));
        }
    }

    public void sub() {
        if (stack.size() == 0) {
            System.out.println("Nothing to sub. Stack is empty.");
        } else if (stack.size() == 1) {
            stack.push(-stack.pop());
        } else {
            stack.push(-stack.pop() + stack.pop());
        }
    }

    public void mult() {
        if (stack.size() < 2) {
            System.out.println("Don't enough variables for multiplication. Stack consist of" + stack.size() + "variables");
        } else {
            stack.push(stack.pop() * stack.pop());
        }
    }

    private boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
