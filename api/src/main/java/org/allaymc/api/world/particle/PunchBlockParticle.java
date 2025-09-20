package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;

/**
 * PunchBlockParticle is a particle shown when a player is punching a block.
 * It shows particles of a specific block type at a particular face of a block.
 *
 * @author daoge_cmd
 */
public record PunchBlockParticle(BlockState blockState, BlockFace blockFace) implements Particle {
}
