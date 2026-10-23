package org.allaymc.api.world.data;

/**
 * {@code FireworkType} names the explosion shapes stored in firework metadata
 * and used by firework effects. Serialization uses enum ordinals, so
 * declaration order preserves the numeric identity of each shape and must
 * remain compatible with existing saved firework descriptors.
 *
 * @author daoge_cmd
 */
public enum FireworkType {
    /**
     * Small spherical explosion shape.
     */
    SMALL_SPHERE,
    /**
     * Large spherical explosion shape.
     */
    HUGE_SPHERE,
    /**
     * Star-shaped firework explosion pattern.
     */
    STAR,
    /**
     * Creeper-head firework explosion pattern.
     */
    CREEPER_HEAD,
    /**
     * Burst-shaped firework explosion pattern.
     */
    BURST
}
