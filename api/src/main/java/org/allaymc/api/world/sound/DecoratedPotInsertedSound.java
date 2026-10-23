package org.allaymc.api.world.sound;

/**
 * {@code DecoratedPotInsertedSound} describes successful item insertion into a
 * decorated pot. The runtime uses {@code progress} to raise pitch through
 * {@code 0.7 + 0.5 * progress}, without clamping the supplied value; inventory
 * updates occur separately in the caller.
 *
 * @param progress insertion fullness used for pitch scaling
 */
public record DecoratedPotInsertedSound(double progress) implements Sound {
}
