package org.allaymc.api.world.sound;

/**
 * Sound played when a bucket is filled from a liquid source in the world.
 *
 * @param water whether the liquid is water. Setting this to {@code false} means the liquid is lava
 */
public record BucketFillSound(boolean water) implements Sound {
}
