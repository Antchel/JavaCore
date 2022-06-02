package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryFirstSorter implements ISorter{
    @Override
    public List<NodeInfo> sort(List<NodeInfo> dataToSort) {

        return Stream.concat(dataToSort.stream()
                                        .filter(el -> !el.getIsFile())
                                        .sorted(Comparator.comparing(NodeInfo::getNodeNameInLowerCase)),
                             dataToSort.stream()
                                        .filter(NodeInfo::getIsFile)
                                        .sorted(Comparator.comparing(NodeInfo::getNodeNameInLowerCase)))
                     .collect(Collectors.toList());
    }
}
