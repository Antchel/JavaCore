package com.jcourse.agolovenko.lesson5.HTTPServer.ResponseSender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpResponseSender implements Sendable{

    private final BufferedWriter outputData;
    private final String message;

    public HttpResponseSender(Socket socket, String message) throws IOException {
        outputData = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.message = message;
    }

    @Override
    public void send() {

        try {
            outputData.write(message);
            outputData.flush();
            outputData.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
