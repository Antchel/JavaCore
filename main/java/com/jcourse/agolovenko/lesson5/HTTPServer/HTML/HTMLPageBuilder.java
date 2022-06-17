package com.jcourse.agolovenko.lesson5.HTTPServer.HTML;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.IHTMLBuilder;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.VelocityHtmlGenerator;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.DirectoryModel;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;

import java.io.StringWriter;

public abstract class HTMLPageBuilder {

    public static void HTMLPageBuild(String dirPath, String templatePath, String templateObjectName, StringWriter writer) throws IllegalAccessException {
        IDirectoryModel model = DirectoryModel.fromFileSystemDirectory(dirPath);
        IHTMLBuilder html = new VelocityHtmlGenerator(templatePath, templateObjectName);
        html.generateHtmlByModel(model, writer);
    }
}
