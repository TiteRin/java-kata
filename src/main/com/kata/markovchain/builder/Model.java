package com.kata.markovchain.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Model {
    private final Map<String, State> states;

    public Model(Map<String, State> states) {
        this.states = states;
    }

    public State getState(String name) {
        return states.get(name);
    }

    public State getRandomState() {

        if (states.isEmpty()) return null;

        return (new ArrayList<>(states.values())).get(
                ThreadLocalRandom.current().nextInt(0, states.size())
        );
    }

    public Collection<State> getStates() {
        return states.values();
    }

    public boolean contains(String name) {
        return states.containsKey(name);
    }
}
