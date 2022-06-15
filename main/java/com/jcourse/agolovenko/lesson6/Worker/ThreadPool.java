package com.jcourse.agolovenko.lesson6.Worker;

import com.jcourse.agolovenko.lesson6.Configurator;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPool implements ITaskController {

    private static final int WORKERS_COUNT = Configurator.getWorkers();
    private final CopyOnWriteArrayList<BuildCarTask> taskQueue = new CopyOnWriteArrayList<>(new LinkedList<>());
//    private final List<BuildCarTask> taskQueue = new LinkedList<>();
    private Set<? super Thread> availableThreads = new HashSet<>();

    @Override
    public void taskInterrupted(Task t) {
        // TODO: 14.06.2022 Implement correct threads interrupting
    }

    public void addTask(Task t) {
        addTask(t, this);
    }

    public void addTask(Task t, ITaskController l) {
        synchronized (taskQueue) {
            taskQueue.add(new BuildCarTask(t, l));
            taskQueue.notify();
        }
    }

    public ThreadPool() {
        for (int i = 0; i < WORKERS_COUNT; i++) {
            availableThreads.add(new WorkerImpl("Worker_" + i, taskQueue));
        }
        for (Object availableThread : availableThreads) {
            ((Thread) availableThread).start();
        }

    }
}
