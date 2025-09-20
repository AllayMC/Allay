package org.allaymc.api.world.sound;

/**
 * Sound played when an entity (commonly a player) performs an attack.
 *
 * @param damage true if the attack actually dealt damage; false otherwise. Implementations may play different
 *               sounds depending on this value.
 */
public record AttackSound(boolean damage) implements Sound {
}
