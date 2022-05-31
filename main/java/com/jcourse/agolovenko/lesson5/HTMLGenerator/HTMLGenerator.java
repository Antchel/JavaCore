package com.jcourse.agolovenko.lesson5.HTMLGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class HTMLGenerator {
    @SuppressWarnings("all")
    private final String TEMPLATE_PATH = "C:\\JavaEdu\\anton-golovenko\\main\\resources\\HTMLTemplate.html";
    private final IDirectoryParser parser;
    public HTMLGenerator(IDirectoryParser parser) {
        this.parser = parser;
    }

    public void generateHTMLFile(String HTMLFilename) throws IOException {
        File tempDirectory = new File(parser.getRoot());
        if (!tempDirectory.isDirectory())
            throw new IOException("Wrong directory");

        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(HTMLFilename));
        BufferedInputStream template = new BufferedInputStream(new FileInputStream(TEMPLATE_PATH));
        byte[] buf = new byte[4096];
        int count;

        while ((count = template.read(buf)) >= 0) {
            writer.write(buf, 0, count);
        }
        template.close();
        writer.write(("<title id=\"title\">Index of" + parser.getRoot() + "</title>").getBytes());
        writer.write(("""
                </head>
                     <body>
                     <h1 id="header">
                """ + parser.getRoot() + """
                </h1>
                     
                     <table>
                       <thead>
                         <tr class="header" id="theader">
                           <th id="nameColumnHeader" tabindex="0" role="button">Name</th>
                           <th id="sizeColumnHeader" class="detailsColumn" tabindex="0" role="button">
                             Size
                           </th>
                           <th id="dateColumnHeader" class="detailsColumn" tabindex="0" role="button">
                             Date Modified
                           </th>
                         </tr>
                       </thead>
                       <tbody id="tbody">""").getBytes());
        parser.parseDirectoryData();
        Map<String, NodeInfo> dirs = parser.getDirectories();
        Map<String, NodeInfo> files = parser.getFiles();
        NumberFormat nf = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

        for (Map.Entry<String, NodeInfo> el : dirs.entrySet()){
            String fn = el.getKey();
            writer.write("<tr>".getBytes());
            NodeInfo node = el.getValue();
            writer.write(("<td data-value=\""+fn+"\"><a class=\"icon dir\" href=\""+node.filepath()+"\">"+fn+"/</a></td>").getBytes());
            writer.write(("<td class=\"detailsColumn\" data-value=\"0\"></td>").getBytes());
            writer.write(("<td class=\"detailsColumn\" data-value=\""+node.date()+"\">"+ node.date() +"</td>\n</tr>").getBytes());
        }
        for (Map.Entry<String, NodeInfo> el : files.entrySet()){
            String fn = el.getKey();
            writer.write("<tr>".getBytes());
            NodeInfo node = el.getValue();

            writer.write(("<td data-value=\""+fn+"\"><a class=\"icon file\" href=\""+node.filepath()+"\">"+fn+"/</a></td>").getBytes());
            writer.write(("<td class=\"detailsColumn\" data-value=\""+nf.format(node.size())+"\">"+nf.format(node.size())+ "Bytes" +"</td>").getBytes());
            writer.write(("<td class=\"detailsColumn\" data-value=\""+node.date()+"\">"+ node.date() +"</td>\n</tr>").getBytes());
        }
        writer.write("""        
                     </table>
                     </body>
                    </html>
                """.getBytes(StandardCharsets.UTF_8));
        writer.close();
    }
}
