package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;
import com.jcourse.agolovenko.lesson6.OrderManager.IValueChangedListener;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store<T extends IStorageItem> {
    private final List<T> store;
    private final int storeSize;
    private final List<Object> vendorMonitors = Collections.synchronizedList(new ArrayList<>());
    private final Set<IValueChangedListener> productsCountListeners = Collections.synchronizedSet(new HashSet<>());

    public Store(int storeSize) {
        this.storeSize = storeSize;
        store = new CopyOnWriteArrayList<>();
    }

    public void put(T item, Object monitor) {
        if (store.size() >= storeSize) {
            productsCountListeners.forEach(l -> l.valueChanged(store.size()));
            try {
                vendorMonitors.add(monitor);
                synchronized (monitor) {
                    monitor.wait();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        store.add(item);
        synchronized (this) {
            notify();
        }

        productsCountListeners.forEach(l -> l.valueChanged(store.size()));
    }

    public synchronized T get() {
        if (store.isEmpty()) {
            productsCountListeners.forEach(l -> l.valueChanged(store.size()));
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return get();
        } else {
            T item = store.remove(0);
            if (!vendorMonitors.isEmpty()) {
                synchronized (vendorMonitors.get(0)) {
                    vendorMonitors.remove(0).notifyAll();
                }
            }
            productsCountListeners.forEach(l -> l.valueChanged(store.size()));
            return item;
        }
    }

    public void addListener(IValueChangedListener listener) {
        productsCountListeners.add(listener);
    }
}
