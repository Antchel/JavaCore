package com.jcourse.agolovenko.lesson4;

public class WordCounter implements Comparable<WordCounter>{
    private final String word;
    private int counter = 0;

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
        int res = Integer.compare(o.counter, this.counter);
        if (res == 0) {
            return o.getWord().compareToIgnoreCase(this.getWord());
        }
        return res;
    }
}
