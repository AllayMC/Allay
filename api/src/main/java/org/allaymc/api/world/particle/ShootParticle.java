package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.BlockFace;

/**
 * {@code ShootParticle} describes the directional puff emitted by a dispenser
 * or dropper. The runtime uses {@code face} to select both the direction code
 * and positional offset, so the caller supplies the originating block position
 * separately from the direction.
 *
 * @param face dispenser or dropper facing
 *
 * @author daoge_cmd
 */
public record ShootParticle(BlockFace face) implements Particle {
}
