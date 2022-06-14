package com.jcourse.agolovenko.lesson6.Vendors;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;
import com.jcourse.agolovenko.lesson6.Store.Store;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.concurrent.*;


public class Vendor<T extends IStorageItem> implements Runnable {

    private final Class<? extends IStorageItem> clazz;
    private final ScheduledExecutorService executor;
    private final long delay;
    private final int poolSize;
    private Store<T> warehouse;
    private final Object monitor = new Object();

    public Vendor(Class<? extends IStorageItem> clazz, Store<T> warehouse, long milliseconds, int poolSize) {
        this.clazz = clazz;
        this.delay = milliseconds;
        this.warehouse = warehouse;
        this.poolSize = poolSize;
        executor = Executors.newScheduledThreadPool(poolSize);
    }

    private T createItem() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        return (T) (clazz.getDeclaredConstructors()[0]).newInstance();
    }

    Runnable task = () -> {
        System.out.println("Scheduling: " + LocalTime.now());
        System.out.println("Thread: " + Thread.currentThread());
        try {
            warehouse.put(this.createItem(), monitor);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    };

    @Override
    public void run() {
        for (int i = 0; i < poolSize; i++) {
            executor.scheduleWithFixedDelay(task, 0, delay, TimeUnit.MILLISECONDS);
        }
    }
}
