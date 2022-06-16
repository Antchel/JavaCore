package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store<T extends IStorageItem> {
    private final List<T> store;
    private JLabel label = null;

    private final int storeSize;

    public Store(int storeSize) {
        this.storeSize = storeSize;
        store = Collections.synchronizedList(new ArrayList<>());
    }
    public Store(int storeSize, JLabel label) {
        this(storeSize);
        this.label = label;
    }

    public synchronized void put(T item) {
        if (store.size() == storeSize) {
            label.setText(String.valueOf(store.size()));
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notify();
        store.add(item);
        label.setText(String.valueOf(store.size()));
    }

    public synchronized T get() {
        if (store.isEmpty()) {
            label.setText(String.valueOf(0));
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        } else {
            T item = store.remove(0);
            notify();
            label.setText(String.valueOf(store.size()));
            return item;
        }
    }

}
