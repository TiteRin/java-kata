package com.kata.pomodoro;

import java.util.function.Consumer;

public interface PomodoroTimer {
    void start();
    void stop();
    void update(Pomodoro newPomodoro);
}


//public class PomodoroTimer {
//
//    private final Pomodoro pomodoro;
//    private final Consumer<Pomodoro> onTick;
//    private final Runnable onFinish;
//
//    public PomodoroTimer(
//            Pomodoro pomodoro,
//            Consumer<Pomodoro> onTick,
//            Runnable onFinish
//    ) {
//        this.pomodoro = pomodoro;
//        this.onTick = onTick;
//        this.onFinish = onFinish;
//    }
//
//    public void start() {}
//
//    private void tick();
//
//    public void update(Pomodoro newPomodoro) {}
//
//    public void stop() {}
//}
