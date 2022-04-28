package com.jcourse.agolovenko.lesson2.datamanagers;

import com.jcourse.agolovenko.lesson2.datamanagers.IDataManager;

public class StorageManager implements IDataManager {
    private Double result;

    public Double getResult() {
        return result;
    }

    @Override
    public void processData(Double number) {
        result = number;
    }
}
