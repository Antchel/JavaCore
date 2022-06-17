package com.jcourse.agolovenko.lesson6.OrderManager;

import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.Store.Store;

public class Worker implements Task {
    private final Store<Accessories> accessoriesStore;
    private final Store<CarBody> carBodyStore;
    private final Store<Engine> engineStore;
    private final CarWarehouse carStore;

    public Worker(Store<Accessories> accessoriesStore,
                  Store<CarBody> carBodyStore,
                  Store<Engine> engineStore,
                  CarWarehouse carStore) {
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
        this.carStore = carStore;
    }

    private synchronized Car buildCar() {
        CarBody carBody = this.carBodyStore.get();
        Engine engine = this.engineStore.get();
        Accessories accessories = this.accessoriesStore.get();
        return new Car(carBody, accessories, engine);
    }

    public void moveCarToWarehouse(Car car){
        try {
            if (car != null)
                carStore.putCar(car);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized void performWork() {
        moveCarToWarehouse(buildCar());
    }
}
