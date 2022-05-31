package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.util.Map;

public interface IDirectoryParser {
    Map<String, NodeInfo> getFiles();

    Map<String, NodeInfo> getDirectories();

    void parseDirectoryData();

    String getRoot();
}
