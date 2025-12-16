package com.kata.markovchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MarkovChain {

    private final TextAnalyzer textAnalyzer;
    private final int length;

    public MarkovChain(int length, String text) {
        this.length = length;
        this.textAnalyzer = new TextAnalyzer(text);
    }

    public String generateText() {

        Map<String, State> states = this.textAnalyzer.getStates();

        List<String> words = new ArrayList<>();

        State nextStep = states.entrySet().stream().findAny().get().getValue();

        do {
            String currentWord = nextStep.getName();
            State currentStep = states.get(currentWord);
            words.add(currentWord);

            if (currentStep.getTransitions().isEmpty()) break;

            double random = Math.random();
            double cumulativeProbability = 0.0;

            for(Map.Entry<State, Double> entry: currentStep.getTransitions().entrySet()) {
                cumulativeProbability += entry.getValue();
                if (random <= cumulativeProbability) {
                    nextStep = entry.getKey();
                }
            }

        } while (words.size() < this.length);

        return String.join(" ", words);
    }
}