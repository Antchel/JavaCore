package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store<T extends IStorageItem> {
    private final List<T> store;
    private final int storeSize;

    public Store(int storeSize) {
        this.storeSize = storeSize;
        store = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void put(T item) {
        if (store.size() == storeSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        store.add(item);
        System.out.println(this.getClass().getGenericSuperclass() + " Store has " + store.size() + " items!");
    }

    public synchronized T get() throws InterruptedException {
        if (store.isEmpty()) {
            wait();
            return null;
        } else {
            T item = store.remove(0);
            notify();
            return item;
        }
    }
}
