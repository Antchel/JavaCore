package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import org.apache.velocity.Template;

import java.io.*;

public interface IHTMLBuilder {

    Template getTemplate();
    Template getTemplate(String templatePath);

    String getHTMLString(Template template);

    void generateHTMLFile(String HTMLFilename) throws IOException;
}
