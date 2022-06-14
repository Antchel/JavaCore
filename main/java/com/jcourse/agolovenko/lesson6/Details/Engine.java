package com.jcourse.agolovenko.lesson6.Details;

import java.util.UUID;

public class Engine implements IStorageItem {
    private final String serialNumber;

    public Engine() {
        this.serialNumber = UUID.randomUUID().toString();
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }
}
