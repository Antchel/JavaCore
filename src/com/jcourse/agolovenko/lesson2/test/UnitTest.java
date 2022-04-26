package com.jcourse.agolovenko.lesson2.test;

import com.jcourse.agolovenko.lesson2.ALU;
import com.jcourse.agolovenko.lesson2.Invoker;

import java.lang.reflect.InvocationTargetException;

public class UnitTest {
    private static void evaluateSqrtRoots(ALU calculator, Double a, Double b, Double c) {
        Invoker inv = new Invoker();
        try {
            inv.invoke(calculator, new String[]{"define", "a", a.toString()});
            inv.invoke(calculator, new String[]{"define", "b", b.toString()});
            inv.invoke(calculator, new String[]{"define", "c", c.toString()});
            // b^2
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"*"});
            // 4ac
            inv.invoke(calculator, new String[]{"push", "4"});
            inv.invoke(calculator, new String[]{"push", "a"});
            inv.invoke(calculator, new String[]{"*"});
            inv.invoke(calculator, new String[]{"push", "c"});
            inv.invoke(calculator, new String[]{"*"});
            // b^2-4ac
            inv.invoke(calculator, new String[]{"-"});
            //sqrt(b^2-4ac)
            inv.invoke(calculator, new String[]{"sqrt"});
            //-b + sqrt(b^2-4ac)
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"-"});
            //2a
            inv.invoke(calculator, new String[]{"push", "2"});
            inv.invoke(calculator, new String[]{"push", "a"});
            inv.invoke(calculator, new String[]{"*"});
            //(-b + sqrt(b^2-4ac))/ 2a
            inv.invoke(calculator, new String[]{"/"});
            System.out.print("First root = ");
            inv.invoke(calculator, new String[]{"print"});
            inv.invoke(calculator, new String[]{"pop"});
            System.out.print("-----");

//-----------------------------------------------------------------------------

            inv.invoke(calculator, new String[]{"define", "a", a.toString()});
            inv.invoke(calculator, new String[]{"define", "b", b.toString()});
            inv.invoke(calculator, new String[]{"define", "c", c.toString()});
            // b^2
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"*"});
            // 4ac
            inv.invoke(calculator, new String[]{"push", "4"});
            inv.invoke(calculator, new String[]{"push", "a"});
            inv.invoke(calculator, new String[]{"*"});
            inv.invoke(calculator, new String[]{"push", "c"});
            inv.invoke(calculator, new String[]{"*"});
            // b^2-4ac
            inv.invoke(calculator, new String[]{"-"});
            //-sqrt(b^2-4ac)
            inv.invoke(calculator, new String[]{"sqrt"});
            inv.invoke(calculator, new String[]{"-"});
            //-b + sqrt(b^2-4ac)
            inv.invoke(calculator, new String[]{"push", "b"});
            inv.invoke(calculator, new String[]{"-"});
            //2a
            inv.invoke(calculator, new String[]{"push", "2"});
            inv.invoke(calculator, new String[]{"push", "a"});
            inv.invoke(calculator, new String[]{"*"});
            //(-b + sqrt(b^2-4ac))/ 2a
            inv.invoke(calculator, new String[]{"/"});
            System.out.print("Second root = ");
            inv.invoke(calculator, new String[]{"print"});
            inv.invoke(calculator, new String[]{"pop"});
            System.out.println();
        } catch (NoSuchMethodException |InvocationTargetException|
                InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void runTest(ALU calculator) {
        evaluateSqrtRoots(calculator, 1., 2., 1.);
        evaluateSqrtRoots(calculator, 0., 0., 0.);
        evaluateSqrtRoots(calculator, 0., -10., 50.);
        evaluateSqrtRoots(calculator, 1., 0., -16.);
        }
}
