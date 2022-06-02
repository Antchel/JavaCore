package com.jcourse.agolovenko.lesson5.HTMLGenerator.Collector;

import com.jcourse.agolovenko.lesson5.HTMLGenerator.Collector.ICollector;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.IDirectoryModel;
import com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel.NodeInfo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryDataCollector implements ICollector {
    private final IDirectoryModel storage;

    public DirectoryDataCollector(IDirectoryModel storage) {
        this.storage = storage;
    }

    @Override
    public void collect() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(storage.getRoot()))) {
            for (Path path : stream) {
                FileTime creationTime = (FileTime) Files.getAttribute(path, "creationTime");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(creationTime.toMillis());
                String nodeName = path.getFileName().toString();
                boolean isFile = false;
                long size = 0;
                if (!Files.isDirectory(path)) {
                    size = Files.size(path);
                    isFile = true;
                }
                storage.putData(new NodeInfo(nodeName, path.toString(), df.format(date), size, isFile));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
