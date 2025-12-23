package com.kata.pomodoro;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public record Pomodoro(PomodoroState state, Duration totalDuration, Instant startedAt, Instant pausedAt, Clock clock) {

    public static Pomodoro of(Duration duration) {
        return new Pomodoro(PomodoroState.STOPPED, duration, null, null, Clock.systemUTC());
    }

    public static Pomodoro of(Duration duration, Clock clock) {
        return new Pomodoro(PomodoroState.STOPPED, duration, null, null, clock);
    }


    public static Pomodoro create() {
        return new Pomodoro(PomodoroState.STOPPED, Duration.ofMinutes(25), null, null, Clock.systemUTC());
    }


    public Pomodoro start() {
        return new Pomodoro(
                state.start(),
                totalDuration,
                Instant.now(clock),
                null,
                clock
        );
    }

    public Pomodoro stop() {
        return new Pomodoro(
                state.stop(),
                totalDuration,
                null,
                null,
                clock
        );
    }


    public Pomodoro pause() {
        return new Pomodoro(
                state.pause(),
                totalDuration,
                startedAt,
                Instant.now(clock),
                clock
        );
    }

    public Pomodoro resume() {

        Duration elapsed = Duration.between(pausedAt, Instant.now(clock));
        Instant newStartedAt = startedAt.plus(elapsed);

        return new Pomodoro(
                state.resume(),
                totalDuration,
                newStartedAt,
                null,
                clock
        );
    }

    public Pomodoro withClock(Clock withClock) {
        return new Pomodoro(state, totalDuration, startedAt, pausedAt, withClock);
    }

    public Duration elapsed() {

        if (state == PomodoroState.STOPPED) {
            return Duration.ZERO;
        }

        if (startedAt == null) {
            return Duration.ZERO;
        }

        Instant endPoint = (state == PomodoroState.PAUSED) ? pausedAt : Instant.now(clock);
        return Duration.between(startedAt, endPoint);
    }

    public Duration remaining() {
        return totalDuration.minus(elapsed());
    }

    public boolean isFinished() {
        return remaining().isZero() || remaining().isNegative();
    }
}
