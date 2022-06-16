package com.jcourse.agolovenko.lesson6.Vendors;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;
import com.jcourse.agolovenko.lesson6.Store.Store;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class Vendor<T extends IStorageItem> {

    private final Class<? extends IStorageItem> clazz;
    private ScheduledExecutorService executor;
    private final int poolSize;
    JLabel label = null;


    private final AtomicLong producedItems = new AtomicLong(0);
    private Store<T> warehouse;

    public Vendor(Class<? extends IStorageItem> clazz, Store<T> warehouse, int poolSize) {
        this.clazz = clazz;
        this.warehouse = warehouse;
        this.poolSize = poolSize;
    }

    public Vendor(Class<? extends IStorageItem> clazz, Store<T> warehouse, int poolSize, JLabel label) {
        this(clazz, warehouse, poolSize);
        this.label = label;
    }

    private T createItem() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        producedItems.incrementAndGet();
        if (label != null)
            label.setText(String.valueOf(producedItems));
        return (T) (clazz.getDeclaredConstructors()[0]).newInstance();
    }

    public AtomicLong getProducedItems() {
        return producedItems;
    }

    Runnable task = () -> {
        try {
            warehouse.put(this.createItem());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    };

    public void runWithRate(long milliseconds) {
        if (executor != null) {
            executor.shutdown();
            if (!executor.isTerminated()) {
                try {
                    executor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        executor = new ScheduledThreadPoolExecutor(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executor.scheduleWithFixedDelay(task, 0, milliseconds, TimeUnit.MILLISECONDS);
        }
    }
}
