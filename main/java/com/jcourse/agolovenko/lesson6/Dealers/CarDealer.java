package com.jcourse.agolovenko.lesson6.Dealers;

import com.jcourse.agolovenko.lesson6.Configurator;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.WarehouseController;
import com.jcourse.agolovenko.lesson6.Worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CarDealer implements Runnable {

    private final ScheduledExecutorService executor;
    private final long delay;
    private CarWarehouse carWarehouse;
    private final WarehouseController warehouseController;
    private final AtomicLong saledCarsAmont = new AtomicLong(0);
    private final Object monitor = new Object();


    public CarDealer(WarehouseController warehouseController, CarWarehouse carWarehouse, long delay) {
        executor = Executors.newScheduledThreadPool(Configurator.getCarDealers());
        this.delay = delay;
        this.carWarehouse = carWarehouse;
        this.warehouseController = warehouseController;
    }

    Logger logger = LoggerFactory.getLogger(Worker.class);

    private void sendCarRequest() {
        try {
            System.out.println("sendCarRequest");
            warehouseController.makeOrder();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void soldCar() {
        if (Configurator.getLogSale()) {
            logger.info(Thread.currentThread() + "Car has been sold!");
        }
    }

    Runnable task = () -> {
//        System.out.println("Car- Seller Scheduling: " + LocalTime.now());
        Car car = carWarehouse.getCarFromStore();
        if (car == null) {
            sendCarRequest();
        }
        else {
            saledCarsAmont.incrementAndGet();
            System.out.println("saledCarsAmont = " + saledCarsAmont);
        }
    };

    @Override
    public void run() {
        for (int i = 0; i < Configurator.getCarDealers(); i++) {
            executor.scheduleWithFixedDelay(task, 0, delay, TimeUnit.MILLISECONDS);
        }
    }
}
