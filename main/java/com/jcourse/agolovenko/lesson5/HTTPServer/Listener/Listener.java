package com.jcourse.agolovenko.lesson5.HTTPServer.Listener;

import com.jcourse.agolovenko.lesson5.HTTPServer.ClientRequestProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Listener implements IListener {

    private final ExecutorService threadPool;
    private final int port;
    private final String directory;

    public Listener(ExecutorService executorService, int port, String directory) {
        this.threadPool = executorService;
        this.directory = directory;
        this.port = port;
    }

    @Override
    public void listen() {
        try (ServerSocket s = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = s.accept();
                threadPool.submit(new ClientRequestProcessor(clientSocket, directory));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
