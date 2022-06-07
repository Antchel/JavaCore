package com.jcourse.agolovenko.lesson5.HTTPServer.HTTP;

import java.io.*;
import java.util.*;

public class HTTPRequest {

    private final InputStream request;
    private String HTTPMethod;
    private String targetPath;
    private final StringBuilder requestHeaders = new StringBuilder();

    public StringBuilder getRequestHeaders() {
        return requestHeaders;
    }

    public String getTargetPath() {
        return targetPath;
    }


    public HTTPRequest(InputStream request) throws IOException {
        this.request = request;
        parse();
    }

    private void parse() throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(request));

        String line = inputStream.readLine();
        if (line != null && !line.isEmpty()) {
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            HTTPMethod = tokenizer.nextToken();
            targetPath = tokenizer.nextToken().substring(1).replace("%20", " ");
        }
        else {
            HTTPMethod = "unknown";
        }
        while (line != null && !line.isBlank()) {
            requestHeaders.append(line);
            line = inputStream.readLine();
        }
    }

    public String getHTTPMethod() {
        return HTTPMethod;
    }
}
