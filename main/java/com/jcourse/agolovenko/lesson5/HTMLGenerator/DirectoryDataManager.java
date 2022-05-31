package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DirectoryDataManager implements IDirectoryParser {
    private final Map<String, NodeInfo> files = new TreeMap<>();
    private final Map<String, NodeInfo> directories = new TreeMap<>();
    private final String dir;

    public DirectoryDataManager(String sourceDirectory) {
        this.dir = sourceDirectory;
    }

    @Override
    public String getRoot() {
        return dir;
    }

    @Override
    public Map<String, NodeInfo> getFiles() {
        return files;
    }

    @Override
    public Map<String, NodeInfo> getDirectories() {
        return directories;
    }

    @Override
    public void parseDirectoryData() {
        Path currPath = Path.of(dir);
        FileTime creationTime;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currPath)) {
            for (Path path : stream) {
                String nodeName;
                creationTime = (FileTime) Files.getAttribute(path, "creationTime");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(creationTime.toMillis());
                nodeName = path.getFileName().toString();
                if (!Files.isDirectory(path)) {
                    files.put(nodeName, new NodeInfo(path.toString(), df.format(date), Files.size(path)));
                } else {
                    directories.put(nodeName, new NodeInfo(path.toString(), df.format(date), 0L));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
