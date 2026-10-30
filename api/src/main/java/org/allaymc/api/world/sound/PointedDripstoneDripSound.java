package org.allaymc.api.world.sound;

/**
 * {@code PointedDripstoneDripSound} describes a pointed-dripstone drip landing
 * in a cauldron. The runtime chooses a water or lava event from {@code
 * isWater}; the caller supplies the position and changes any cauldron contents
 * independently of this sound descriptor.
 *
 * @param isWater {@code true} for water, {@code false} for lava
 *
 * @author daoge_cmd
 */
public record PointedDripstoneDripSound(boolean isWater) implements Sound {
}
