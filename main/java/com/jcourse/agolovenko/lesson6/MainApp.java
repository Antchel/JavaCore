package com.jcourse.agolovenko.lesson6;

import com.jcourse.agolovenko.lesson6.Dealers.CarDealer;
import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.Store.Store;
import com.jcourse.agolovenko.lesson6.UI.Form.CarFactory;
import com.jcourse.agolovenko.lesson6.UI.Controllers.DealersSliderController;
import com.jcourse.agolovenko.lesson6.UI.Controllers.VendorsSliderController;
import com.jcourse.agolovenko.lesson6.Vendors.Vendor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainApp {

    public static void main(String[] args) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {

        JFrame frame = new JFrame("Fabrique");
        CarFactory carFactory = new CarFactory("TST");
        frame.setContentPane(carFactory.mainPanel);
        frame.setPreferredSize(new Dimension(1024, 720));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Init Item Warehouses
        Store<CarBody> carBodyStore = new Store<>(Configurator.getBodyWarehouseCapacity(), carFactory.CarBodyStore){};
        Store<Engine> engineStore = new Store<>(Configurator.getEngineWarehouseCapacity(),carFactory.EngineStore){};
        Store<Accessories> accessoriesStore = new Store<>(Configurator.getAccessoryWarehouseCapacity(), carFactory.AccessoriesStore){};
        // Init Car warehouse
        CarWarehouse carStore = new CarWarehouse(Configurator.getStorageAutoSize(), carFactory.CarsStore);
        // Init Item Producers
        Vendor<CarBody> carBodyVendor = new Vendor<>(CarBody.class, carBodyStore , 1, carFactory.TotalCarBodyProduced);
        Vendor<Engine> engineVendor = new Vendor<>(Engine.class, engineStore, 1, carFactory.TotalEnginesProduced);
        Vendor<Accessories> accessoriesVendor = new Vendor<>(Accessories.class, accessoriesStore,
                Configurator.getAccessorySuppliers(),carFactory.TotalAccessoriesProduced);
        // Init Car controller
        VendorsSliderController.assignListener(carFactory.EnginesProduceSpeed,engineVendor);
        VendorsSliderController.assignListener(carFactory.CarBodyProduceSpeed,carBodyVendor);
        VendorsSliderController.assignListener(carFactory.AccessoriesProduceSpeed,accessoriesVendor);

        WarehouseController warehouseController = new WarehouseController(accessoriesStore, carStore, engineStore, carBodyStore, carFactory.TasksInQueue);
        // Init Car dealers
        CarDealer carDealer = new CarDealer(warehouseController, carStore, carFactory.ProducedCars);

        DealersSliderController.assignListener(carFactory.CarsSellSpeed, carDealer);

        // Start Car Factory
        carBodyVendor.runWithRate(Configurator.getCreationItemFreq());
        engineVendor.runWithRate(Configurator.getCreationItemFreq());
        accessoriesVendor.runWithRate(Configurator.getCreationItemFreq());
        carDealer.runThreadsWithDelay( Configurator.getCarSoldFreq());

    }
}
