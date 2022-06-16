package com.jcourse.agolovenko.lesson6.Dealers;

import com.jcourse.agolovenko.lesson6.Configurator;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.OrderManager.Worker;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.WarehouseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CarDealer {

    private ScheduledExecutorService executor;
    private CarWarehouse carWarehouse;
    private JLabel label = null;
    private final WarehouseController warehouseController;

    private final AtomicLong saledCarsAmont = new AtomicLong(0);


    public CarDealer(WarehouseController warehouseController, CarWarehouse carWarehouse) {
        this.carWarehouse = carWarehouse;
        this.warehouseController = warehouseController;
    }

    public CarDealer(WarehouseController warehouseController, CarWarehouse carWarehouse, JLabel label) {
        this(warehouseController, carWarehouse);
        this.label = label;
    }

    Logger logger = LoggerFactory.getLogger(Worker.class);

    private void sendCarRequest() {
        try {
            warehouseController.makeOrder();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    Runnable task = () -> {
        Car car = carWarehouse.getCarFromStore();
        if (car == null) {
            sendCarRequest();
        } else {
            saledCarsAmont.incrementAndGet();
            if (Configurator.getLogSale()) {
                logger.info("Car " + car.getSerialNumber() + " had been sold!\n");
            }
            label.setText(String.valueOf(saledCarsAmont));
        }
    };

    public void runThreadsWithDelay(long milliseconds) {
        if (executor != null) {
            executor.shutdown();
            if (!executor.isTerminated()) {
                try {
                    executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        executor = new ScheduledThreadPoolExecutor(Configurator.getCarDealers());
        for (int i = 0; i < Configurator.getCarDealers(); i++) {
            executor.scheduleWithFixedDelay(task, 0, milliseconds, TimeUnit.MILLISECONDS);
        }
    }
}
