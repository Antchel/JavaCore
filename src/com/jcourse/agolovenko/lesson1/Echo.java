package com.jcourse.agolovenko.lesson1;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author Anton Golovenko
 * Echo class implement displaying User input from Console or IDE terminal
 */
public class Echo {
    public void ExecuteEcho() throws UnsupportedEncodingException {
        Console console = System.console();
        Scanner keyboard = new Scanner(System.in);
        if (console != null) {
            System.out.println(console.charset().name());
            String str = new String(keyboard.nextLine().getBytes(System.getProperty("file.encoding")), console.charset().name());
            System.out.println(str);
        } else {
            System.out.println(keyboard.nextLine());
        }
    }
}
