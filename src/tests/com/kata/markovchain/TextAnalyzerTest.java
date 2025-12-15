package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerTest {

    @Test
    void getStates_returns_ListOfWordsFromText() {
        String text = "Hello World";
        TextAnalyzer textAnalyzer = new TextAnalyzer(text);
        Map<String, State> analyse = textAnalyzer.getStates();

        assertEquals(1, analyse.size());
        assertTrue(analyse.containsKey("hello"));
        assertFalse(analyse.containsKey("world"));
    }

    @Test
    void getStates_returns_StatesWithTransitions() {
        String text = "Hello World";
        TextAnalyzer textAnalyzer = new TextAnalyzer(text);
        Map<String, State> analyse = textAnalyzer.getStates();

        State stateHello = new State("hello");
        stateHello.addTransition(new State("world"), 1.0);

        assertEquals(analyse.get("hello"), stateHello);
    }

    @Test
    void getStates_returns_StatesWithTransitionsAndProbabilities() {
        String text = "les hommes libres peuvent rester libres ou bien vendre leur liberté";
        TextAnalyzer textAnalyzer = new TextAnalyzer(text);
        Map<String, State> analyse = textAnalyzer.getStates();

        State stateLes = new State("les");
        stateLes.addTransition(new State("hommes"), 1.0);

        State stateLibres = new State("libres");
        stateLibres.addTransition(new State("peuvent"), 0.5);
        stateLibres.addTransition(new State("ou"), 0.5);

        assertEquals(analyse.get("les"), stateLes);
        assertEquals(analyse.get("libres"), stateLibres);
    }
}