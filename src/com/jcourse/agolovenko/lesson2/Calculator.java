package com.jcourse.agolovenko.lesson2;

import com.jcourse.agolovenko.lesson2.parser.Parser;
import com.jcourse.agolovenko.lesson2.test.UnitTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        ALU calculator = new ALU();
        Invoker inv = new Invoker();
        Scanner data;
        if (args.length == 0) {
            data = new Scanner(System.in);
        } else {
            File file = new File(args[0]);
            try {
                data = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Parser parser = new Parser(data);
        while (parser.hasNextLine()) {
            String[] params = parser.parse();
            try {
                inv.invoke(calculator, params);
            } catch (NoSuchMethodException | InvocationTargetException
                    | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        ALU calc = new ALU();
        UnitTest.runTest(calc);

    }
}
