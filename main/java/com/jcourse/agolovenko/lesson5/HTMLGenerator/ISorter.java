package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.util.List;

@FunctionalInterface
public interface ISorter {
    List<NodeInfo> sort(List<NodeInfo> dataToSort);
}
