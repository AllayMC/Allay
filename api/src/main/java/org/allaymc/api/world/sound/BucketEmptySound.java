package org.allaymc.api.world.sound;

/**
 * Sound played when a bucket that contains a liquid is emptied into the world.
 *
 * @param type the liquid/content type of the bucket
 */
public record BucketEmptySound(Type type) implements Sound {

    public enum Type {
        WATER,
        LAVA,
        POWDER_SNOW,
        FISH
    }
}
