package com.kata.pomodoro;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class MutableClock extends Clock {
    private Instant instant;
    private final ZoneId zone;

    MutableClock(Instant instant, ZoneId zone) {
        this.instant = instant;
        this.zone = zone;
    }

    void advance(Duration duration) {
        instant = instant.plus(duration);
    }


    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zoneId) {
        return new MutableClock(instant, zoneId);
    }

    @Override
    public Instant instant() {
        return instant;
    }
}
