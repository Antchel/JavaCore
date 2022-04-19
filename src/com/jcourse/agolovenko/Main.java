package com.jcourse.agolovenko;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        firstTask();
        secondTask();
    }

    public static void firstTask() throws UnsupportedEncodingException {
        Scanner console = new Scanner(System.in);
        System.out.println(System.getProperty("file.encoding"));
        String str = new String(console.nextLine().getBytes(StandardCharsets.UTF_8), System.getProperty("file.encoding"));
        System.out.println(str);
    }

    public static void secondTask() {
        Magicnumber game = new Magicnumber();
        game.StartGame();
    }
}