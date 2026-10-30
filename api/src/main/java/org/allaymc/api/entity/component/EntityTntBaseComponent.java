package org.allaymc.api.entity.component;

/**
 * {@code EntityTntBaseComponent} exposes the remaining fuse of a primed TNT
 * entity. Tick processing decrements a positive fuse and attempts explosion
 * after the fuse becomes nonpositive, while state publication supplies the
 * countdown used by viewers.
 *
 * @author daoge_cmd
 */
public interface EntityTntBaseComponent extends EntityBaseComponent {
    /**
     * Returns the remaining fuse countdown measured in entity ticks.
     */
    int getFuseTime();

    /**
     * Replaces the remaining fuse countdown and publishes the new entity
     * state. A nonpositive value selects explosion processing on the next TNT
     * tick instead of another countdown decrement.
     */
    void setFuseTime(int fuseTime);
}
