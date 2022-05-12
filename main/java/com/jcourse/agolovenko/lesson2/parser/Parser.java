package com.jcourse.agolovenko.lesson2.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {

    public String[] parse(String stringToPArse) {
        int paramCnt = 0;
        Pattern p = Pattern.compile("[a-zA-Z0-9+-/*]+");
        String[] params = new String[3];
        Matcher m1 = p.matcher(stringToPArse);

        while (m1.find() && paramCnt < 3) {
            params[paramCnt] = m1.group();
            paramCnt++;
        }
        return params;
    }
}
