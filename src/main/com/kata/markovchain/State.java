package com.kata.markovchain;

import java.util.HashMap;
import java.util.Map;

public class State {
    private final String name;
    private final Map<State, Double> transitions = new HashMap<>();

    public State(String name) {
        this.name = name;
    }

    public void addTransition(State state, double probability) {
        transitions.put(state, probability);
    }

    public String getName() {
        return name;
    }

    public Map<State, Double> getTransitions() {
        return transitions;
    }
}
