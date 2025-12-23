package com.kata.pomodoro;

import java.time.Duration;
import java.time.Instant;

public record PomodoroSession(
        Instant startTime,
        Instant endTime,
        Pomodoro pomodoro
) {

    public Duration elapsed() {
        return Duration.between(startTime, endTime);
    }

    public boolean isFinished() {
        return pomodoro.isFinished();
    }
}
