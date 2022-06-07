package com.jcourse.agolovenko.lesson5.HTTPServer;

import com.jcourse.agolovenko.lesson5.HTTPServer.HTTP.HTTPRequest;
import com.jcourse.agolovenko.lesson5.HTTPServer.HTTP.HTTPResponse;

import java.io.IOException;
import java.net.Socket;

public class ClientRequestProcessor implements Runnable {
    private final Socket clientSocket;
    private final String rootDirectoryPath;

    public ClientRequestProcessor(Socket clientSocket, String directoryPath) throws IOException {
        this.clientSocket = clientSocket;
        this.rootDirectoryPath = directoryPath;
    }

    public void run() {
        HTTPRequest request;
        try {
            request = new HTTPRequest(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HTTPResponse response = new HTTPResponse(request, clientSocket, rootDirectoryPath);

        response.buildResponse();
        response.send();
    }
}
