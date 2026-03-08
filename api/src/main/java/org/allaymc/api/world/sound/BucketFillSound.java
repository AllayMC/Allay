package org.allaymc.api.world.sound;

/**
 * Sound played when a bucket is filled from a source in the world.
 *
 * @param type the liquid/content type of the bucket
 */
public record BucketFillSound(Type type) implements Sound {

    public enum Type {
        WATER,
        LAVA,
        POWDER_SNOW,
        FISH
    }
}
