package com.jcourse.agolovenko.lesson6;

import java.io.IOException;
import java.io.InputStream;
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

    private static final int DEFAULT_VALUE = 10;

    private static final Properties SETTINGS = new Properties();

    static {

        try (InputStream settings = Configurator.class.getResourceAsStream("/FactoryConfig.conf");) {
            SETTINGS.load(settings);
        } catch (IOException e) {
            throw new RuntimeException("Could not find configuration properties file");
        }

        bodyWarehouseCapacity = SETTINGS.get("BodyWarehouseCapacity") != null
                ? Integer.parseInt((String) SETTINGS.get("BodyWarehouseCapacity"))
                : DEFAULT_VALUE;
        engineWarehouseCapacity = SETTINGS.get("EngineWarehouseCapacity") != null
                ? Integer.parseInt((String) SETTINGS.get("EngineWarehouseCapacity"))
                : DEFAULT_VALUE;
        accessoryWarehouseCapacity = SETTINGS.get("AccessoryWarehouseCapacity") != null
                ? Integer.parseInt((String) SETTINGS.get("AccessoryWarehouseCapacity"))
                : DEFAULT_VALUE;
        storageAutoSize = SETTINGS.get("StorageAutoSize") != null
                ? Integer.parseInt((String) SETTINGS.get("StorageAutoSize"))
                : DEFAULT_VALUE;
        accessorySuppliers = SETTINGS.get("AccessorySuppliers") != null
                ? Integer.parseInt((String) SETTINGS.get("AccessorySuppliers"))
                : DEFAULT_VALUE;
        workers = SETTINGS.get("Workers") != null
                ? Integer.parseInt((String) SETTINGS.get("Workers"))
                : DEFAULT_VALUE;
        carDealers = SETTINGS.get("CarDealers") != null
                ? Integer.parseInt((String) SETTINGS.get("CarDealers"))
                : DEFAULT_VALUE;
        logSale = SETTINGS.get("LogSale") == null || Boolean.parseBoolean((String) SETTINGS.get("LogSale"));
        creationItemFreq = SETTINGS.get("CreationItemFreq") != null
                ? Integer.parseInt((String) SETTINGS.get("CreationItemFreq"))
                : DEFAULT_VALUE;
        carSoldFreq = SETTINGS.get("CarSoldFreq") != null
                ? Integer.parseInt((String) SETTINGS.get("CarSoldFreq"))
                : DEFAULT_VALUE;
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




