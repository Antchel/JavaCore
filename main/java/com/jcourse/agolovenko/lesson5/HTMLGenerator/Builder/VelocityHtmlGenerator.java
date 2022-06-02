package com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.NodeInfo;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.Collector.DirectoryDataCollector;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VelocityHtmlGenerator implements IHTMLBuilder {
    private Template template;
    private final String templateObjectName;

    public VelocityHtmlGenerator(String pathToTemplate, String templateObjectName) {
        this.template = new Template();
        this.templateObjectName = templateObjectName;
        try {
            template = Velocity.getTemplate(pathToTemplate);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get template from [" + pathToTemplate + "]");
        }
    }

    public void generateHtmlByModel(IDirectoryModel model, Writer writer) {

        VelocityContext context = new VelocityContext();

        context.put(templateObjectName, model);
//        context.put("dir", model.getRoot());
        template.merge(context, writer);
    }

    public void writeHTMLtoFile(Writer HTMLData, String HTMLFilePath) {
        try (PrintWriter file = new PrintWriter(new FileOutputStream(HTMLFilePath))) {
            file.write(HTMLData.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't create file in [" + HTMLFilePath + "]");
        }
    }
}
