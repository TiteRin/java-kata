package com.kata.markovchain;

class MarkovChain {

    private final TextAnalyzer textAnalyzer;
    private final int length;

    public MarkovChain(int length, String text) {
        this.length = length;
        this.textAnalyzer = new TextAnalyzer(text);
    }

    public String generateText() {
        String[] result = new String[this.length];
        return String.join(" ", result);
    }
}