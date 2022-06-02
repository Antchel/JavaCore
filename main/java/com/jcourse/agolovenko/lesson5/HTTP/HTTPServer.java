package com.jcourse.agolovenko.lesson5.HTTP;

import org.apache.struts.action.RequestProcessor;
import picocli.CommandLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    @CommandLine.Option(names = {"-p", "--port"}, description = "Server port value")
    private static int port = 1 << 16;
    @CommandLine.Option(names = {"-d", "--dir"}, description = "Source directory path")
    private static String directory = "C:\\";

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port)) {
            while(true) {
                Socket clientSocket = server.accept();
                Thread client = new Thread(new ClientRequestProcessor());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
