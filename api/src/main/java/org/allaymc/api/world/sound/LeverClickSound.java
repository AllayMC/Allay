package org.allaymc.api.world.sound;

/**
 * Sound played when a lever is toggled.
 *
 * @param powered whether the lever is now in the powered state
 * @author daoge_cmd
 */
public record LeverClickSound(boolean powered) implements Sound {
}
