package com.jcourse.agolovenko.lesson6.Worker;

public class BuildCarTask {
    private final ITaskController controller;
    private final Task task;

    public BuildCarTask(Task t, ITaskController l) {
        controller = l;
        task = t;
    }

    void interrupted() {
        controller.taskInterrupted(task);
    }

    void buildCar() throws InterruptedException {
        task.performWork();
    }
}

