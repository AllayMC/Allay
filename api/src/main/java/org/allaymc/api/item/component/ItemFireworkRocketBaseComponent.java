package org.allaymc.api.item.component;

import org.allaymc.api.world.explosion.FireworkExplosion;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ItemFireworkRocketBaseComponent} exposes a rocket's base flight
 * duration and explosion definitions. Rocket launching converts the duration
 * into randomized game ticks and passes the configured explosions to the
 * entity, while item serialization stores flight duration as a byte.
 *
 * @author daoge_cmd
 */
public interface ItemFireworkRocketBaseComponent extends ItemBaseComponent {
    /**
     * Returns the base flight duration in whole seconds before randomization.
     */
    int getDuration();

    /**
     * Returns a newly randomized rocket lifetime in game ticks. The
     * calculation converts {@code getDuration()} from seconds at twenty ticks
     * per second and adds zero through eleven extra ticks.
     */
    default int getRandomizedDuration() {
        // Twenty ticks per second converts flight seconds; the extra ticks vary launch lifetimes.
        return getDuration() * 20 + ThreadLocalRandom.current().nextInt(12);
    }

    /**
     * Replaces the base flight duration used for subsequent rocket launches.
     * The server setter stores {@code duration} without validation, and
     * serialization narrows the stored integer to a signed byte.
     */
    void setDuration(int duration);

    /**
     * Returns the explosion definitions currently configured for this rocket.
     * The server returns its backing set directly, so mutations through that
     * set affect subsequent launching and serialization despite the view
     * annotation.
     */
    @UnmodifiableView
    Set<FireworkExplosion> getExplosions();

    /**
     * Replaces the rocket's configured explosion definitions with the supplied
     * set. The server retains {@code explosions} directly, and subsequent tag
     * loading adds entries to it; callers must provide mutable storage when
     * loading is required.
     */
    void setExplosions(Set<FireworkExplosion> explosions);
}
