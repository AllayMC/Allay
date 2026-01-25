package org.allaymc.api.world.sound;

/**
 * Sound played when pointed dripstone drips liquid.
 *
 * @param isWater whether the liquid is water (true) or lava (false)
 * @author daoge_cmd
 */
public record PointedDripstoneDripSound(boolean isWater) implements Sound {
}
