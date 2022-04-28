package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.datamanagers.IDataManager;
import com.jcourse.agolovenko.lesson2.datamanagers.StorageManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CalculatorTest {
    @Test
    public void evaluateSQRT() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        StorageManager dataManager = new StorageManager();
        Calculator calculator = new Calculator(dataManager);
        CommandFactory factory = new CommandFactory(calculator);
        CommandInvoker invoker = new CommandInvoker();
        Client client = new Client(invoker,factory);

        String x1 = " ( - b + sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
        String x2 = " ( - b - sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
        System.out.println();
        Map<String,Double> params = Map.of("a", 1., "b", 0., "c", -16.);
        client.evaluateExpression(x1, params);
        Assertions.assertEquals(dataManager.getResult(),4.0,0.00001);
        System.out.println("X1 = " + dataManager.getResult());
        client.evaluateExpression(x2, params);
        Assertions.assertEquals(dataManager.getResult(),-4.0,0.00001);
        System.out.println("X2 = " + dataManager.getResult());
        System.out.println();
        params = Map.of("a", 1., "b", 10., "c", 20.);
        client.evaluateExpression(x1, params);
        System.out.println("X1 = " + dataManager.getResult());
        Assertions.assertEquals(dataManager.getResult(),-2.763932,0.00001);
        client.evaluateExpression(x2, params);
        System.out.println("X2 = " + dataManager.getResult());
        Assertions.assertEquals(dataManager.getResult(),-7.2360679,0.00001);

        System.out.println();
        params = Map.of("a", 1., "b", 5., "c", 0.);
        client.evaluateExpression(x1, params);
        System.out.println("X1 = " + dataManager.getResult());
        Assertions.assertEquals(dataManager.getResult(),0.,0.00001);
        client.evaluateExpression(x2, params);
        System.out.println("X2 = " + dataManager.getResult());
        Assertions.assertEquals(dataManager.getResult(),-5.,0.00001);
        System.out.println();
        params = Map.of("a", 12., "b", 4., "c", 7.);
        client.evaluateExpression(x1, params);
        System.out.println("X1 = " + dataManager.getResult());
        Assertions.assertTrue(dataManager.getResult().isNaN());
        client.evaluateExpression(x2, params);
        System.out.println("X2 = " + dataManager.getResult());
        Assertions.assertTrue(dataManager.getResult().isNaN());
        System.out.println();

    }
}
