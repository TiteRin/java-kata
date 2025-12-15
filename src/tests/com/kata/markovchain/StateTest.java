package com.kata.markovchain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void convertTextToMap_returns_WordsAsMap() {
        String text = "Hello World, Hello all the world, hello everybody";
        Map<String, String[]> mapResult = new HashMap<>();

        mapResult.put("hello", new String[]{"world", "all", "everybody"});
        mapResult.put("world", new String[]{"hello", "hello"});
        mapResult.put("all", new String[]{"the"});
        mapResult.put("the", new String[]{"world"});

        Map<String, String[]> map = (new TextAnalyzer(text)).convertTextToMap();

        assertTrue(map.containsKey("hello"));
        assertArrayEquals(mapResult.get("hello"), map.get("hello"));
        assertTrue(map.containsKey("world"));
        assertArrayEquals(mapResult.get("world"), map.get("world"));
        assertTrue(map.containsKey("all"));
        assertArrayEquals(mapResult.get("all"), map.get("all"));
        assertTrue(map.containsKey("the"));
        assertArrayEquals(mapResult.get("the"), map.get("the"));
    }

//    @Test
//    void addTransition_should_recalulateProbability() {
//        State hello = new State("hello");
//        State world = new State("world");
//        State mona = new State("mona");
//
//        hello.addTransition(world);
//        assertEquals(1.0, hello.getTransitions().get(world));
//
//        hello.addTransition(mona);
//        assertEquals(0.5, hello.getTransitions().get(world));
//        assertEquals(0.5, hello.getTransitions().get(mona));
//    }
}