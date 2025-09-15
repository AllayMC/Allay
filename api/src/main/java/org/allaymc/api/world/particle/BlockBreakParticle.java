package org.allaymc.api.world.particle;

import org.allaymc.api.block.type.BlockState;

/**
 * BlockBreakParticle is a particle sent when a block is broken. It represents particles
 * textured like the block that the particle holds.
 *
 * @param blockState the block state of which particles should be shown. The particles will change
 *                   depending on what block state is held.
 * @author daoge_cmd
 */
public record BlockBreakParticle(BlockState blockState) implements Particle {
}
