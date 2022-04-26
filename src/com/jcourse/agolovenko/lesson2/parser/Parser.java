package com.jcourse.agolovenko.lesson2.parser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Parser(Scanner source) {

    public String[] parse() {
        int paramCnt = 0;
        Pattern p = Pattern.compile("[a-zA-Z0-9+-/*]+");
        String[] params = new String[3];
        Matcher m1 = p.matcher(source.nextLine());
        while (m1.find() && paramCnt < 3) {
            params[paramCnt] = m1.group();
            paramCnt++;
        }
        return params;
    }

    public boolean hasNextLine() {
        return source.hasNextLine();
    }
}
