package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.datamanagers.TestPrintDevice;
import com.jcourse.agolovenko.lesson3.ExecuteMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CalculatorTest {

    static Logger log = LoggerFactory.getLogger("TEST");
    @Test
    public void evaluateSQRT() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestPrintDevice dataManager = new TestPrintDevice();
        Calculator calculator = new Calculator(dataManager);
        ExecuteMode mode = ExecuteMode.DEBUG;
        CommandFactory factory = new CommandFactory(calculator, mode);
        CommandInvoker invoker = new CommandInvoker();
        Client client = new Client(invoker,factory);

        String x1 = " ( - b + sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
        String x2 = " ( - b - sqrt ( b * b - 4 * a * c ) ) / ( 2 * a ) ";
        Map<String, Double> params = Map.of("a", 1., "b", 0., "c", -16.);
        {
            client.evaluateExpression(x1, params);
            Assertions.assertEquals(dataManager.getResult(), 4.0, 0.00001);
            client.evaluateExpression(x2, params);
            Assertions.assertEquals(dataManager.getResult(), -4.0, 0.00001);
            log.info("Passed");
        }
        {
            params = Map.of("a", 1., "b", 10., "c", 20.);
            client.evaluateExpression(x1, params);
            Assertions.assertEquals(dataManager.getResult(), -2.763932, 0.00001);
            client.evaluateExpression(x2, params);
            Assertions.assertEquals(dataManager.getResult(), -7.2360679, 0.00001);
            log.info("Passed");
        }
        {
            params = Map.of("a", 1., "b", 5., "c", 0.);
            client.evaluateExpression(x1, params);
            Assertions.assertEquals(dataManager.getResult(), 0., 0.00001);
            client.evaluateExpression(x2, params);
            Assertions.assertEquals(dataManager.getResult(), -5., 0.00001);
            log.info("Passed");
        }
        {
            params = Map.of("a", 12., "b", 4., "c", 7.);
            client.evaluateExpression(x1, params);
            Assertions.assertTrue(dataManager.getResult().isNaN());
            client.evaluateExpression(x2, params);
            Assertions.assertTrue(dataManager.getResult().isNaN());
            log.info("Passed");
        }
    }
}
