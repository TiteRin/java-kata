package com.kata.markovchain;

import com.kata.markovchain.builder.Builder;
import com.kata.markovchain.builder.Model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    @Test
    void build_returns_AModelWith3States() {
        Model model = Builder.build(List.of("a", "b", "c"));
        assertEquals(3, model.getStates().size());
    }

    @Test
    void build_returns_AModelWith2States() {
        Model model = Builder.build(List.of("a", "b", "b", "a"));
        assertEquals(2, model.getStates().size());
    }

    @Test
    void build_returns_AStateHasProbabilities() {
        Model model = Builder.build(List.of("a", "b", "b", "a"));
        assertEquals(2, model.getState("b").getProbabilities().size());
    }

    @Test
    void build_returns_AStateHasNoProbability() {
        Model model = Builder.build(List.of("a", "b", "c"));
        assertEquals(0, model.getState("c").getProbabilities().size());
    }

    @Test
    void build_returns_AStateWithAProbabilityOf1() {
        Model model = Builder.build(List.of("a", "b", "b", "a"));
        assertEquals(1, model.getState("a").getProbabilities().get("b"));
    }

    @Test
    void build_returns_AStateWithAProbabilityOf05() {
        Model model = Builder.build(List.of("a", "b", "b", "a"));
        assertEquals(0.5, model.getState("b").getProbabilities().get("b"));
    }
}