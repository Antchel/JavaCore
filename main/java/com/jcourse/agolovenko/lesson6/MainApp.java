package com.jcourse.agolovenko.lesson6;

import com.jcourse.agolovenko.lesson6.Dealers.CarDealer;
import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.Store;
import com.jcourse.agolovenko.lesson6.Vendors.Vendor;
import com.jcourse.agolovenko.lesson6.Worker.WarehouseController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainApp {

    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        // Init Item Warehouses
        Store<CarBody> carBodyStore = new Store<>(Configurator.getBodyWarehouseCapacity());
        Store<Engine> engineStore = new Store<>(Configurator.getEngineWarehouseCapacity());
        Store<Accessories> accessoriesStore = new Store<>(Configurator.getAccessoryWarehouseCapacity());
        // Init Car warehouse
        Store<Car> carStore = new Store<>(Configurator.getStorageAutoSize());
        // Init Item Producers
        Vendor<CarBody> carBodyVendor = new Vendor<>(CarBody.class, carBodyStore, Configurator.getCreationItemFreq(), 1);
        Vendor<Engine> engineVendor = new Vendor<>(Engine.class, engineStore, Configurator.getCreationItemFreq(), 1);
        Vendor<Accessories> accessoriesVendor = new Vendor<>(Accessories.class, accessoriesStore, Configurator.getCreationItemFreq(), Configurator.getAccessorySuppliers());
        // Init Car controller
        WarehouseController warehouseController = new WarehouseController(accessoriesStore, carStore, engineStore, carBodyStore);
        // Init Car dealers
        CarDealer carDealer = new CarDealer(warehouseController, carStore, Configurator.getCarSoldFreq());

        // Start Car Factory
        carBodyVendor.run();
        engineVendor.run();
        accessoriesVendor.run();
        carDealer.run();

    }
}
