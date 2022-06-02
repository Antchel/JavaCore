package com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel;

public class NodeInfo {

    private final String nodeName;
    private final String nodePath;
    private final String date;
    private final long size;
    private final boolean isFile;

    public NodeInfo(String nodeName, String nodePath, String date, long size, boolean isFile) {
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.date = date;
        this.size = size;
        this.isFile = isFile;
    }

    public String getNodeNameInLowerCase() {
        return nodeName.toLowerCase();
    }

    public String getNodeName() {
        return nodeName;
    }


    public String getNodePath() {
        return nodePath;
    }


    public String getDate() {
        return date;
    }


    public Long getSize() {
        return size;
    }


    public boolean getIsFile() {
        return isFile;
    }

}
