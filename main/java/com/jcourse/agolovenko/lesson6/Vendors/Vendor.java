package com.jcourse.agolovenko.lesson6.Vendors;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;
import com.jcourse.agolovenko.lesson6.Store.Store;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.concurrent.*;


public class Vendor<T extends IStorageItem> {

    private Class<? extends IStorageItem> clazz;
    private final ScheduledExecutorService executor;
    private final long delay;
    private final int poolSize;
    private Store<T> warehouse;

    public Vendor(Class<? extends IStorageItem> clazz, Store<T> warehouse, long milliseconds, int poolSize) {
        this.clazz = clazz;
        this.delay = milliseconds;
        this.warehouse = warehouse;
        this.poolSize = poolSize;
        executor = new ScheduledThreadPoolExecutor(poolSize);
    }

    private T createItem() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        return (T) (clazz.getDeclaredConstructors()[0]).newInstance();
    }

    Runnable task = () -> {
//        System.out.println(clazz.getSimpleName() + " Product Vendor Scheduling: " + LocalTime.now());
//        System.out.println("Thread: " + Thread.currentThread());
        try {
            warehouse.put(this.createItem());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    };

    public void runWithRate() {
        for (int i = 0; i < poolSize; i++) {
            executor.scheduleWithFixedDelay(task, 0, this.delay, TimeUnit.MILLISECONDS);
        }
    }
    public void runWithRate(long delay) {
        for (int i = 0; i < poolSize; i++) {
            executor.scheduleWithFixedDelay(task, 0, delay, TimeUnit.MILLISECONDS);
        }
    }
}
