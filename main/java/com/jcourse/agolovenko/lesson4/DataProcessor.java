package com.jcourse.agolovenko.lesson4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    private final Map<String, WordCounter> wordsCollection = new HashMap<>();
    private int totalWordsCounter = 0;
    Logger logger = LoggerFactory.getLogger(DataProcessor.class);

    public int getTotalWordsAmount() {
        return totalWordsCounter;
    }

    public Map<String, WordCounter> getWordsCollection() {
        return wordsCollection;
    }

    public void collectData(Reader inputData) throws IOException {
        int letter;
        StringBuilder word = new StringBuilder();
        long start = System.nanoTime();
        while ((letter = inputData.read()) != -1) {
            appendOrCollect(word, (char) letter);
        }
        appendOrCollect(word, (char) letter);
        long end = System.nanoTime();
        logger.debug("Collecting duration is " + (double)(end - start)/ 1_000_000 + " Milliseconds");
    }

    private void appendOrCollect(StringBuilder word, char letter) {
        if (Character.isLetterOrDigit(letter)) {
            word.append(letter);
        } else if (word.length() > 0) {
            totalWordsCounter++;
            if (wordsCollection.containsKey(word.toString().toLowerCase())) {
                wordsCollection.get(word.toString().toLowerCase()).increment();
            } else {
                wordsCollection.put(word.toString().toLowerCase(), new WordCounter(word.toString().toLowerCase()));
            }
            word.setLength(0);
        }
    }
}