package com.jcourse.agolovenko.lesson6.OrderManager;

import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order extends Thread {
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue;
    private final JLabel label;

    public Order(CopyOnWriteArrayList<BuildCarTask> taskQueue, JLabel label) {
        this.taskQueue = taskQueue;
        this.label = label;
    }

    private void performTask(BuildCarTask t)
    {
        try {
            t.buildCar();
        }
        catch (InterruptedException ex) {
            t.interrupted();
        }
    }

    public void run()
    {
        BuildCarTask toExecute;
        while (true)
        {
            synchronized (taskQueue)
            {
                if (taskQueue.isEmpty())
                {
                    if (label != null)  label.setText("0");
                    try {
                        taskQueue.wait();
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }
                else
                {
                    toExecute = taskQueue.remove(0);
                    if (label != null) label.setText(String.valueOf(taskQueue.size()));
                }
            }
            performTask(toExecute);
        }
    }

}
