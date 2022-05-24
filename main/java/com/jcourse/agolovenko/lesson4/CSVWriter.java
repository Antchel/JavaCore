package com.jcourse.agolovenko.lesson4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    Logger logger = LoggerFactory.getLogger(CSVWriter.class);
    private final char DELIMITER = ';';

    public void writeDataToFileInWordFreqFormat(List<WordCounter> listOfWords, int totalWordsCounter, String filename){
        StringBuilder sb = new StringBuilder();
        makeTitleInWordFreqFormat(sb);
        makeRowsInWordFreqFormat(sb, listOfWords, totalWordsCounter);
        try (FileOutputStream writer = new FileOutputStream(filename)) {
            writer.write(sb.toString().getBytes(System.getProperty("file.encoding")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void makeRowsInWordFreqFormat(StringBuilder sb, List<WordCounter> wordCounters, int totalWordsCounter) {
        for (WordCounter tr : wordCounters) {
            sb.append(tr.getWord());
            sb.append(DELIMITER);
            sb.append(tr.getCounter());
            sb.append(DELIMITER);
            sb.append(1.0 * tr.getCounter() / totalWordsCounter * 100);
            sb.append('\n');
            logger.debug(tr.getWord() + " repeats " + tr.getCounter() + " times! It's " + (1.0*tr.getCounter() / totalWordsCounter * 100) + "% ratio!");
        }
    }

    private void makeTitleInWordFreqFormat(StringBuilder sb){
        sb.append("Word");
        sb.append(DELIMITER);
        sb.append("Used times");
        sb.append(DELIMITER);
        sb.append("Freq in %");
        sb.append('\n');
    }
}
