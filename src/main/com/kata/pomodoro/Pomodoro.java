package com.kata.pomodoro;

public record Pomodoro(PomodoroState state) {

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
