package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarkovChainTest {

    @Test
    void generateText_returns_string() {
        MarkovChain markovChain = new MarkovChain(10, "Hello World");
        assertEquals(10, markovChain.generateText().split(" ").length);
    }
}