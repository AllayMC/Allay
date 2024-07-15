package org.allaymc.server.loottables.roll;

import org.allaymc.api.loottable.Rolls;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
public class SimpleRolls implements Rolls {
    protected int rolls = 1;
    public SimpleRolls(int rolls) {
        this.rolls = rolls;
    }

    @Override
    public int getRolls() {
        return rolls;
    }
}
