package com.kata.markovchain.builder;

import java.util.HashMap;
import java.util.Map;

public class State {

    private final String name;
    private final Map<String, Integer> occurrences = new HashMap<>();
    private Map<String, Double> probabilities;

    public State(String name) {
        this.name = name;
    }

    public void incrementTransition(String nextState) {
        occurrences.merge(nextState, 1, Integer::sum);
    }

    public void normalizeTransitions() {
        int total = occurrences.values().stream().mapToInt(Integer::intValue).sum();
        probabilities = new HashMap<>();

        occurrences.forEach((state, count) -> probabilities.put(state, count / (double) total));
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getProbabilities() {
        return probabilities;
    }
}
