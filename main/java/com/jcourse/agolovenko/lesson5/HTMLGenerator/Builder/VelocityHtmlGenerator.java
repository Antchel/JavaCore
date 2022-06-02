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

    public VelocityHtmlGenerator(String pathToTemplate) {
        this.template = new Template();
        try {
            template = Velocity.getTemplate(pathToTemplate);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get template from ["+pathToTemplate+"]");
        }
    }

    public void generateHtmlByModel(IDirectoryModel model, Writer writer) {
        new DirectoryDataCollector(model).collect();
        List<NodeInfo> sortedList = Stream.concat(model.getDirNodes().stream()
                                .filter(el -> !el.getIsFile())
                                .sorted(Comparator.comparing(NodeInfo::getNodeNameInLowerCase)),
                        model.getDirNodes().stream()
                                .filter(NodeInfo::getIsFile)
                                .sorted(Comparator.comparing(NodeInfo::getNodeNameInLowerCase)))
                .collect(Collectors.toList());

        VelocityContext context = new VelocityContext();

        context.put("nodeList", sortedList);
        context.put("dir", model.getRoot());
        template.merge(context, writer);
    }

    public void writeHTMLtoFile(Writer HTMLData, String HTMLFilePath){
        try (PrintWriter file = new PrintWriter(new FileOutputStream(HTMLFilePath))) {
            file.write(HTMLData.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't create file in ["+HTMLFilePath+"]");
        }
    }
}
