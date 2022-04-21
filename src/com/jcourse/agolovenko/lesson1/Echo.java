package com.jcourse.agolovenko.lesson1;

import java.io.*;
import java.util.Scanner;

/**
 * @author Anton Golovenko
 * Echo class implement displaying User input from Console or IDE terminal
 */
public class Echo {
    /**
     * Waiting for user input and send this data to console
     */
    public void executeEcho() throws IOException {

        if (System.console() != null) {
            byte[] byteStr;
            System.out.println(System.console().charset().name());
            byteStr = System.console().readLine().getBytes(System.console().charset().name());
            System.out.println(new String(byteStr,System.console().charset().name()));
        } else {
            System.out.println(System.getProperty("file.encoding"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(br.readLine());
        }
    }
}
