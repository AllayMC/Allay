package org.allaymc.api.world.sound;

/**
 * {@code FallSound} describes the landing sound selected from an entity's fall
 * distance. The current runtime chooses the large-fall event above four blocks
 * and the small-fall event otherwise; constructing this descriptor neither
 * computes nor applies fall damage.
 *
 * @param distance fall distance in blocks
 */
public record FallSound(double distance) implements Sound {
}
