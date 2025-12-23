package com.kata.pomodoro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat; // Ajoute cette ligne
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Pomodoro Timer")
public class PomodoroTest {

    @Nested
    @DisplayName("When new")
    class WhenNew {
        @Test
        @DisplayName("should be in STOPPED state")
        void shouldStartPomodoro() {

            Pomodoro pomodoro = Pomodoro.create();

            assertThat(pomodoro.state()).as("Le Pomodoro doit être dans l’état STOPPED après création").isEqualTo(PomodoroState.STOPPED);
        }
    }

    @Nested
    @DisplayName("When started")
    class WhenStarted {
        @Test
        @DisplayName("can’t be started again")
        void shouldNotStartWhenAlreadyStarted() {
            Pomodoro started = Pomodoro.create().start();
            assertThatThrownBy(started::start).as("Démarrer un Pomodoro déjà démarré n’est pas autorisé").isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("can be stopped")
        void shouldStopAfterStarted() {
            Pomodoro started = Pomodoro.create().start();

            assertThat(started.stop().state()).as("Après stop(), Pomodoro doit être dans l’état STOPPED").isEqualTo(PomodoroState.STOPPED);
        }

        @Test
        @DisplayName("can be paused")
        void shouldPauseAfterStarted() {
            Pomodoro started = Pomodoro.create().start();

            assertThat(started.pause().state()).as("Après pause(), Pomodoro doit être dans l’état PAUSED").isEqualTo(PomodoroState.PAUSED);
        }
    }

    @Nested
    @DisplayName("When paused")
    class WhenPaused {
        @Test
        @DisplayName("can be resumed")
        void shouldResumeAfterPaused() {
            Pomodoro paused = Pomodoro.create().start().pause();

            assertThat(paused.resume().state()).as("Après resume(), Pomodoro doit revenir à l’état STARTED").isEqualTo(PomodoroState.STARTED);
        }

        @Test
        @DisplayName("can be stopped")
        void shouldStopAfterPaused() {
            Pomodoro paused = Pomodoro.create().start().pause();

            assertThat(paused.stop().state()).as("Après resume(), Pomodoro peut revenir à l’état STOPPED").isEqualTo(PomodoroState.STOPPED);
        }
    }

    @Nested
    @DisplayName("State transitions")
    class Transitions {
        @Test
        @DisplayName("Should not pause after stopped")
        void shouldNotPauseAfterStopped() {
            Pomodoro stopped = Pomodoro.create();

            assertThatThrownBy(stopped::pause).as("Pomodoro ne peut pas être mis en pause dans l’état STOPPED").isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("Should stop after any state")
        void shouldStopAfterAnyState() {
            Pomodoro started = Pomodoro.create().start();
            Pomodoro paused = started.pause();

            assertAll(
                    () -> assertThat(started.stop().state()).isEqualTo(PomodoroState.STOPPED),
                    () -> assertThat(paused.stop().state()).isEqualTo(PomodoroState.STOPPED)
            );
        }
    }


    @Nested
    @DisplayName("Time tracking")
    class TimeTracking {

        @Test
        @DisplayName("Should keep the remaining time after paused")
        void shouldKeepTheRemainingTimeAfterPaused() {
            Clock fixed = Clock.fixed(Instant.parse("2025-12-22T10:00:00Z"), ZoneOffset.UTC);
            Pomodoro p = Pomodoro.of(Duration.ofMinutes(25), fixed).start();

            Clock later = Clock.offset(fixed, Duration.ofMinutes(5));

            Pomodoro pLater = p.withClock(later);

            assertThat(pLater.elapsed()).isEqualTo(Duration.ofMinutes(5));
            assertThat(pLater.remaining()).isEqualTo(Duration.ofMinutes(20));
        }
    }
}
