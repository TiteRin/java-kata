package com.kata.markovchain;

import java.util.*;

public class TextAnalyzer {

    private final String text;
    private final Map<String, State> states;

    public TextAnalyzer(String text) {
        this.text = text;
        this.states = new HashMap<>();

        this.analyze();
    }

    private String cleanText() {
        return this.text.toLowerCase();

    }

    private Map<String, String[]> convertTextToMap() {
        String[] words = cleanText().split("[^\\p{L}\\p{N}]+");

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

    private void analyze() {
        Map<String, String[]> words = convertTextToMap();

        words.forEach((word, nextWords) -> {
            State state = new State(word);
            List<String> array = Arrays.asList(nextWords);


            int totalWords = nextWords.length;
            for (String nextWord : nextWords) {
                if (state.hasTransition(nextWord)) {
                    continue;
                }
                double totalAppearanceInNextWords = Collections.frequency(array, nextWord);
                state.addTransition(new State(nextWord), totalAppearanceInNextWords / totalWords);
            }

            states.put(word, state);
        });
    }

    public Map<String, State> getStates() {
        return states;
    }
}
