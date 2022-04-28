package com.jcourse.agolovenko.lesson2.datamanagers;

import com.jcourse.agolovenko.lesson2.datamanagers.IDataManager;

public class ConsoleManager implements IDataManager {
    @Override
    public void processData(Double number) {
        System.out.println(number);
    }
}
