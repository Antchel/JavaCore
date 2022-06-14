package com.jcourse.agolovenko.lesson6.Worker;

import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.Car;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.Store;
import com.jcourse.agolovenko.lesson6.Worker.Task.ThreadPool;
import com.jcourse.agolovenko.lesson6.Worker.Task.Worker;

public class WarehouseController {
    private final Store<Car> carWarehouse;
    private final Store<Accessories> accessoriesStore;
    private final Store<CarBody> carBodyStore;
    private final Store<Engine> engineStore;
    public WarehouseController(Store<Accessories> accessoriesStore,
                               Store<Car> carWarehouse,
                               Store<Engine> engineStore,
                               Store<CarBody> carBodyStore) {

        this.carWarehouse = carWarehouse;
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
    }

    public void makeOrder() throws InterruptedException {

        ThreadPool threadPool = new ThreadPool();
        threadPool.addTask(new Worker(accessoriesStore,carBodyStore,engineStore,carWarehouse));
    }
}
