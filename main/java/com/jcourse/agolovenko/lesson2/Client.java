package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.commands.Command;
import com.jcourse.agolovenko.lesson2.datamanagers.ConsolePrintDevice;
import com.jcourse.agolovenko.lesson2.datamanagers.IPrintDevice;
import com.jcourse.agolovenko.lesson2.parser.Parser;
import com.jcourse.agolovenko.lesson2.parser.RPNParser;
import com.jcourse.agolovenko.lesson3.ExecuteMode;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public record Client(CommandInvoker invoker,
                     CommandFactory factory) {

    @Option(names = {"-P", "--path"}, description = "The filePath option")
    private static String filePath = null;
    @Option(names = {"-M", "--mode"}, description = "The execute mode option")
    private static String executionMode = "RELEASE";

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

    /**
     * evaluateExpression provides calculation of expression in arithmetical form ((5+2)*2) etc.
     * @param expression arithmetic equation
     * @param params used in case of variables values in expression, else null
     */
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
        BufferedReader data = new BufferedReader(new StringReader(line.toString()));
        try {
            evaluateExpression(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param source Input source for RPN commands for calculator
     */
    public void evaluateExpression(BufferedReader source) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ArithmeticException, IOException {
        Parser parser = new Parser();
        String line;
        while ((line = source.readLine()) != null) {
            String[] params = parser.parse(line);
            String[] tail = Arrays.copyOfRange(params, 1, params.length);
            try {
                Command command = factory.getCommandByName(params[0], tail);
                invoker.invoke(command);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        IPrintDevice dataManager = new ConsolePrintDevice();
        Calculator calculator = new Calculator(dataManager);
        CommandInvoker inv = new CommandInvoker();
        new CommandLine(new Client(null, null)).parseArgs(args);
        ExecuteMode mode = executionMode.compareToIgnoreCase("debug") == 0 ? ExecuteMode.DEBUG : ExecuteMode.RELEASE;
        CommandFactory factory = new CommandFactory(calculator, mode);
        Client client = new Client(inv, factory);
        BufferedReader data;

        if (filePath == null) {
            data = new BufferedReader(new InputStreamReader(System.in));
        } else {
            try {
                data = new BufferedReader(new FileReader(filePath));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        try {
            client.evaluateExpression(data);
        } catch (NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException | ArithmeticException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
