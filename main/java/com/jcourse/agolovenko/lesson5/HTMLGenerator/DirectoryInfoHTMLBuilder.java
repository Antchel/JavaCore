package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DirectoryInfoHTMLBuilder implements IHTMLBuilder {
    private final DirectoryDataStorage data;
    @SuppressWarnings("all")
    private String templatePath = "./anton-golovenko/main/resources/DirectoryListTemplate.vm";

    public DirectoryInfoHTMLBuilder(DirectoryDataStorage data) {
        this.data = data;
    }

    public Template getTemplate() {
        Template template = new Template();

        try {
            template = Velocity.getTemplate(templatePath);
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
        return template;
    }

    public Template getTemplate(String templatePath) {
        this.templatePath = templatePath;
        Template template = new Template();
        try {
            template = Velocity.getTemplate(templatePath);
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
        return template;
    }


    public String getHTMLString(Template template) {
        StringWriter writer = new StringWriter();
        DirectoryFirstSorter sorter = new DirectoryFirstSorter();
        List<NodeInfo> sortedList = sorter.sort(data.getDirNodes());
        VelocityContext context = new VelocityContext();

        context.put("nodeList", sortedList);
        context.put("dir", data.getRoot());
        template.merge(context, writer);

        return writer.toString();
    }

    public void generateHTMLFile(String HTMLFilename) throws IOException {

        if (!Files.isDirectory(Path.of(data.getRoot())))
            throw new IOException("Wrong directory");
        IParser parser = new DirectoryDataCollector(data);
        parser.parse();
        String out = getHTMLString(getTemplate());
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(HTMLFilename))) {
            writer.write(out);
        }
//        System.out.println(out.toString());
    }
}
