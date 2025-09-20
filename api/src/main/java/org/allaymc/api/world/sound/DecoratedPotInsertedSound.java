package org.allaymc.api.world.sound;

/**
 * Sound played when an item is successfully inserted into a decorated pot.
 *
 * @param progress a numeric value representing how much of the decorated pot has been filled;
 *                 higher values indicate a more-filled pot
 */
public record DecoratedPotInsertedSound(double progress) implements Sound {
}
