package com.kata.pomodoro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat; // Ajoute cette ligne
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PomodoroTest {

    @Test
    @DisplayName("Should be created STOPPED")
    void shouldStartPomodoro() {

        Pomodoro pomodoro = Pomodoro.create();

        assertThat(pomodoro.state())
                .as("Le Pomodoro doit être dans l’état STOPPED après création")
                .isEqualTo(PomodoroState.STOPPED);
    }

    @Test
    @DisplayName("Should not start when already started")
    void shouldNotStartWhenAlreadyStarted() {
        Pomodoro started = Pomodoro.create().start();
        assertThatThrownBy(started::start)
            .as("Démarrer un Pomodoro déjà démarré n’est pas autorisé")
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Should stop after started")
    void shouldStopAfterStarted() {
        Pomodoro started = Pomodoro.create().start();

        assertThat(started.stop().state())
                .as("Après stop(), Pomodoro doit être dans l’état STOPPED")
                .isEqualTo(PomodoroState.STOPPED);
    }

    @Test
    @DisplayName("Should pause after started")
    void shouldPauseAfterStarted() {
        Pomodoro started = Pomodoro.create().start();

        assertThat(started.pause().state())
                .as("Après pause(), Pomodoro doit être dans l’état PAUSED")
                .isEqualTo(PomodoroState.PAUSED);
    }

    @Test
    @DisplayName("Should resume after paused")
    void shouldResumeAfterPaused() {
        Pomodoro paused = Pomodoro.create().start().pause();

        assertThat(paused.resume().state())
                .as("Après resume(), Pomodoro doit revenir à l’état STARTED")
                .isEqualTo(PomodoroState.STARTED);
    }

    @Test
    @DisplayName("Should not pause after stopped")
    void shouldNotPauseAfterStopped() {
        Pomodoro stopped = Pomodoro.create();

        assertThatThrownBy(stopped::pause)
                .as("Pomodoro ne peut pas être mis en pause dans l’état STOPPED")
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Should stop after any state")
    void shouldStopAfterAnyState() {
        Pomodoro started = Pomodoro.create().start();
        Pomodoro paused = started.pause();
        Pomodoro resumed = paused.resume();

        assertThat(started.stop().state()).isEqualTo(PomodoroState.STOPPED);
        assertThat(paused.stop().state()).isEqualTo(PomodoroState.STOPPED);
        assertThat(resumed.stop().state()).isEqualTo(PomodoroState.STOPPED);
    }
}
