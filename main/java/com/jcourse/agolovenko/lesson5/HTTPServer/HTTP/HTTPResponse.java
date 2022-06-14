package com.jcourse.agolovenko.lesson5.HTTPServer.HTTP;


import com.jcourse.agolovenko.lesson5.HTTPServer.HTML.HTMLPageBuilder;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

public class HTTPResponse {
    private final StringBuilder response = new StringBuilder();
    private final FileHttpGetRequest request;
    private final Socket clientSocket;
    private final String rootDirectoryPath;
    private final StringWriter writer = new StringWriter();

    public HTTPResponse(FileHttpGetRequest request, Socket clientSocket, String rootDirectoryPath) {
        this.request = request;
        this.rootDirectoryPath = rootDirectoryPath;
        this.clientSocket = clientSocket;
    }

    // TODO: 06.06.2022 Implement buildResponse via Builder pattern. Create newBuilder.uri. etc.

    public void buildResponse() {
        String path = request.getTargetPath();
        String appType = "";


        Path folder = Paths.get(path);
        if (!Files.exists(folder)) {
            response.append("HTTP/1.1 404 Not Found\r\n");
            return;
        }

        if (path.isEmpty() || !Files.isDirectory(folder))
            path = rootDirectoryPath;
        if (Files.isDirectory(Path.of(request.getTargetPath()))) {
            try {
                HTMLPageBuilder.HTMLPageBuild(path, "./anton-golovenko/main/resources/ServerTemplate.vm", "model", writer);
            } catch (IllegalAccessException e) {
                sendErrorNotify("Could not get access to template file");
            }
        } else {
            appType = URLConnection.getFileNameMap().getContentTypeFor(request.getTargetPath());
        }

        switch (request.getHTTPMethod()) {
            case "GET" -> {
                response.append("HTTP/1.1 200 OK\r\n");
                if (appType != null && appType.isEmpty()) {
                    addResponseHeaders();
                    response.append(writer);

                } else if (appType == null || appType.equals("null")) {
                    response.append("Content-Type: application/octet-stream\r\n");
                } else {
                    String filePath = request.getTargetPath();
                    response.append("Content-Type: ").append(appType).append("\r\n");
                    response.append("Last-Modified: ").append(new Date(new File(filePath).lastModified())).append("\r\n");
                    response.append("Content-Disposition: attachment; filename=").append(Path.of(request.getTargetPath()).getFileName()).append("\r\n\r\n");
                    try {
                        StringBuilder data = new StringBuilder();
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(request.getTargetPath()));
                        byte[] buf = new byte[4096];
                        int count;
                        while ((count = in.read(buf)) >= 0) {
                            data.append(Arrays.toString(buf), 0, count);
                        }
                        response.append(data);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
            case "POST", "HEAD" -> {
                response.append("HTTP/1.1 200 OK\r\n");
                addResponseHeaders();
            }
            default -> {
                response.append("HTTP/1.0 501 Not Implemented\r\n");
                response.append("Allow: GET, POST, HEAD\r\n");
                addResponseHeaders();
            }
        }

    }

    public final String getResponse() {
        return response.toString();
    }
    // TODO: 07.06.2022 Create additional class for headers generatind with methods : SetContentType, etc. 
    private void addResponseHeaders() {
        response.append("Content-Type: text/html; charset=utf-8\r\n");
        response.append("Cache-Control: no-cache\r\n");
        response.append("Date: ").append(new Date()).append("\r\n");
        response.append("Server: AppliedTech\r\n");
        response.append("Host: ").append(clientSocket.getLocalSocketAddress()).append("\r\n");
        response.append("\r\n");
    }

    public void sendErrorNotify(String message) {
        try {
            clientSocket.getOutputStream().write(message.getBytes());
            clientSocket.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
