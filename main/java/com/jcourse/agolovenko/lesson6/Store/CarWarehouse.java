package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.Car;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarWarehouse {
    private final List<Car> cars;
    private final int storePool;
    private JLabel label = null;

    public CarWarehouse(int storePool) {
        this.storePool = storePool;
        cars = Collections.synchronizedList(new ArrayList<>());
    }

    public CarWarehouse(int storePool, JLabel label) {
        this(storePool);
        this.label = label;
    }

    public synchronized Car getCarFromStore() {
        if (!cars.isEmpty()) {
            Car car = cars.remove(0);
            if (label != null) label.setText(String.valueOf(cars.size()));
            notify();
            return car;
        } else {
            if (label != null) label.setText(String.valueOf(0));
            return null;
        }
    }

    public synchronized void putCar(Car car) throws InterruptedException {
        if (cars.size() < storePool) {
            cars.add(car);
            if (label != null) label.setText(String.valueOf(cars.size()));
        } else
            wait();
    }

}
