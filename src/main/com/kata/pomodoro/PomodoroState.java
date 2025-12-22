package com.kata.pomodoro;

public enum PomodoroState {
    STOPPED {
        @Override
        public PomodoroState start() {
            return STARTED;
        }
    }, STARTED {
        @Override
        public PomodoroState stop() {
            return STOPPED;
        }

        @Override
        public PomodoroState pause() {
            return PAUSED;
        }
    }, PAUSED {
        @Override
        public PomodoroState resume() {
            return STARTED;
        }

        @Override
        public PomodoroState stop() {
            return STOPPED;
        }
    };

    public PomodoroState start() {
        throw new IllegalStateException("Impossible depuis " + this);
    }

    public PomodoroState pause() {
        throw new IllegalStateException("Impossible depuis " + this);
    }

    public PomodoroState resume() {
        throw new IllegalStateException("Impossible depuis " + this);
    }

    public PomodoroState stop() {
        throw new IllegalStateException("Impossible depuis " + this);
    }
}
