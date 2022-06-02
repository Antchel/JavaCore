package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import org.apache.velocity.Template;

import java.io.IOException;

public class HTMLService {

    public static void main(String[] args) throws IOException {
        DirectoryDataStorage storage = new DirectoryDataStorage("C:\\Work\\prj\\apache");
        IHTMLBuilder html = new DirectoryInfoHTMLBuilder(storage);
        html.generateHTMLFile("./res.html");
    }
}
