package com.jcourse.agolovenko.lesson5.HTMLGenerator.Builder;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;

import java.io.Writer;

public interface IHTMLBuilder {

    void generateHtmlByModel(IDirectoryModel model, Writer writer);
    void writeHTMLtoFile(Writer HTMLData, String HTMLFilePath);
}
