package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {


    @Test
    void equals_returns_trueIfStatesHaveSameName() {
        State state1 = new State("hello");
        State state2 = new State("hello");
        assertEquals(state2, state1);
    }

    @Test
    void equals_returns_falseIfStatesHaveDifferentName() {
        State state1 = new State("hello");
        State state2 = new State("world");
        assertNotEquals(state2, state1);
    }

    @Test
    void equals_returns_falseIfStatesAreNull() {
        State state1 = new State("hello");
        assertNotEquals(null, state1);
    }

    @Test
    void equals_returns_falseIfStatesAreDifferentClasses() {
        State state1 = new State("hello");
        assertNotEquals("hello", state1);
    }
}