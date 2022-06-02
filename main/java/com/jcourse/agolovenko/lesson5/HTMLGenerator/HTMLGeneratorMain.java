package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.IHTMLBuilder;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder.VelocityHtmlGenerator;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.DirectoryModel;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;

import java.io.*;

public class HTMLGeneratorMain {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        IDirectoryModel model = new DirectoryModel();
        model.fromFileSystemDirectory("C:\\Work\\");

        IHTMLBuilder html = new VelocityHtmlGenerator("./anton-golovenko/main/resources/DirectoryListTemplate.vm");
        StringWriter writer = new StringWriter();
        html.generateHtmlByModel(model, writer);
        html.writeHTMLtoFile(writer, "./res.html");
    }
}
