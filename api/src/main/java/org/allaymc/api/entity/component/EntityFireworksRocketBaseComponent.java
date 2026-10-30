package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;

/**
 * {@code EntityFireworksRocketBaseComponent} exposes a rocket lifetime,
 * explosion definitions, and optional player attachment. Lifetime processing
 * compares elapsed ticks with the configured duration, while the physics
 * component uses attachment to follow a player instead of applying free-flight
 * acceleration.
 *
 * @author daoge_cmd
 */
public interface EntityFireworksRocketBaseComponent extends EntityBaseComponent {
    /**
     * Returns the configured rocket lifetime threshold measured in ticks. The
     * rocket expires after its elapsed count exceeds this value, so the
     * comparison does not trigger at equality.
     */
    int getExistenceTicks();

    /**
     * Stores the rocket lifetime threshold measured in ticks without resetting
     * elapsed time. The next tick compares the incremented elapsed count with
     * this threshold, and an already exceeded threshold expires immediately.
     */
    void setExistenceTicks(int ticks);

    /**
     * Returns the stored count of ticks elapsed for this rocket.
     */
    int getExistedTicks();

    /**
     * Replaces the stored elapsed lifetime without changing the configured
     * threshold. The next tick increments this count before testing
     * expiration, so callers can advance or postpone expiration through this
     * value.
     */
    void setExistedTicks(int ticks);

    /**
     * Returns the shared explosion definitions associated with this rocket.
     * Callers must treat the returned set as read-only; the server retains the
     * set supplied to {@code setExplosions} rather than copying its entries.
     */
    @UnmodifiableView
    Set<FireworkExplosion> getExplosions();

    /**
     * Retains the supplied explosion set and publishes updated rocket state.
     * Callers must supply a nonnull set and coordinate later changes
     * themselves, because lifetime processing and serialization read the same
     * set.
     */
    void setExplosions(Set<FireworkExplosion> explosions);

    /**
     * Returns the attached player, or {@code null} for a freely moving rocket.
     * Attachment supplies the client target and makes rocket physics follow
     * the player on each tick.
     */
    EntityPlayer getAttachedPlayer();

    /**
     * Stores the player that this rocket follows during subsequent physics
     * ticks. Passing {@code null} detaches the rocket and allows its
     * configured horizontal multiplier and upward acceleration to apply again.
     */
    void setAttachedPlayer(EntityPlayer player);

    /**
     * Returns whether this rocket currently retains a nonnull attached player.
     */
    default boolean isAttached() {
        return getAttachedPlayer() != null;
    }
}
