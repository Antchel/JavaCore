package com.jcourse.agolovenko.lesson4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSorter {

    Logger logger = LoggerFactory.getLogger(DataSorter.class);

    public List<WordCounter> sortWords(Map<String, WordCounter> wordsCollection) {
        long start = System.nanoTime();
        List<WordCounter> wordCounters = new ArrayList<>(wordsCollection.size());
        for (
                Map.Entry<String, WordCounter> el : wordsCollection.entrySet()) {
            wordCounters.add(el.getValue());
        }
        wordCounters.sort(WordCounter::compareTo);
        long end = System.nanoTime();

        logger.debug("Sorting duration is " + (double) (end-start)/1_000_000 + " Milliseconds");

        return wordCounters;
    }

}
