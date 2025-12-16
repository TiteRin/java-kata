package com.kata.markovchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class MarkovChain {

    private final TextAnalyzer textAnalyzer;
    private final int length;

    public MarkovChain(int length, String text) {
        this.length = length;
        this.textAnalyzer = new TextAnalyzer(text);
    }

    public String generateText() {

        Map<String, State> machine = this.textAnalyzer.getStates();
        List<State> allStates = new ArrayList<>(machine.values());

        List<String> words = new ArrayList<>();

        State nextStep = allStates.get(
                ThreadLocalRandom.current().nextInt(0, allStates.size())
        );

        do {
            String currentWord = nextStep.getName();
            State currentStep = machine.get(currentWord);
            words.add(currentWord);

            if (currentStep.getTransitions().isEmpty()) break;

            double random = Math.random();
            double cumulativeProbability = 0.0;

            for (Map.Entry<State, Double> entry : currentStep.getTransitions().entrySet()) {
                cumulativeProbability += entry.getValue();
                if (random <= cumulativeProbability) {
                    nextStep = entry.getKey();
                    break;
                }
            }

        } while (words.size() < this.length);

        return String.join(" ", words);
    }
}