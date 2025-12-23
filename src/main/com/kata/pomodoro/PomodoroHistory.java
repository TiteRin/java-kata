package com.kata.pomodoro;

import java.util.ArrayList;
import java.util.List;

public class PomodoroHistory {
    private List<PomodoroSession> history;

    public PomodoroHistory(List<PomodoroSession> history) {
        this.history = List.copyOf(history);
    }

    public PomodoroHistory() {
        this.history = new ArrayList<>();
    }

    public PomodoroHistory add(PomodoroSession session) {
        List<PomodoroSession> newHistory = new ArrayList<>(history);
        newHistory.add(session);
        return new PomodoroHistory(newHistory);
    }

    public List<PomodoroSession> all() {
        return history;
    }

    public PomodoroSession first() {
        return history.getFirst();
    }

    public PomodoroSession last() {
        return history.getLast();
    }
}
