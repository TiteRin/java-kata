package com.kata.markovchain;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {

    private final String text;
    private Map<String, State> states = new HashMap<>();

    public TextAnalyzer(String text) {
        this.text = text;
        this.states = new HashMap<>();
    }

    private String cleanText() {
        return this.text.replaceAll("\\W", " ")
                .replaceAll("\\W+", " ")
                .trim();
    }

    public Map<String, State> analyze() {

        String[] words = cleanText()
                .toLowerCase()
                .split(" ");

        for (int i = 0, wordsLength = words.length; i < wordsLength - 1; i++) {
            String word = words[i];
            String nextWord = words[i + 1];

            State state = this.states.getOrDefault(
                    word,
                    new State(word)
            );

            State nextState = this.states.getOrDefault(
                    nextWord,
                    new State(nextWord)
            );

            this.states.put(word, state);
            this.states.put(nextWord, nextState);
        }

        return this.states;
    }
}
