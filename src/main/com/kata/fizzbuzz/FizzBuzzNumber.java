package com.kata.fizzbuzz;

public class FizzBuzzNumber {

    private final int number;

    public FizzBuzzNumber(int number) {
        this.number = number;
    }

    private boolean isDivisibleBy(int divisor) {
        return this.number % divisor == 0;
    }

    public boolean isFizz() {
        return isDivisibleBy(3);
    }

    public boolean isBuzz() {
        return isDivisibleBy(5);
    }

    public boolean isFizzBuzz() {
        return isFizz() && isBuzz();
    }

    public String toString() {

        if (isFizzBuzz()) return "FizzBuzz";
        if (isFizz()) return "Fizz";
        if (isBuzz()) return "Buzz";

        return String.valueOf(this.number);
    }
}
