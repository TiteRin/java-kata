package com.kata.pomodoro;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SimplePomodoroTimer implements PomodoroTimer {

    private Pomodoro pomodoro;
    private final Consumer<Pomodoro> onTick;
    private final Runnable onFinish;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> future;

    public SimplePomodoroTimer(
            Pomodoro pomodoro,
            Consumer<Pomodoro> onTick,
            Runnable onFinish
    ) {
        this.pomodoro = pomodoro;
        this.onTick = onTick;
        this.onFinish = onFinish;
    }

    @Override
    public void start() {
        if (pomodoro.isFinished()) {
            stop();
            onFinish.run();
            return;
        }

        future = scheduler.scheduleAtFixedRate(this::tick, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (future != null) {
            future.cancel(true);
        }
        scheduler.shutdownNow();
    }

    @Override
    public void update(Pomodoro newPomodoro) {
        this.pomodoro = Objects.requireNonNull(newPomodoro);
        if (pomodoro.state() != PomodoroState.STARTED) {
            stop();
        }
    }

    private void tick() {
        if (pomodoro.isFinished()) {
            stop();
            onFinish.run();
            return;
        }

        if (onTick == null) {
            return;
        }

        onTick.accept(pomodoro);
    }
}
