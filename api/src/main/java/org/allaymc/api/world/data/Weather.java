package org.allaymc.api.world.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the weather in the world.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Weather {
    CLEAR(20 * 60 * 10, 20 * 60 * 150),
    RAIN(20 * 60 * 3, 20 * 60 * 13),
    THUNDER(20 * 60 * 3, 20 * 60 * 13);

    private final int minTime;
    private final int maxTime;

    /**
     * Generate a random time length for this weather.
     *
     * @return the random time length for this weather
     */
    public int generateRandomTimeLength() {
        return ThreadLocalRandom.current().nextInt(minTime, maxTime + 1);
    }
}