package org.allaymc.api.world.particle;

import org.joml.Vector3ic;

/**
 * {@code DragonEggTeleportParticle} describes the displacement visualized when
 * a dragon egg teleports. The runtime packs absolute coordinate magnitudes
 * into separate bytes with sign bits, so callers must keep each magnitude
 * within one byte to avoid overlapping fields.
 *
 * @param diff {@code diff} retains the supplied displacement vector. Callers
 * must leave it unchanged until particle encoding finishes.
 *
 * @author daoge_cmd
 */
public record DragonEggTeleportParticle(Vector3ic diff) implements Particle {
}
