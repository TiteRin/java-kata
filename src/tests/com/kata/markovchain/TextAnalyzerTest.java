package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerTest {

    @Test
    void analyze_returns_ListOfWordsFromText() {
        String text = "Hello World";
        TextAnalyzer textAnalyzer = new TextAnalyzer(text);
        Map<String, State> analyse = textAnalyzer.analyze();

        State stateHello = new State("hello");
        stateHello.addTransition(new State("world"), 1.0);

        State stateWorld = new State("world");

        assertEquals(2, analyse.size());
        assertTrue(analyse.containsKey("hello"));
        assertTrue(analyse.containsKey("world"));
    }
}