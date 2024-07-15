package org.allaymc.server.loottables.roll;

import org.allaymc.api.loottable.Rolls;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public class RangedRolls implements Rolls {
    protected int min;
    protected int max;
    public RangedRolls(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int getRolls() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
