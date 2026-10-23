package org.allaymc.api.world.sound;

/**
 * {@code AttackSound} describes an attack sound selected by whether damage was
 * dealt. The runtime chooses the strong-attack event for {@code true} and the
 * no-damage event for {@code false}; this descriptor does not apply combat
 * damage itself.
 *
 * @param damage whether the attack dealt damage
 */
public record AttackSound(boolean damage) implements Sound {
}
