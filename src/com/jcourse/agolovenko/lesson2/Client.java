package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;
import com.jcourse.agolovenko.lesson2.parser.Parser;
import com.jcourse.agolovenko.lesson2.parser.RPNParser;
import com.jcourse.agolovenko.lesson2.test.UnitTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Client {
    private final ICalculator calculator;
    private final Invoker invoker;
    public final Factory factory;

    public Client(ICalculator calc, Invoker invoker, Factory factory) {
        this.calculator = calc;
        this.invoker = invoker;
        this.factory = factory;
    }

    private String[] denoteExpression(String expression){
        Scanner str = new Scanner(expression);
        Stack<String> strings = new Stack<>();
        while(str.hasNext("[0-9a-zA-Z+-/*()]|sqrt")){
            strings.push(str.next("[0-9a-zA-Z+-/*()]|sqrt"));
        }
        String[] res = new String[strings.size()];
        int size = strings.size() - 1;
        for (int i = size; i >= 0; i--) {
            res[i] = strings.pop();
        }
        return res;
    }

    public void evaluateExpression(String expression, Map<String,Double> params) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ArithmeticException{
        RPNParser parser = new RPNParser();
        String[] denotedExpression = denoteExpression(expression);
        ArrayList<String> tokens = parser.convertToRPN(denotedExpression);
        tokens.add("PRINT");
        StringBuilder line = new StringBuilder();
        for ( var entry : params.entrySet()) {
            line.append("DEFINE "+entry.getKey() + " " + entry.getValue() + "\n");
        }
        for (String token : tokens) {
            line.append(token).append("\n");
        }
        line.append("POP\n");
        Scanner data = new Scanner(line.toString());
        evaluateExpression(data);
    }

    public void evaluateExpression(Scanner source) throws NoSuchMethodException, InvocationTargetException,
                     InstantiationException, IllegalAccessException, ArithmeticException{
        Parser parser = new Parser(source);
        while (parser.hasNextLine()) {
            String[] params = parser.parse();
            String[] tail = Arrays.copyOfRange(params, 1, params.length);
            Command command = factory.getCommandByName(params[0]).newInstance(calculator, tail);

            invoker.invoke(command);

        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Invoker inv = new Invoker();
        Factory factory = new Factory();
        Scanner data;
        Client client = new Client(calculator, inv, factory);
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
        } catch (NoSuchMethodException | InvocationTargetException|
                InstantiationException | IllegalAccessException|  ArithmeticException e){
            System.out.println(e.getMessage());
        }

        UnitTest.runTest();
    }
}
