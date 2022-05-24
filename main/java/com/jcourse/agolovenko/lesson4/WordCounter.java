package com.jcourse.agolovenko.lesson4;

public class WordCounter implements Comparable<WordCounter>{
    private final String word;
    private int counter = 1;

    public WordCounter(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getCounter() {
        return counter;
    }

    public void increment() {
        counter++;
    }

    @Override
    public int compareTo(WordCounter o) {
        return Integer.compare(o.counter, this.counter);
    }
}
