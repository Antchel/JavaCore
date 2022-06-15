package com.jcourse.agolovenko.lesson6.Worker;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WorkerImpl extends Thread {
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue;

    public WorkerImpl(String name, CopyOnWriteArrayList<BuildCarTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
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
                    try {
                        taskQueue.wait();
                    }
                    catch (InterruptedException ex) {
                        System.err.println("Thread was inetrrupted:"+getName());
                    }
                    continue;
                }
                else
                {
                    toExecute = taskQueue.remove(0);

                    System.out.println("taskQueue size is " + taskQueue.size());
                }
            }
            performTask(toExecute);
        }
    }

}
