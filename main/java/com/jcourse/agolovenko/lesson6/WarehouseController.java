package com.jcourse.agolovenko.lesson6;

import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.Store.Store;
import com.jcourse.agolovenko.lesson6.Worker.ThreadPool;
import com.jcourse.agolovenko.lesson6.Worker.Worker;

public class WarehouseController {
    private final CarWarehouse carWarehouse;
    private final Store<Accessories> accessoriesStore;
    private final Store<CarBody> carBodyStore;
    private final Store<Engine> engineStore;
    private final ThreadPool threadPool = new ThreadPool();
    public WarehouseController(Store<Accessories> accessoriesStore,
                               CarWarehouse carWarehouse,
                               Store<Engine> engineStore,
                               Store<CarBody> carBodyStore) {

        this.carWarehouse = carWarehouse;
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
    }

    public void makeOrder() throws InterruptedException {
        threadPool.addTask(new Worker(accessoriesStore,carBodyStore,engineStore,carWarehouse));
    }
}
