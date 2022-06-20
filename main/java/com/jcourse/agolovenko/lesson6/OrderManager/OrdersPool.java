package com.jcourse.agolovenko.lesson6.OrderManager;

import com.jcourse.agolovenko.lesson6.Configurator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrdersPool implements ITaskController {

    private static final int WORKERS_COUNT = Configurator.getWorkers();
    private final Set<IValueChangedListener> listeners = new HashSet<>();
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue = new CopyOnWriteArrayList<>(new LinkedList<>());

    @Override
    public void taskInterrupted(Task t) {
        Thread.currentThread().interrupt();
    }

    public void assignTaskToWorker(Task t) {
        assignTaskToWorker(t, this);
    }

    public void assignTaskToWorker(Task t, ITaskController l) {
        synchronized (taskQueue) {
            taskQueue.add(new BuildCarTask(t, l));
            notifyListeners();
            taskQueue.notify();
        }
    }

    public OrdersPool() {
        for (int i = 0; i < WORKERS_COUNT; i++) {
            Order order = new Order(taskQueue);
            order.addListeners(listeners);
            Thread workerThread = new Thread(order);
            workerThread.start();
        }
    }

    private void notifyListeners() {
        listeners.forEach( l -> l.valueChanged(taskQueue.size()));
    }
    public void addListener(IValueChangedListener listener) {
        listeners.add(listener);
    }
}
