package com.jcourse.agolovenko.lesson6.Details;

import java.util.UUID;

public class Car implements IStorageItem {

    private final String serialNumber;
    private final CarBody carBody;
    private final Accessories accessories;
    private final Engine engine;

    public Car(CarBody carBody, Accessories accessories, Engine engine) {
        serialNumber = UUID.randomUUID().toString();
        this.engine = engine;
        this.accessories = accessories;
        this.carBody = carBody;
    }
    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

}
