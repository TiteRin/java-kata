package com.kata.pomodoro;

public record Pomodoro(PomodoroState state) {

    public static Pomodoro create() {
        return new Pomodoro(PomodoroState.STOPPED);
    }

    public Pomodoro start() {
        return new Pomodoro(this.state.start());
    }

    public Pomodoro stop() {
        return new Pomodoro(this.state.stop());
    }

    public Pomodoro pause() {
        return new Pomodoro(this.state.pause());
    }

    public Pomodoro resume() {
        return new Pomodoro(this.state.resume());
    }
}
