package com.jcourse.agolovenko.lesson6.OrderManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order implements Runnable {
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue;
//    private final JLabel label;
    private Set<IValueChangedListener> listeners = new HashSet<>();

    public Order(CopyOnWriteArrayList<BuildCarTask> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void addListener(IValueChangedListener listener){
        listeners.add(listener);
    }

    private void performTask(BuildCarTask t) {
        try {
            t.buildCar();
        } catch (InterruptedException ex) {
            t.interrupted();
        }
    }

    private void notifyListeners(){
        listeners.forEach(l -> l.valueChanged(taskQueue.size()));
    }

    @Override
    public void run() {
        BuildCarTask toExecute;
        while (true) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                } else {
                    toExecute = taskQueue.remove(0);
                }
                notifyListeners();
            }
            performTask(toExecute);
        }
    }

}
