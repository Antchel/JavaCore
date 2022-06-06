package com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.Collector.DirectoryDataCollector;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryModel implements IDirectoryModel {
    private final List<NodeInfo> dirNodes = new ArrayList<>();
    private static String root;

    private DirectoryModel() {
        new DirectoryDataCollector(this).collect().inDirFirstOrder();
    }

    public static IDirectoryModel fromFileSystemDirectory(String path) throws IllegalAccessException {
        if (!Files.isDirectory(Path.of(path)))
            throw new IllegalAccessException("String [" + path + "] is not a directory");
        root = path;
        return new DirectoryModel();
    }

    @Override
    public void putData(NodeInfo data) {
        dirNodes.add(data);

    }

    @Override
    public String getRoot() {
        return root;
    }

    @Override
    public List<NodeInfo> getDirNodes() {
        return Collections.unmodifiableList(dirNodes);
    }

    @Override
    public void clearNodes() {
        dirNodes.clear();
    }
}
