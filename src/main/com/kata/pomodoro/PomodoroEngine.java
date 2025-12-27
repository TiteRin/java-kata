package com.kata.pomodoro;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PomodoroEngine {

    private PomodoroHistory history = new PomodoroHistory();
    private final Clock clock;
    private Pomodoro currentPomodoro;
    private PomodoroTimer timer;

    public PomodoroEngine() {
        this.clock = Clock.systemUTC();
    }

    public PomodoroEngine(Clock clock) {
        this.clock = Objects.requireNonNull(clock);
    }

    public void startNewSession(Duration duration) {
        this.currentPomodoro = Pomodoro.of(duration, clock).start();
        Instant startInstant = clock.instant();

        this.timer = new SimplePomodoroTimer(currentPomodoro, null, () -> {
            Instant endInstant = clock.instant();
            PomodoroSession session = new PomodoroSession(startInstant, endInstant, currentPomodoro);
            history = history.add(session);
            currentPomodoro = null;
            timer = null;
        });

        timer.start();
    }

    public void pause() {
        if (currentPomodoro == null) {
            throw new IllegalStateException("No active pomodoro session");
        }

        currentPomodoro = currentPomodoro.pause();
        timer.update(currentPomodoro);
    }

    public void resume() {
        if (currentPomodoro == null) {
            throw new IllegalStateException("No active pomodoro session");
        }

        currentPomodoro = currentPomodoro.resume();
        timer.update(currentPomodoro);
    }

    public void stop() {
        if (currentPomodoro == null) {
            return;
        }

        currentPomodoro = currentPomodoro.stop();
        timer.update(currentPomodoro);

        currentPomodoro = null;
        timer = null;
    }

    public List<PomodoroSession> history() {
        return history.all();
    }

    public Optional<PomodoroTimer> timer() {
        return Optional.ofNullable(timer);
    }

    public Optional<Pomodoro> currentPomodoro() {
        return Optional.ofNullable(currentPomodoro);
    }

    public PomodoroSession lastSession() {
        return history.last();
    }

    public PomodoroState state() {
        return currentPomodoro().map(Pomodoro::state).orElse(PomodoroState.STOPPED);
    }

    public Duration getRemaining() {
        return currentPomodoro().map(Pomodoro::remaining).orElse(Duration.ZERO);
    }

    public Duration getElapsed() {
        return currentPomodoro().map(Pomodoro::elapsed).orElse(Duration.ZERO);
    }
}
