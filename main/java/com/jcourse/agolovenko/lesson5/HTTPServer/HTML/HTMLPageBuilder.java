package com.jcourse.agolovenko.lesson5.HTTPServer.HTML;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.IHTMLBuilder;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.VelocityHtmlGenerator;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.DirectoryModel;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;

import java.io.StringWriter;

public class HTMLPageBuilder {

    private final StringWriter writer = new StringWriter();

    public HTMLPageBuilder(String dirPath, String templatePath, String templateObjectName) throws IllegalAccessException {
        IDirectoryModel model = DirectoryModel.fromFileSystemDirectory(dirPath);
        IHTMLBuilder html = new VelocityHtmlGenerator(templatePath, templateObjectName);
        html.generateHtmlByModel(model, writer);
    }

    public StringWriter getWriter() {
        return writer;
    }
}
