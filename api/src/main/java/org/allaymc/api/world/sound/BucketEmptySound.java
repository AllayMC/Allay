package org.allaymc.api.world.sound;

/**
 * Sound played when a bucket that contains a liquid is emptied into the world.
 *
 * @param water whether the liquid is water. Setting this to {@code false} means the liquid is lava
 */
public record BucketEmptySound(boolean water) implements Sound {
}
