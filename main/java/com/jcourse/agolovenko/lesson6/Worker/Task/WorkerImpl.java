package com.jcourse.agolovenko.lesson6.Worker.Task;

import java.util.List;

public class WorkerImpl extends Thread {
    private final List<BuildCarTask> taskQueue;

    public WorkerImpl(String name, List<BuildCarTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    private void performTask(BuildCarTask t)
    {
        try {
            System.out.println(Thread.currentThread().getName());
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
                }
            }
            performTask(toExecute);
        }
    }

}
