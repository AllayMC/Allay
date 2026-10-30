package org.allaymc.api.entity.component;

/**
 * {@code EntityPickableBaseComponent} exposes the delay that controls whether
 * an entity can be collected. The server tick decrements positive delays, and
 * {@code canBePicked} accepts exactly zero; a negative delay therefore remains
 * unavailable until a caller changes it.
 *
 * @author IWareQ
 */
public interface EntityPickableBaseComponent extends EntityBaseComponent {
    /**
     * Returns the remaining pickup delay measured in entity ticks.
     */
    int getPickupDelay();

    /**
     * Sets the pickup delay measured in entity ticks. Positive values count
     * down during server ticks, zero permits collection immediately, and
     * negative values remain unchanged by that countdown and prevent
     * collection.
     */
    void setPickupDelay(int delay);

    /**
     * Returns whether the pickup delay is exactly zero for this entity.
     * Negative delays remain unavailable, rather than behaving like elapsed
     * positive delays, until another operation changes their value.
     */
    default boolean canBePicked() {
        return getPickupDelay() == 0;
    }
}
