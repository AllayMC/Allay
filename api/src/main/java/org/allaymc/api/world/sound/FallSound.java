package org.allaymc.api.world.sound;

/**
 * Sound played when an entity falls and hits the ground.
 *
 * @param distance the distance the entity fell (units are implementation-specific, commonly measured in blocks)
 */
public record FallSound(double distance) implements Sound {
}
