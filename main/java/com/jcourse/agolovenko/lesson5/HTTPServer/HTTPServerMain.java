package com.jcourse.agolovenko.lesson5.HTTPServer;

import com.jcourse.agolovenko.lesson5.HTTPServer.Listener.IListener;
import com.jcourse.agolovenko.lesson5.HTTPServer.Listener.Listener;
import picocli.CommandLine;

import java.util.concurrent.Executors;

public class HTTPServerMain {
    @CommandLine.Option(names = {"-p", "--port"}, description = "Server port value")
    private static final int PORT = 8085;
    @CommandLine.Option(names = {"-d", "--dir"}, description = "Source directory path")
    private static final String DIRECTORY = "C:\\Work";
    @CommandLine.Option(names = {"-t", "--threads"}, description = "Threads in thread pool")
    private static final int THREADS_AMOUNT = 5;

    public static void main(String[] args) {
        IListener listener = new Listener(Executors.newFixedThreadPool(THREADS_AMOUNT), PORT, DIRECTORY);
        listener.listen();
    }
    // TODO: 07.06.2022 Describe API in Swagger 
}
