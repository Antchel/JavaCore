package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarWarehouse {
    private final List<Car> cars;
    private final int storePool;

    public CarWarehouse(int storePool) {
        this.storePool = storePool;
        cars = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized Car getCarFromStore() {
        if (!cars.isEmpty()) {
            Car car = cars.remove(0);
            notify();
            return car;
        } else {
            return null;
        }
    }

    public synchronized void putCar(Car car) throws InterruptedException {
        if (cars.size() < storePool) {
            cars.add(car);
        }
        else
            wait();
    }

}
