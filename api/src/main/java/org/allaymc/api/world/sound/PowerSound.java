package org.allaymc.api.world.sound;

/**
 * Sound played when a redstone component is powered on/off
 *
 * @param powered whether the redstone component is powered
 * @author daoge_cmd
 */
public record PowerSound(boolean powered) implements Sound {
}
