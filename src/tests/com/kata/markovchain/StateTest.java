package com.kata.markovchain;

import com.kata.markovchain.builder.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void State_returns_ANewObject() {
        State state = new State("stateA");
        assertEquals("stateA", state.getName());
    }
}