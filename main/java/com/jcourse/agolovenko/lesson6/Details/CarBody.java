package com.jcourse.agolovenko.lesson6.Details;

import java.util.UUID;

public class CarBody implements IStorageItem {
    private final String serialNumber;

    public CarBody() {
        this.serialNumber = UUID.randomUUID().toString();
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }
}
