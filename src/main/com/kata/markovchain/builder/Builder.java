package com.kata.markovchain.builder;

import java.util.*;

public class Builder {

    public static Model build(List<String> tokens) {
        Map<String, State> states = new HashMap<>();

        for (int i = 0; i < tokens.size() - 1; i++) {
            String current = tokens.get(i);
            String next = tokens.get(i + 1);

            states
                    .computeIfAbsent(current, State::new)
                    .incrementTransition(next);
            states.computeIfAbsent(next, State::new);
        }

        states.values().forEach(State::normalizeTransitions);
        return new Model(states);
    }
}
