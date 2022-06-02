package com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryModel implements IDirectoryModel {
    private final List<NodeInfo> dirNodes = new ArrayList<>();
    private String root;

    public void fromFileSystemDirectory(String path) throws IllegalAccessException {
         if (!Files.isDirectory(Path.of(path)))
             throw new IllegalAccessException("String [" + path +"] is not a directory");
        this.root = path;
    }

    public void putData(NodeInfo data) {
        dirNodes.add(data);

    }

    public String getRoot() {
        return root;
    }

    public List<NodeInfo> getDirNodes() {
        return Collections.unmodifiableList(dirNodes);
    }
}
