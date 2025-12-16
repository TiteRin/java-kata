package com.kata.markovchain.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class Chain implements Iterator {

    private final Model model;
    private final Random random;
    private State currentState;

    public Chain(Model model, State start, Random random) {
        this.model = model;
        this.currentState = start;
        this.random = random;
    }

    public Chain(Model model) {

        this.model = model;
        this.currentState = this.model.getRandomState();
        this.random = new Random();
    }


    @Override
    public boolean hasNext() {
        return currentState != null && !currentState.getProbabilities().isEmpty();
    }

    @Override
    public String next() {
        if (currentState == null) {
            return null;
        }
        String word = currentState.getName();
        currentState = selectNextState();
        return word;
    }

    private State selectNextState() {
        double r = random.nextDouble();
        double cumulative = 0.0;

        if (currentState.getProbabilities().isEmpty()) return null;

        for (var entry : currentState.getProbabilities().entrySet()) {
            cumulative += entry.getValue();
            if (r <= cumulative) return model.getState(entry.getKey());
        }

        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        Iterator.super.forEachRemaining(action);
    }
}
