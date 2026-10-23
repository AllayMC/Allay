package org.allaymc.api.world.sound;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockBreakingSound} describes the repeated hit sound for block
 * breaking. The runtime sends the supplied state hash with the hit event so
 * the client can select the material sound; callers decide when to repeat the
 * effect.
 *
 * @param blockState block state supplying the hit material
 */
public record BlockBreakingSound(BlockState blockState) implements Sound {
}
