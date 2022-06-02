package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryDataStorage {
    private final List<NodeInfo> dirNode;
    private final String root;

    public DirectoryDataStorage(String rootDirectory) {
        this.dirNode = new ArrayList<>();
        this.root = rootDirectory;
    }

    public void putData(NodeInfo data) {
        dirNode.add(data);

    }

    public String getRoot() {
        return root;
    }

    public List<NodeInfo> getDirNodes() {
        return Collections.unmodifiableList(dirNode);
    }
}
