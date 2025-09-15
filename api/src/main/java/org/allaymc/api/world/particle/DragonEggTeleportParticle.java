package org.allaymc.api.world.particle;

import org.joml.Vector3ic;

/**
 * DragonEggTeleportParticle is a particle that shows up when a dragon egg teleports.
 *
 * @param diff the vector with the values being the difference from the original position to
 *             the new position.
 * @author daoge_cmd
 */
public record DragonEggTeleportParticle(Vector3ic diff) implements Particle {
}
