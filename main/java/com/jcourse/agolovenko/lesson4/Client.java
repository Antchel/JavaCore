package com.jcourse.agolovenko.lesson4;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        CSVWriter csv = new CSVWriter();
        DataSorter dataSorter = new DataSorter();

        try {
            Reader data = new InputStreamReader(new BufferedInputStream(new FileInputStream("./main/resources/text.txt")), "CP1251");
            dataProcessor.collectData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        csv.writeDataToFileInWordFreqFormat(dataSorter.sortWords(dataProcessor.getWordsCollection()),
                dataProcessor.getTotalWordsAmount(), "./out/logs/test.csv");

    }
}
