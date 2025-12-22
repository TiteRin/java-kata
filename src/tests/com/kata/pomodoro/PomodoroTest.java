package com.kata.pomodoro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PomodoroTest {

    @Test
    @DisplayName("Should start Pomodoro")
    void shouldStartPomodoro() {

        Pomodoro pomodoro = Pomodoro.create();

        Pomodoro started = pomodoro.start();

        assertThat(started.getState())
                .as("Le Pomodoro doit être dans l’état STARTED après start")
                .isEqualTo(PomodoroState.STARTED);
    }
}
