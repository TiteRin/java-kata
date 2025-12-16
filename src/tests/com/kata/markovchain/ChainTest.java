package com.kata.markovchain;

import com.kata.markovchain.builder.Builder;
import com.kata.markovchain.builder.Chain;
import com.kata.markovchain.builder.Model;
import com.kata.markovchain.builder.State;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void hasNext_returns_TrueIfThereAreMoreElements() {
        Model model = Builder.build(Arrays.asList("a", "b", "c"));
        Chain chain = new Chain(model);
        assertTrue(chain.hasNext());
    }

    @Test
    void hasNext_returns_FalseIfThereAreNoMoreElements() {
        Model model = Builder.build(Arrays.asList("a", "b", "c"));
        Chain chain = new Chain(model);
        chain.next();
        chain.next();
        chain.next();
        assertFalse(chain.hasNext());
    }

    @Test
    void next_returns_NextElement() {
        Model model = Builder.build(Arrays.asList("a", "b", "c"));
        Chain chain = new Chain(model, model.getState("a"), new Random());

        System.out.println(model.getStates());

        assertEquals("a", chain.next());
        assertEquals("b", chain.next());
        assertEquals("c", chain.next());
    }
}