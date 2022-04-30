package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;
import com.jcourse.agolovenko.lesson2.datamanagers.ConsolePrintDevice;
import com.jcourse.agolovenko.lesson2.datamanagers.IPrintDevice;
import com.jcourse.agolovenko.lesson2.parser.Parser;
import com.jcourse.agolovenko.lesson2.parser.RPNParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public record Client(CommandInvoker invoker,
                     CommandFactory factory) {

    private String[] denoteExpression(String expression) {
        Scanner str = new Scanner(expression);
        Stack<String> strings = new Stack<>();
        while (str.hasNext("[0-9a-zA-Z+-/*()]|sqrt")) {
            strings.push(str.next("[0-9a-zA-Z+-/*()]|sqrt"));
        }
        String[] res = new String[strings.size()];
        int size = strings.size() - 1;
        for (int i = size; i >= 0; i--) {
            res[i] = strings.pop();
        }
        return res;
    }

    public void evaluateExpression(String expression, Map<String, Double> params) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ArithmeticException {
        RPNParser parser = new RPNParser();
        String[] denotedExpression = denoteExpression(expression);
        ArrayList<String> tokens = parser.convertToRPN(denotedExpression);
        tokens.add("PRINT");
        StringBuilder line = new StringBuilder();
        for (var entry : params.entrySet()) {
            line.append("DEFINE ").append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        for (String token : tokens) {
            line.append(token).append("\n");
        }
        line.append("POP\n");
        Scanner data = new Scanner(line.toString());
        evaluateExpression(data);
    }

    public void evaluateExpression(Scanner source) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ArithmeticException {
        Parser parser = new Parser(source);
        while (parser.hasNextLine()) {
            String[] params = parser.parse();
            String[] tail = Arrays.copyOfRange(params, 1, params.length);
            Command command = factory.getCommandByName(params[0], tail);

            invoker.invoke(command);

        }
    }

    public static void main(String[] args) {
        IPrintDevice dataManager = new ConsolePrintDevice();
        Calculator calculator = new Calculator(dataManager);
        CommandInvoker inv = new CommandInvoker();
        CommandFactory factory = new CommandFactory(calculator);
        Scanner data;
        Client client = new Client(inv, factory);
        if (args.length == 0) {
            data = new Scanner(System.in);
        } else {
            File file = new File(args[0]);
            try {
                data = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        try {
            client.evaluateExpression(data);
        } catch (NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
