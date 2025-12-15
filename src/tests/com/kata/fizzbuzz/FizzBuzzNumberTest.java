package com.kata.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzNumberTest {
    @Test
    void toString_returns_numberAsString() {
        FizzBuzzNumber number = new FizzBuzzNumber(17);
        assertEquals("17", number.toString());
    }

    @Test
    void toString_returns_FizzBuzzIfNumberIsDivisibleBy3And5() {
        FizzBuzzNumber number = new FizzBuzzNumber(15);
        assertEquals("FizzBuzz", number.toString());
    }

    @Test
    void toString_returns_FizzIfNumberIsDivisibleBy3() {
        FizzBuzzNumber number = new FizzBuzzNumber(9);
        assertEquals("Fizz", number.toString());
    }

    @Test
    void toString_returns_BuzzIfNumberIsDivisibleBy5() {
        FizzBuzzNumber number = new FizzBuzzNumber(10);
        assertEquals("Buzz", number.toString());
    }

    @Test
    void isFizz_returns_trueIfNumberIsDivisibleBy3() {
        FizzBuzzNumber number = new FizzBuzzNumber(12);
        assertTrue(number.isFizz());
    }

    @Test
    void isFizz_returns_falseIfNumberIsNotDivisibleBy3() {
        FizzBuzzNumber number = new FizzBuzzNumber(13);
        assertFalse(number.isFizz());
    }

    @Test
    void isBuzz_returns_trueIfNumberIsDivisibleBy5() {
        FizzBuzzNumber number = new FizzBuzzNumber(15);
        assertTrue(number.isBuzz());
    }

    @Test
    void isBuzz_returns_falseIfNumberIsNotDivisibleBy5() {
        FizzBuzzNumber number = new FizzBuzzNumber(16);
        assertFalse(number.isBuzz());
    }

    @Test
    void isFizzBuzz_returns_trueIfNumberIsDivisibleBy3And5() {
        FizzBuzzNumber number = new FizzBuzzNumber(30);
        assertTrue(number.isFizzBuzz());
    }

    @Test
    void isFizzBuzz_returns_falseIfNumberIsNotDivisibleBy3And5() {
        FizzBuzzNumber number = new FizzBuzzNumber(31);
        assertFalse(number.isFizzBuzz());
    }
}