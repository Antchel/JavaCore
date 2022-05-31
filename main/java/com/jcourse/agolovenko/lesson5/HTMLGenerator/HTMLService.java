package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.io.IOException;

public class HTMLService {

    public static void main(String[] args) throws IOException {
        HTMLGenerator html = new HTMLGenerator(new DirectoryDataManager("C:\\Work\\"));
        html.generateHTMLFile("./res.html");
    }
}
