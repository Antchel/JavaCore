package com.jcourse.agolovenko.lesson6.Details;

import java.util.UUID;

public class Accessories implements IStorageItem {

    private final String serialNumber;

    public Accessories() {
        this.serialNumber = UUID.randomUUID().toString();
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }
}
