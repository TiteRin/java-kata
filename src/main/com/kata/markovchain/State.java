package com.kata.markovchain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public boolean hasTransition(String stateName) {
        return transitions.containsKey(new State(stateName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof State otherState)) return false;

        if (!otherState.name.equals(this.name)) return false;

        return otherState.transitions.equals(this.transitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
