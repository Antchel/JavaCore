package com.jcourse.agolovenko.lesson5.HTTPServer;

import com.jcourse.agolovenko.lesson5.HTTPServer.HTTP.FileHttpGetRequest;
import com.jcourse.agolovenko.lesson5.HTTPServer.HTTP.HTTPResponse;
import com.jcourse.agolovenko.lesson5.HTTPServer.ResponseSender.HttpResponseSender;

import java.io.IOException;
import java.net.Socket;

public class ClientRequestProcessor implements Runnable {

    private final HttpResponseSender httpSender;
    private final Socket clientSocket;

    public ClientRequestProcessor(Socket clientSocket, String directoryPath) throws IOException {
        FileHttpGetRequest request;
        this.clientSocket = clientSocket;
        try {
            request = new FileHttpGetRequest(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HTTPResponse response = new HTTPResponse(request, clientSocket, directoryPath);
        response.buildResponse();
        httpSender = new HttpResponseSender(clientSocket, response.getResponse());
    }

    public void run() {
        httpSender.send();
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
