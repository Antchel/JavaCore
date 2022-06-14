package com.jcourse.agolovenko.lesson6.Worker.Task;

import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.Store;

public class Worker implements Task {
    private final Store<Accessories> accessoriesStore;
    private final Store<CarBody> carBodyStore;
    private final Store<Engine> engineStore;
    private final Store<Car> carStore;

    private final Object monitor = new Object();

    public Worker(Store<Accessories> accessoriesStore,
                  Store<CarBody> carBodyStore,
                  Store<Engine> engineStore,
                  Store<Car> carStore) {
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
        this.carStore = carStore;
    }

    private synchronized Car buildCar() throws InterruptedException {
        return new Car(this.carBodyStore.get(this),
                this.accessoriesStore.get(this),
                this.engineStore.get(this)
        );

    }

    public synchronized void moveCarToWarehouse(){
        try {
            carStore.put(buildCar(), this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void performWork() throws InterruptedException {
        buildCar();
        moveCarToWarehouse();
    }
}
