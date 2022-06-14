package com.jcourse.agolovenko.lesson6;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Configurator {

    private static final int bodyWarehouseCapacity;
    private static final int engineWarehouseCapacity;
    private static final int accessoryWarehouseCapacity;
    private static final int storageAutoSize;
    private static final int accessorySuppliers;
    private static final int workers;
    private static final int carDealers;
    private static final boolean logSale;
    private static final int creationItemFreq;
    private static final int carSoldFreq;

    private static final Properties SETTINGS = new Properties();

    static {
        InputStream settings = Configurator.class.getResourceAsStream("/FactoryConfig.conf");
        try {
            SETTINGS.load(settings);
            Objects.requireNonNull(settings).close();
        } catch (IOException e) {
            throw new RuntimeException("Could not find configuration properties file");
        }
        bodyWarehouseCapacity = Integer.parseInt((String) SETTINGS.get("BodyWarehouseCapacity"));
        engineWarehouseCapacity = Integer.parseInt((String) SETTINGS.get("EngineWarehouseCapacity"));
        accessoryWarehouseCapacity = Integer.parseInt((String) SETTINGS.get("AccessoryWarehouseCapacity"));
        storageAutoSize = Integer.parseInt((String) SETTINGS.get("StorageAutoSize"));
        accessorySuppliers = Integer.parseInt((String) SETTINGS.get("AccessorySuppliers"));
        workers = Integer.parseInt((String) SETTINGS.get("Workers"));
        carDealers = Integer.parseInt((String) SETTINGS.get("CarDealers"));
        logSale = Boolean.parseBoolean((String) SETTINGS.get("LogSale"));
        creationItemFreq = Integer.parseInt((String) SETTINGS.get("CreationItemFreq"));
        carSoldFreq = Integer.parseInt((String) SETTINGS.get("CarSoldFreq"));
    }

    public static int getBodyWarehouseCapacity() {
        return bodyWarehouseCapacity;
    }

    public static int getEngineWarehouseCapacity() {
        return engineWarehouseCapacity;
    }

    public static int getAccessoryWarehouseCapacity() {
        return accessoryWarehouseCapacity;
    }

    public static int getStorageAutoSize() {
        return storageAutoSize;
    }

    public static int getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public static int getWorkers() {
        return workers;
    }

    public static int getCarDealers() {
        return carDealers;
    }

    public static boolean getLogSale() {
        return logSale;
    }
    public static int getCreationItemFreq() {
        return creationItemFreq;
    }
    public static int getCarSoldFreq() {
        return carSoldFreq;
    }
}




