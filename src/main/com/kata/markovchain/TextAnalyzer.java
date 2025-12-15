package com.kata.markovchain;

import java.util.*;

public class TextAnalyzer {

    private final String text;
    private final Map<String, State> states;

    public TextAnalyzer(String text) {
        this.text = text;
        this.states = new HashMap<>();
    }

    private String cleanText() {
        return this.text.replaceAll("\\W", " ")
                .replaceAll("\\W+", " ")
                .trim();
    }

    private Map<String, String[]> convertTextToMap() {
        String[] words = cleanText()
                .toLowerCase()
                .split(" ");

        Map<String, String[]> map = new HashMap<>();

        for (int i = 0, wordsLength = words.length; i < wordsLength - 1; i++) {
            String word = words[i];
            String nextWord = words[i + 1];

            List<String> array = new ArrayList<>(Arrays.asList(map.getOrDefault(word, new String[]{})));

            array.add(nextWord);

            map.put(word, array.toArray(new String[0]));
        }

        return map;
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

            State nextState = new State(nextWord);

            state.addTransition(nextState, 1.0);

            this.states.put(word, state);
        }

        return this.states;
    }
}
