package org.allaymc.api.world.sound;

/**
 * {@code BucketEmptySound} describes a bucket-emptying sound for the selected
 * contents. The runtime maps {@code type} to water, lava, powder-snow, or fish
 * events, while the caller supplies the position and performs the actual world
 * interaction separately.
 *
 * @param type bucket contents sound category
 */
public record BucketEmptySound(Type type) implements Sound {

    /**
     * {@code Type} identifies the bucket contents used to select an
     * interaction sound.
     */
    public enum Type {
        /**
         * Water bucket contents category.
         */
        WATER,
        /**
         * Lava bucket contents category.
         */
        LAVA,
        /**
         * Powder-snow bucket contents category.
         */
        POWDER_SNOW,
        /**
         * Fish-bearing bucket contents category.
         */
        FISH
    }
}
