package org.allaymc.api.world.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code Weather} defines weather categories and the random durations used by world weather
 * timers. Each category stores inclusive tick bounds, and sampling returns a
 * uniformly selected duration within them; rain and thunder timers are managed
 * independently by the world implementation.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Weather {
    /**
     * Clear interval lasting ten through one hundred fifty minutes at twenty ticks per
     * second.
     */
    CLEAR(20 * 60 * 10, 20 * 60 * 150),
    /**
     * Rain interval lasting three through thirteen minutes at twenty ticks per
     * second.
     */
    RAIN(20 * 60 * 3, 20 * 60 * 13),
    /**
     * Thunder interval lasting three through thirteen minutes at twenty ticks
     * per second.
     */
    THUNDER(20 * 60 * 3, 20 * 60 * 13);

    // The minTime and maxTime values bound one sampled interval inclusively in
    // world ticks. World weather transitions reset their own timers using these
    // category-specific ranges.
    /**
     * Inclusive minimum duration in ticks.
     */
    private final int minTime;
    /**
     * Inclusive maximum duration in ticks.
     */
    private final int maxTime;

    /**
     * Returns a random duration in world ticks between this category's
     * inclusive bounds. Sampling uses the current thread's random generator and
     * does not change an existing world timer itself.
     */
    public int generateRandomTimeLength() {
        // Adding one makes the stored upper duration inclusive during random
        // selection.
        return ThreadLocalRandom.current().nextInt(minTime, maxTime + 1);
    }
}