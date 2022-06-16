package com.jcourse.agolovenko.lesson6.OrderManager;

import com.jcourse.agolovenko.lesson6.Configurator;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrdersPool implements ITaskController {

    private static final int WORKERS_COUNT = Configurator.getWorkers();
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue = new CopyOnWriteArrayList<>(new LinkedList<>());
    private final JLabel label;
    private Set<? super Thread> availableThreads = new HashSet<>();

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
            if (label != null) label.setText(String.valueOf(taskQueue.size()));
            taskQueue.notify();
        }
    }

    public OrdersPool(JLabel label) {
        this.label = label;
        for (int i = 0; i < WORKERS_COUNT; i++) {
            availableThreads.add(new Order(taskQueue, label));
        }
        for (Object availableThread : availableThreads) {
            ((Thread) availableThread).start();
        }

    }
}
