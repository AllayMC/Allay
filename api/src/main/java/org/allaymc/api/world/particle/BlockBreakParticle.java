package org.allaymc.api.world.particle;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockBreakParticle} represents block-textured debris for a broken
 * block. The runtime encodes the supplied state hash as the destruction effect
 * payload, so the descriptor selects appearance while its caller separately
 * supplies the effect position.
 *
 * @param blockState block state supplying the debris texture
 *
 * @author daoge_cmd
 */
public record BlockBreakParticle(BlockState blockState) implements Particle {
}
