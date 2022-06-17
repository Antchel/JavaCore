package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.OrderManager.IValueChangedListener;

import javax.swing.*;
import java.util.*;

public class CarWarehouse {
    private final List<Car> cars;
    private final int storePool;
    private final Set<IValueChangedListener> productsCountListeners = Collections.synchronizedSet(new HashSet<>());


    public CarWarehouse(int storePool) {
        this.storePool = storePool;
        cars = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized Car getCarFromStore() {
        if (!cars.isEmpty()) {
            Car car = cars.remove(0);
            productsCountListeners.forEach(l -> l.valueChanged(cars.size()));
            notify();
            return car;
        } else {
            productsCountListeners.forEach(l -> l.valueChanged(cars.size()));
            return null;
        }
    }

    public synchronized void putCar(Car car) throws InterruptedException {
        if (cars.size() < storePool) {
            cars.add(car);
            productsCountListeners.forEach(l -> l.valueChanged(cars.size()));
        } else
            wait();
    }

    public void addListener(IValueChangedListener listener) {
        productsCountListeners.add(listener);
    }

}
