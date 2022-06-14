package com.jcourse.agolovenko.lesson6.Store;

import com.jcourse.agolovenko.lesson6.Details.IStorageItem;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

public class Store<T extends IStorageItem> {
    private final Map<String, T> store;
    private final int storeSize;
    private final BlockingQueue<Object> storeWaitersQueue = new SynchronousQueue<>();
    private final BlockingQueue<Object> workersWaitersQueue = new SynchronousQueue<>();

    public Store(int storeSize) {
        this.storeSize = storeSize;
        store = new ConcurrentHashMap<>(storeSize);
    }

    public synchronized void put(T item, Object whoToNotify) {
        if (!storeWaitersQueue.isEmpty()) storeWaitersQueue.remove().notify();
        if (store.size() == storeSize) {
            try {
                storeWaitersQueue.put(whoToNotify);
                whoToNotify.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        store.put(item.getSerialNumber(), item);
    }

    public synchronized T get(Object whoToNotify) throws InterruptedException {
        if (store.isEmpty()) {
            try {
                workersWaitersQueue.put(whoToNotify);
                wait();
                return get(whoToNotify);
            } catch (InterruptedException ex) {
                return null;
            }
        }
        else {
            return store.remove(store.entrySet().iterator().next().getKey());
        }
    }

}
