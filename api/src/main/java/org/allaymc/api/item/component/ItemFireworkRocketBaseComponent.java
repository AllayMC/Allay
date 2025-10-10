package org.allaymc.api.item.component;

import org.allaymc.api.world.FireworkExplosion;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public interface ItemFireworkRocketBaseComponent extends ItemBaseComponent {
    /**
     * Retrieves the duration of the firework in seconds.
     *
     * @return The duration of the firework in seconds. Returns an integer value representing the time
     * the firework will last when launched
     */
    int getFireworkDuration();

    /**
     * Calculates and retrieves a randomized duration for the firework in ticks.
     * The duration is derived from the firework's base duration (in seconds) multiplied by 20 (to convert to ticks),
     * with an additional random value between 0 (inclusive) and 12 (exclusive) added for a variance effect.
     *
     * @return The randomized firework duration in ticks as an integer value.
     */
    default int getRandomizedFireworkDuration() {
        return getFireworkDuration() * 20 + ThreadLocalRandom.current().nextInt(12);
    }

    /**
     * Sets the duration for the firework rocket. The duration determines the time in seconds for which
     * the firework will travel before exploding.
     *
     * @param duration the duration of the firework in seconds. It must be a non-negative integer.
     */
    void setFireworkDuration(int duration);

    /**
     * Retrieves the set of firework explosions associated with the firework rocket.
     *
     * @return a {@code Set} of {@code FireworkExplosion} objects representing the explosions configured for the firework rocket
     */
    @UnmodifiableView
    Set<FireworkExplosion> getFireworkExplosions();

    /**
     * Sets the firework explosions associated with this item.
     *
     * @param fireworkExplosions a set of {@link FireworkExplosion} that specifies the various explosions to be used for this
     *                           firework rocket. Passing an empty set will result in no explosions
     */
    void setFireworkExplosions(Set<FireworkExplosion> fireworkExplosions);
}
