package com.jcourse.agolovenko.lesson6;

import com.jcourse.agolovenko.lesson6.Details.Accessories;
import com.jcourse.agolovenko.lesson6.Details.CarBody;
import com.jcourse.agolovenko.lesson6.Details.Engine;
import com.jcourse.agolovenko.lesson6.Store.CarWarehouse;
import com.jcourse.agolovenko.lesson6.Store.Store;
import com.jcourse.agolovenko.lesson6.OrderManager.OrdersPool;
import com.jcourse.agolovenko.lesson6.OrderManager.Worker;

import javax.swing.*;

public class WarehouseController {
    private final CarWarehouse carWarehouse;
    private final Store<Accessories> accessoriesStore;
    private final Store<CarBody> carBodyStore;
    private final Store<Engine> engineStore;
    private final OrdersPool threadPool;

    public WarehouseController(Store<Accessories> accessoriesStore,
                               CarWarehouse carWarehouse,
                               Store<Engine> engineStore,
                               Store<CarBody> carBodyStore,
                               JLabel label) {
        this.carWarehouse = carWarehouse;
        this.accessoriesStore = accessoriesStore;
        this.carBodyStore = carBodyStore;
        this.engineStore = engineStore;
        threadPool = new OrdersPool(label);
    }

    public void makeOrder() throws InterruptedException {
        threadPool.assignTaskToWorker(new Worker(accessoriesStore, carBodyStore, engineStore, carWarehouse));
    }
}
