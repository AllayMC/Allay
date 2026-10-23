package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;

/**
 * {@code PunchBlockParticle} describes block-textured debris emitted from a
 * selected block face. The runtime chooses the directional effect from {@code
 * blockFace} and uses the state hash for its texture; neither component is
 * substituted when it is {@code null}.
 *
 * @param blockState block state supplying the debris texture
 *
 * @param blockFace face receiving the punching effect
 *
 * @author daoge_cmd
 */
public record PunchBlockParticle(BlockState blockState, BlockFace blockFace) implements Particle {
}
