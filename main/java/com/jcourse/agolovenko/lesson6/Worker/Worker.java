package com.jcourse.agolovenko.lesson6.Worker;

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
    private Car car = null;
    private  CarBody carBody = null;
    private Engine engine = null;
    private Accessories accessories = null;

    public Worker(Store<Accessories> accessoriesStore,
                  Store<CarBody> carBodyStore,
                  Store<Engine> engineStore,
                  CarWarehouse carStore) {
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
        this.carStore = carStore;
    }

    private void buildCar() throws InterruptedException {
        carBody = this.carBodyStore.get();
        engine = this.engineStore.get();
        accessories = this.accessoriesStore.get();
        car = new Car(carBody,accessories,engine);
    }

    public void moveCarToWarehouse(){
        try {
            carStore.putCar(car);
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
