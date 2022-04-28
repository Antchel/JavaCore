package com.jcourse.agolovenko.lesson2.test;

import com.jcourse.agolovenko.lesson2.Calculator;
import com.jcourse.agolovenko.lesson2.Client;
import com.jcourse.agolovenko.lesson2.Factory;
import com.jcourse.agolovenko.lesson2.Invoker;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class UnitTest {
    public static void runTest() {
        Client client = new Client(new Calculator(), new Invoker(), new Factory());
        try {
            String x1 = " ( - b + sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
            String x2 = " ( - b - sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
            System.out.println();
            Map<String,Double> params = Map.of("a", 1., "b", 0., "c", -16.);
            System.out.print("X1 = ");
            client.evaluateExpression(x1, params);
            System.out.print("X2 = ");
            client.evaluateExpression(x2, params);
            System.out.println();
            params = Map.of("a", 1., "b", 10., "c", 20.);
            client.evaluateExpression(x1, params);
            client.evaluateExpression(x2, params);
            System.out.println();
            params = Map.of("a", 1., "b", 5., "c", 0.);
            client.evaluateExpression(x1, params);
            client.evaluateExpression(x2, params);
            System.out.println();
            params = Map.of("a", 12., "b", 4., "c", 7.);
            client.evaluateExpression(x1, params);
            client.evaluateExpression(x2, params);
            System.out.println();
        } catch (NoSuchMethodException | InvocationTargetException|
                InstantiationException | IllegalAccessException|  ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}
