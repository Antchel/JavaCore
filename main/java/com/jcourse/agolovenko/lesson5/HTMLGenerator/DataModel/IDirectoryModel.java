package com.jcourse.agolovenko.lesson5.HTMLGenerator.DataModel;

import java.util.List;

public interface IDirectoryModel {

    void putData(NodeInfo data);

    String getRoot();

    List<NodeInfo> getDirNodes();

    void clearNodes();
}
