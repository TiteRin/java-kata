package com.kata.pomodoro;

import java.util.Objects;

public class Pomodoro {

    private final PomodoroState state;

    private Pomodoro(PomodoroState state) {
        this.state = Objects.requireNonNull(state);
    }

    public static Pomodoro create() {
        return new Pomodoro(PomodoroState.STOPPED);
    }

    public PomodoroState getState() {
        return this.state;
    }

    public Pomodoro start() {
        return new Pomodoro(PomodoroState.STARTED);
    }

    public Pomodoro stop() {
        return new Pomodoro(PomodoroState.STOPPED);
    }

    public Pomodoro pause() {
        return new Pomodoro(PomodoroState.PAUSED);
    }
}
